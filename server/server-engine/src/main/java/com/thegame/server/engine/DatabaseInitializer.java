package com.thegame.server.engine;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.reflection.PackageUtils;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.messages.output.AreaMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import com.thegame.server.persistence.entities.Area;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;
import com.thegame.server.engine.intern.services.MapperService;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.entities.AreaExitId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class DatabaseInitializer {
	
	private static final LogStream logger=LogStream.getLogger(DatabaseInitializer.class);
	
	private final String assetsPackage;
	private final Genson genson;
	private final LocationDao locationDao;
	
	
	protected DatabaseInitializer(final String _assetsPackage){
		this.assetsPackage=_assetsPackage;
		this.genson=new GensonBuilder().useRuntimeType(true).create();
		this.locationDao=PersistenceServiceFactory.LOCATIONDAO.getInstance(LocationDao.class);
		logger.info("Current path: {}",Paths.get(".").toAbsolutePath().toString());
	}
	
	public static DatabaseInitializer getInstance(){
		return new DatabaseInitializer(Configuration.DATABASE_ASSETS_PACKAGE.getValue());
	}
	
	protected Stream<String> getPackageResources(final String _package) throws IOException{
		final List<String> explorePackages=Stream.of(_package)
													.collect(Collectors.toList());
		final List<String> resourcesFilter=Stream.of("*.json")
													.collect(Collectors.toList());
		return PackageUtils.getExistentResources(explorePackages,resourcesFilter)
													.stream();
	} 
	
	protected Stream<AreaMessageBean> loadAreas() throws IOException{
		
		return getPackageResources(this.assetsPackage+".rooms")
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}",Area.class.getSimpleName(),resource))
			.filter(resource -> resource.endsWith(".json"))
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}::json",Area.class.getSimpleName(),resource))
			.map(resource -> resource.substring(0, resource.length()-5).replaceAll("\\.","/")+".json")
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}::json",Area.class.getSimpleName(),resource))
			.map(resource -> LambdaUtils.readAllBytesFromResource(resource))
			.filter(optionalBytes -> optionalBytes.isPresent())
			.map(optionalBytes -> optionalBytes.get())
			.peek(bytes -> logger.finest("database-initializer::initialize::{}::content-size::{}",Area.class.getSimpleName(),bytes.length))
			.map(bytes -> this.genson.deserialize(bytes, AreaMessageBean.class))
			.peek(areaMessageBean -> logger.finest("database-initializer::initialize::{}::parsed::{}",Area.class.getSimpleName(),areaMessageBean))
			.filter(areaMessageBean -> areaMessageBean!=null);
	}
	
	public void initialize(){

		try {
			logger.trace("database-initializer::initialize::begin");
			this.loadAreas()
				.map(areaMessageBean -> MapperService.instance.toEntity(areaMessageBean))
				.peek(areaEntity -> logger.finest("database-initializer::initialize::{}::entity::{}",Area.class.getSimpleName(),areaEntity))
				.filter(areaEntity -> areaEntity!=null)
				.forEach(areaEntity -> locationDao.saveArea(areaEntity));
			logger.info("database-initializer::initialize::{}::done",Area.class.getSimpleName());
			this.loadAreas()
				.flatMap(areaMessageBean -> areaMessageBean
													.getExits()
													.entrySet()
													.stream()
													.map(exitEntry -> AreaExit.builder()
																			.id(AreaExitId.builder()
																							.area(Area.builder()
																											.id(areaMessageBean.getId())
																											.build())
																							.name(exitEntry.getKey())
																							.build())
																			.toArea(Area.builder()
																							.id(exitEntry.getValue())
																							.build())
																			.build()))
				.peek(areaExitEntity -> logger.finest("database-initializer::initialize::{}::entity::{}",AreaExit.class.getSimpleName(),areaExitEntity))
				.filter(areaExitEntity -> areaExitEntity!=null)
				.forEach(areaExitEntity -> locationDao.saveAreaExit(areaExitEntity));
			logger.info("database-initializer::initialize::{}::done",AreaExit.class.getSimpleName());
			logger.debug("database-initializer::initialize::end");
		} catch (IOException e) {
			logger.error("database-initializer::initialize::fail::{}",e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
