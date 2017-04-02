package com.thegame.server;

import com.thegame.server.daemons.HTTPDaemon;
import com.thegame.server.presentation.TheGameApplication;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class TheGameServer {

	protected static Optional<HTTPDaemon> daemon=Optional.empty(); 
	
	enum Actions{
		START{
			@Override
			public void execute(final Map<ApplicationParameter,Optional<String>> _params){
				
				try{
					final String host=_params.get(ApplicationParameter.HOST).orElse(ApplicationParameter.HOST.defaultValue);
					final int port=Integer.valueOf(_params.get(ApplicationParameter.PORT).orElse(ApplicationParameter.PORT.defaultValue));
					final String name=_params.get(ApplicationParameter.NAME).orElse(ApplicationParameter.NAME.defaultValue);
					TheGameServer.daemon=Optional.of(new HTTPDaemon(host,port,name)
												.start()
												.deployApplication(new TheGameApplication()));
				}catch(Throwable e){
					TheGameServer.daemon
						.ifPresent(httpDaemon -> httpDaemon.stop());
				}
			}
		},
		SHUTDOWN{
			@Override
			public void execute(final Map<ApplicationParameter,Optional<String>> _params){
				TheGameServer.daemon
					.ifPresent(httpDaemon -> httpDaemon.stop());
			}
		},
		;
		
		public abstract void execute(final Map<ApplicationParameter,Optional<String>> _params);
	}
	
	public static void configureLogs() throws SecurityException, UnsupportedEncodingException{
		System.setProperty("java.util.logging.SimpleFormatter.format","%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n");
		Stream.of(Logger.getLogger("").getHandlers())
			.forEach(handler -> handler.setLevel(Level.FINEST));
		Logger.getLogger("com.thegame").setLevel(Level.FINEST);
	}
	
	@SuppressWarnings("CallToPrintStackTrace")
    public static void main(final String[] _args)  {

		try{			
			configureLogs();
			final Map<ApplicationParameter,Optional<String>> params=ApplicationParameter.loadParams(_args);
			Actions.valueOf(params.get(ApplicationParameter.ACTION).orElse(ApplicationParameter.ACTION.defaultValue).toUpperCase())
				.execute(params);
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run(){ 
					TheGameServer.daemon
						.ifPresent(httpDaemon -> httpDaemon.stop());
				}
			});
		}catch(Throwable e){
			System.err.println("Wrong command line!");
			System.out.println("Usage: \n\tMigrationTool <action:[start|stop]> <host:[domain or ip]>  <port:[value]> <name:[process-name]>");
			e.printStackTrace();
		}
    }
}
