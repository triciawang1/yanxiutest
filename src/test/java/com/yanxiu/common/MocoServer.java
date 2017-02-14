package com.yanxiu.common;

import static com.github.dreamhead.moco.Runner.runner;

import java.io.UnsupportedEncodingException;

import org.testng.log4testng.Logger;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;


import static com.github.dreamhead.moco.Moco.*;

public class MocoServer  {
	private static Logger log = Logger.getLogger(MocoServer.class);
	private static Runner runner;
	private static HttpServer mocoServer;
	private static int port = 8789;
	
	
	public static void startServer(){
		mocoServer = httpServer(port);

		runner = runner(mocoServer);
		log.info("start Moco Server");
		runner.start();
	}
	
	
	public static void stopServer(){
		runner.stop();
	}
	

	

	
	public static void response(String jsonFile,String requestUri) throws UnsupportedEncodingException{
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();	

        mocoServer.request(by(uri(requestUri))).response(with(text(body)),header("content-type", "application/json; charset=UTF-8"));
//		mocoServer.request(by(uri(requestUri))).response(body);

	}
	
	public static void responseWithPlainText(String jsonFile,String requestUri) throws UnsupportedEncodingException{
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();	
        log.info(jsonFile+":"+body);
        mocoServer.request(by(uri(requestUri))).response(with(text(body)),header("content-type", " text/plain;charset=UTF-8"));
	}
	
	public void responseWithTransferEncoding(String jsonFile,String requestUri){
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();	

        mocoServer.request(by(uri(requestUri))).response(with(text(body)),header("content-type", "application/json; charset=UTF-8"),header("transfer-encoding", "chunked"));
	}
}
