package com.thegame.server.daemons;

import com.thegame.server.TheGameServer;
import com.thegame.server.common.IsApplication;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import java.util.stream.Collectors;
import javax.servlet.ServletException;

/**
 * @author afarre
 */
public class HTTPDaemon {
		
	private final String name;
	private final Undertow server;
	private final ServletContainer container;
	private final PathHandler path;
	private DeploymentInfo deployment;
	
	public HTTPDaemon(final String _host,final int _port,final String _name){
		this.name=_name;
        this.path = Handlers.path();
        this.server = Undertow.builder()
                .addHttpListener(_port,_host)
                .setHandler(this.path)
                .build();
        this.container = ServletContainer.Factory.newInstance();
		
	}
	
	public HTTPDaemon start(){
		System.out.println("daemon::start::begin");
        this.server.start();
		return this;
	}
	public HTTPDaemon stop(){
		System.out.println("daemon::stop::begin");
		this.container.removeDeployment(this.deployment);
        this.server.stop();
		return this;
	}
	
	public HTTPDaemon deployApplication(final IsApplication _application){

		try {
			final WebSocketDeploymentInfo deploymentInfo=new WebSocketDeploymentInfo()
					.setBuffers(new DefaultByteBufferPool(true, 100));
			_application.getEndpoints().stream().forEach(endpointClass -> deploymentInfo.addEndpoint(endpointClass));

			this.deployment = new DeploymentInfo()
					.setClassLoader(TheGameServer.class.getClassLoader())
					.setContextPath(_application.getContext())
					.addWelcomePage(_application.getWelcomePage())
					.addListeners(_application.getListeners().stream()
												.map(listener -> new ListenerInfo(listener))
												.collect(Collectors.toSet()))
					.setResourceManager(new ClassPathResourceManager(TheGameServer.class.getClassLoader(), _application.getResources()))
					.addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME,deploymentInfo)
					.setDeploymentName(_application.getName()+".war");
			final DeploymentManager manager = this.container.addDeployment(this.deployment);
			manager.deploy();
            this.path.addPrefixPath("/", manager.start());                                                                                        
		} catch (ServletException e) {
            throw new RuntimeException(e);
        }

		return this;
	}

	
}
