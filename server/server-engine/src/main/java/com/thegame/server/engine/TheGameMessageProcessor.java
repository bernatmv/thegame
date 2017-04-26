package com.thegame.server.engine;

import com.thegame.server.common.concurrent.CustomThreadFactory;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.intern.configuration.Configuration;
import com.thegame.server.engine.intern.EngineServiceFactory;
import com.thegame.server.engine.intern.services.ConfigurationService;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.thegame.server.engine.intern.tasks.BaseMessageTask;
import com.thegame.server.engine.intern.support.MessageTaskFactory;
import com.thegame.server.engine.messages.input.InputMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;

/**
 *
 * @author afarre
 */
public class TheGameMessageProcessor extends ThreadPoolExecutor.CallerRunsPolicy{
	
	private static final LogStream logger=LogStream.getLogger(TheGameMessageProcessor.class);
	
	private static volatile TheGameMessageProcessor instance;
	
	private ThreadPoolExecutor threadPool;
	private Map<Class<? extends IsMessageBean>,Class<? extends BaseMessageTask<? extends IsMessageBean>>> taskMapping;
		
	
	protected TheGameMessageProcessor(){
		
		logger.trace("message-processor::initialization::begin");
		this.threadPool=null;
		ConfigurationService configurationService=EngineServiceFactory.CONFIGURATION
								.getInstance(ConfigurationService.class);
		configure(configurationService.get(),"initialization");
		configurationService.registerListener((newConfiguration) -> this.configure(newConfiguration,"reconfiguration-event"));
		logger.finest("message-processor::initialization::reconfiguration-event::registered");
		logger.debug("message-processor::initialization::end");
	}
	
	@SuppressWarnings({"DoubleCheckedLocking", "SynchronizeOnNonFinalField"})
	public static TheGameMessageProcessor getInstance(){

		if(TheGameMessageProcessor.instance==null){
			synchronized(TheGameMessageProcessor.class){
				if(TheGameMessageProcessor.instance==null){
					TheGameMessageProcessor.instance=new TheGameMessageProcessor();
				}
			}
		}

		return TheGameMessageProcessor.instance;
	}
	
	
	private void configure(final Map<String,String> _configuration,final String _type){
		
		final ThreadPoolExecutor oldThreadPool=this.threadPool;
		logger.trace("message-processor::{}::configure::begin",_type);
		final int corePoolSize=Configuration.MESSAGE_PROCESSOR_CORE_POOL_SIZE.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_CORE_POOL_SIZE,corePoolSize);
		final int maxPoolSize=Configuration.MESSAGE_PROCESSOR_MAX_POOL_SIZE.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_MAX_POOL_SIZE,maxPoolSize);
		final int keepAlive=Configuration.MESSAGE_PROCESSOR_KEEP_ALIVE.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_KEEP_ALIVE,keepAlive);
		final TimeUnit keepAliveTimeUnit=Configuration.MESSAGE_PROCESSOR_KEEP_ALIVE_UNITS.getEnumValue(TimeUnit.class,_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_KEEP_ALIVE_UNITS,keepAliveTimeUnit);
		final int queueSize=Configuration.MESSAGE_PROCESSOR_QUEUE_SIZE.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_QUEUE_SIZE,queueSize);
		final int threadPriority=Configuration.MESSAGE_PROCESSOR_THREAD_PRIORITY.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_THREAD_PRIORITY,threadPriority);
		final int threadStackSize=Configuration.MESSAGE_PROCESSOR_THREAD_STACKSIZE.getIntValue(_configuration);
		logger.finest("message-processor::{}::configure::{}::{}",_type,Configuration.MESSAGE_PROCESSOR_THREAD_STACKSIZE,threadStackSize);
		final BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<>(queueSize);
		logger.finest("message-processor::{}::configure::queue{}::instantiated",_type,workQueue);
		final ThreadFactory threadFactory=new CustomThreadFactory("TheGame-MessageProcessor",threadPriority,threadStackSize);
		logger.finest("message-processor::{}::configure::thread-factory::{}::instantiated",_type,threadFactory);
		final ThreadPoolExecutor newThreadPool=new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAlive, keepAliveTimeUnit, workQueue, threadFactory, this);
		logger.debug("message-processor::{}::configure::thread-pool::{}",_type,newThreadPool);
		this.threadPool=newThreadPool;
		logger.debug("message-processor::{}::configure::new::thread-pool::{}::active",_type,newThreadPool);
		
		if(oldThreadPool!=null){
			logger.debug("message-processor::{}::configure::old::thread-pool::{}::terminate::begin",_type,oldThreadPool);
			((CustomThreadFactory)oldThreadPool.getThreadFactory()).getGroup().setDaemon(true);
			oldThreadPool.setKeepAliveTime(1,TimeUnit.MILLISECONDS);
			oldThreadPool.setCorePoolSize(0);
			oldThreadPool.shutdown();
			logger.debug("message-processor::{}::configure::old::thread-pool::{}::terminate::launched",_type,oldThreadPool);
		}
		logger.debug("message-processor::{}::configure::end",_type);

	}
	
	public void process(final InputMessageBean _task){
		
		logger.trace("message-processor::process::begin");
		threadPool.execute(MessageTaskFactory.getInstance(_task));
		logger.debug("message-processor::process::end");
	}

	@Override
	public void rejectedExecution(final Runnable _runnable,final ThreadPoolExecutor _threadPoolExecutor) {
		
		logger.warning("message-processor::queue-is-full::task-running-by-caller");
		super.rejectedExecution(_runnable, _threadPoolExecutor);
	}
}
