package com.yanxiu.common;

import static com.github.dreamhead.moco.Runner.runner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.testng.log4testng.Logger;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.ResponseHandler;
import com.github.dreamhead.moco.Runner;

import static com.github.dreamhead.moco.Moco.*;

public class MocoServer {
	private static Logger log = Logger.getLogger(MocoServer.class);
	private static Runner runner;
	private static HttpServer mocoServer;
	private static int port = 8789;
	private static boolean isStopped = true;

	public static void startServer() {
		mocoServer = httpServer(port);

		runner = runner(mocoServer);
		log.info("start Moco Server");
		runner.start();
		isStopped = false;
	}

	public static void stopServer() {
		log.info("stop Moco Server");
		if (!isStopped) {
			log.info("moco server is running, stop it");
			runner.stop();
			isStopped = true;
		}

	}

	public static void response(String jsonFile, String requestUri) throws UnsupportedEncodingException {
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();

		mocoServer.request(by(uri(requestUri))).response(with(text(body)),
				header("content-type", "application/json; charset=UTF-8"));
		// mocoServer.request(by(uri(requestUri))).response(body);

	}

	public static void seqResponse(String requestUri, String... jsonFiles) {
		// List<ResponseHandler> handlers = new ArrayList<ResponseHandler>();
		ResponseHandler[] handlers = new ResponseHandler[jsonFiles.length];
		int i = 0;
		for (String file : jsonFiles) {

			String body = CommonUtil.getJSONObjectFromFile(file).toString();
			handlers[i++] = with(text(body));
		}
		mocoServer.request(by(uri(requestUri))).response(seq(handlers));
	}

	public static void responseWithPlainText(String jsonFile, String requestUri) throws UnsupportedEncodingException {
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();
		log.info(jsonFile + ":" + body);
		mocoServer.request(by(uri(requestUri))).response(with(text(body)),
				header("content-type", " text/plain;charset=UTF-8"));
	}

	public void responseWithTransferEncoding(String jsonFile, String requestUri) {
		String body = CommonUtil.getJSONObjectFromFile(jsonFile).toString();

		mocoServer.request(by(uri(requestUri))).response(with(text(body)),
				header("content-type", "application/json; charset=UTF-8"), header("transfer-encoding", "chunked"));
	}
}
