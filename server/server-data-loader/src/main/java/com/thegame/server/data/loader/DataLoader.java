package com.thegame.server.data.loader;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.reflection.PackageUtils;
import com.thegame.server.data.loader.beans.AreaImport;
import com.thegame.server.data.loader.beans.ItemImport;
import com.thegame.server.data.loader.services.ImportMapperService;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import com.thegame.server.persistence.entities.Area;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;
import com.thegame.server.persistence.ResourceDao;
import com.thegame.server.persistence.entities.AreaExit;
import com.thegame.server.persistence.entities.AreaExitId;
import com.thegame.server.persistence.entities.Item;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class DataLoader {
	
	private static final LogStream logger=LogStream.getLogger(DataLoader.class);
	
	private final String assetsPackage;
	private final Genson genson;
	private final LocationDao locationDao;
	private final ResourceDao resourceDao;
	
	
	protected DataLoader(final String _assetsPackage){
		this.assetsPackage=_assetsPackage;
		this.genson=new GensonBuilder().useRuntimeType(true).create();
		this.locationDao=PersistenceServiceFactory.LOCATIONDAO.getInstance(LocationDao.class);
		this.resourceDao=PersistenceServiceFactory.RESOURCEDAO.getInstance(ResourceDao.class);
		logger.info("Current path: {}",Paths.get(".").toAbsolutePath().toString());
	}
	
	public static DataLoader getInstance(){
		return new DataLoader(Configuration.DATABASE_ASSETS_PACKAGE.getValue());
	}
	
	protected Stream<String> getPackageResources(final String _package) throws IOException{
		final List<String> explorePackages=Stream.of(_package)
													.collect(Collectors.toList());
		final List<String> resourcesFilter=Stream.of("*.json")
													.collect(Collectors.toList());
		return PackageUtils.getExistentResources(explorePackages,resourcesFilter)
													.stream();
	} 

	protected Stream<byte[]> loadResources(final String _package,final String _resourceType) throws IOException{
		return getPackageResources(this.assetsPackage+"."+_package)
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}",_resourceType,resource))
			.filter(resource -> resource.endsWith(".json"))
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}::json",_resourceType,resource))
			.map(resource -> resource.substring(0, resource.length()-5).replaceAll("\\.","/")+".json")
			.peek(resource -> logger.finest("database-initializer::initialize::{}::found::{}::json",_resourceType,resource))
			.map(resource -> LambdaUtils.readAllBytesFromResource(resource))
			.filter(optionalBytes -> optionalBytes.isPresent())
			.map(optionalBytes -> optionalBytes.get())
			.peek(bytes -> logger.finest("database-initializer::initialize::{}::content-size::{}",_resourceType,bytes.length));
	}

	protected Stream<ItemImport> loadItems() throws IOException{
		return loadResources("items",Item.class.getSimpleName())
			.map(bytes -> this.genson.deserialize(bytes, ItemImport.class))
			.peek(itemMessageBean -> logger.finest("database-initializer::initialize::{}::parsed::{}",Item.class.getSimpleName(),itemMessageBean))
			.filter(itemMessageBean -> itemMessageBean!=null);
	}
	
	protected Stream<AreaImport> loadAreas() throws IOException{
		return loadResources("rooms",Area.class.getSimpleName())
			.map(bytes -> this.genson.deserialize(bytes, AreaImport.class))
			.peek(areaMessageBean -> logger.finest("database-initializer::initialize::{}::parsed::{}",Area.class.getSimpleName(),areaMessageBean))
			.filter(areaMessageBean -> areaMessageBean!=null);
	}
	
	public void initialize(){

		try {
			logger.trace("database-initializer::initialize::begin");
			loadItems()
				.map(itemImport -> ImportMapperService.instance.toEntity(itemImport))
				.peek(itemEntity -> logger.finest("database-initializer::initialize::{}::entity::{}",Item.class.getSimpleName(),itemEntity))
				.filter(itemEntity -> itemEntity!=null)
				.forEach(itemEntity -> resourceDao.saveItem(itemEntity));
			logger.info("database-initializer::initialize::{}::done",Item.class.getSimpleName());
			loadAreas()
				.map(areaImport -> ImportMapperService.instance.toEntity(areaImport))
				.peek(areaEntity -> logger.finest("database-initializer::initialize::{}::entity::{}",Area.class.getSimpleName(),areaEntity))
				.filter(areaEntity -> areaEntity!=null)
				.forEach(areaEntity -> locationDao.saveArea(areaEntity));
			logger.info("database-initializer::initialize::{}::done",Area.class.getSimpleName());
			loadAreas()
				.flatMap(areaImport -> areaImport
										.getExits()
										.entrySet()
										.stream()
										.map(exitEntry -> AreaExit.builder()
															.id(AreaExitId.builder()
																	.area(Area.builder()
																		.id(areaImport.getId())
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
