package com.thegame.server.presentation;

import com.thegame.server.common.IsApplication;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.DatabaseInitializer;
import com.thegame.server.engine.TheGameMessageProcessor;
import com.thegame.server.engine.intern.support.MessageTaskFactory;
import com.thegame.server.persistence.PersistenceServiceFactory;
import com.thegame.server.presentation.endpoints.TheGameEndpoint;
import com.thegame.server.presentation.messages.support.MessageFactory;
import java.util.Collection;
import java.util.EventListener;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.Getter;

/**
 * @author afarre
 */
public class TheGameApplication implements IsApplication,ServletContextListener {
	
	private static final LogStream logger=LogStream.getLogger(TheGameApplication.class);
	
	@Getter
	private final String name="thegame";
	@Getter
	private final Set<Class<?>> endpoints=Stream.of(TheGameEndpoint.class).collect(Collectors.toSet());
	@Getter
	private final Collection<Class<? extends EventListener>> listeners=Stream.of(this.getClass()).collect(Collectors.toSet());

	
	@Override
	public void contextInitialized(ServletContextEvent _sce) {
		
		logger.trace("application::init::begin");
		PersistenceServiceFactory.init();
		logger.trace("application::init::database-connection");
		DatabaseInitializer.getInstance().initialize();
		logger.trace("application::init::database");
		MessageFactory.init();
		logger.trace("application::init::message-factory");
		MessageTaskFactory.init();
		logger.trace("application::init::message-task-factory");
		TheGameMessageProcessor.getInstance();
		logger.trace("application::init::message-processor");
		logger.info("=================================================================================================================");
		logger.info("  _________  ___  ___  _______           ________  ________  _____ ______   _______      ");
		logger.info(" |\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\     ");
		logger.info(" \\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|    ");
		logger.info("      \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__  ");
		logger.info("       \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ ");
		logger.info("        \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\");
		logger.info("         \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|");
		logger.info("=================================================================================================================");
		logger.info("Application deployed");
		logger.trace("application::init::end");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent _sce) {
		logger.info("Application undeployed");
	}
}
