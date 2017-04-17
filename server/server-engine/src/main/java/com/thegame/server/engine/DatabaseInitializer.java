package com.thegame.server.engine;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.messages.AreaMessageBean;
import com.thegame.server.persistence.LocationDao;
import com.thegame.server.persistence.PersistenceServiceFactory;
import com.thegame.server.persistence.entities.Area;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import com.thegame.server.engine.intern.services.MapperService;

/**
 * @author afarre
 */
public class DatabaseInitializer {
	
	private static final LogStream logger=LogStream.getLogger(DatabaseInitializer.class);
	
	private final Path assetsFolder;
	private final Genson genson;
	private final LocationDao locationDao;
	
	
	protected DatabaseInitializer(final Path _assetsFolder){
		this.assetsFolder=_assetsFolder;
		this.genson=new GensonBuilder().useRuntimeType(true).create();
		this.locationDao=PersistenceServiceFactory.LOCATIONDAO.getInstance(LocationDao.class);
		logger.info("Current path: {}",Paths.get(".").toAbsolutePath().toString());
	}
	
	public static DatabaseInitializer getInstance(){
		return new DatabaseInitializer(Configuration.DATABASE_ASSETS_FOLDER.getPathValue());
	}
	
	
	protected Stream<Area> loadAreas() throws IOException{
		
		return Files.list(this.assetsFolder.resolve("rooms"))
			.peek(filePath -> logger.finest("database-initializer::initialize::{}::found::{}",Area.class.getSimpleName(),filePath))
			.filter(filePath -> Files.isRegularFile(filePath))
			.peek(filePath -> logger.finest("database-initializer::initialize::{}::found::{}::regular-file",Area.class.getSimpleName(),filePath))
			.filter(filePath -> filePath.toString().endsWith(".json"))
			.peek(filePath -> logger.finest("database-initializer::initialize::{}::found::{}::json",Area.class.getSimpleName(),filePath))
			.filter(filePath -> Files.isReadable(filePath))
			.peek(filePath -> logger.finest("database-initializer::initialize::{}::found::{}::readable",Area.class.getSimpleName(),filePath))
			.map(filePath -> LambdaUtils.readAllBytes(filePath))
			.filter(optionalBytes -> optionalBytes.isPresent())
			.map(optionalBytes -> optionalBytes.get())
			.peek(bytes -> logger.finest("database-initializer::initialize::{}::content-size::{}",Area.class.getSimpleName(),bytes.length))
			.map(bytes -> this.genson.deserialize(bytes, AreaMessageBean.class))
			.peek(areaMessageBean -> logger.finest("database-initializer::initialize::{}::parsed::{}",Area.class.getSimpleName(),areaMessageBean))
			.filter(areaMessageBean -> areaMessageBean!=null)
			.map(areaMessageBean -> MapperService.instance.toEntity(areaMessageBean));
	}
	
	public void initialize(){

		try {
			logger.trace("database-initializer::initialize::begin");
			this.loadAreas()
				.peek(areaEntity -> logger.finest("database-initializer::initialize::{}::entity::{}",Area.class.getSimpleName(),areaEntity))
				.filter(areaEntity -> areaEntity!=null)
				.forEach(areaEntity -> locationDao.saveArea(areaEntity));
			logger.info("database-initializer::initialize::{}::done",Area.class.getSimpleName());
			logger.debug("database-initializer::initialize::end");
		} catch (IOException e) {
			logger.error("database-initializer::initialize::fail::{}",e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
