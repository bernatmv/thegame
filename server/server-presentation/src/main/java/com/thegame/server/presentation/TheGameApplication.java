package com.thegame.server.presentation;

import com.thegame.server.common.IsApplication;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.presentation.endpoints.TheGameEndpoint;
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
		logger.info("  _________  ___  ___  _______           ________  ________  _____ ______   _______      ");
		logger.info(" |\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\     ");
		logger.info(" \\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|    ");
		logger.info("      \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__  ");
		logger.info("       \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ ");
		logger.info("        \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\");
		logger.info("         \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|");
		logger.info("Application deployed");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent _sce) {
		logger.info("Application undeployed");
	}
}
