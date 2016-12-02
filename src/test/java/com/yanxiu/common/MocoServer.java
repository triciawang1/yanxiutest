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
	private static int port = 8789;
	
	@Override
	public void startServer(){
		mocoServer = httpServer(port);

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
        log.info(jsonFile+":"+body);
        mocoServer.request(by(uri(requestUri))).response(with(text(body)),header("content-type", "application/json; charset=UTF-8"));
//		mocoServer.request(by(uri(requestUri))).response(body);

	}
}
