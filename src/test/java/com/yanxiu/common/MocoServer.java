package com.yanxiu.common;

import static com.github.dreamhead.moco.Runner.runner;

import org.testng.log4testng.Logger;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;


import static com.github.dreamhead.moco.Moco.*;

public class MocoServer extends AbstractServer {
	private Logger log = Logger.getLogger(this.getClass());
	protected Runner runner;
	protected HttpServer mocoServer;
	
	@Override
	public void startServer(){
		mocoServer = httpServer(80);

		runner = runner(mocoServer);
		log.info("start Moco Server");
		runner.start();
	}
	
	@Override
	public void stopServer(){
		runner.stop();
	}
	

	
	public void response(String jsonFile,String requestUri){
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();	
        log.info(body);
		mocoServer.request(by(uri(requestUri))).response(body);
	}
}
