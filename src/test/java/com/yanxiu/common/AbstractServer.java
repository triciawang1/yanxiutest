package com.yanxiu.common;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.testng.log4testng.Logger;

public abstract class AbstractServer {

	private Logger log = Logger.getLogger(AbstractServer.class);
	protected Boolean isServerStarted = false;
	protected String serverName;
	private static final int RETRY_TIMES = 3;
	private static final int TIMEOUT = 30000;
	private CommandLine startCommand;
	private String successfullMsg;
    
	private static String executor;
	
	static {
		if (CommonUtil.isMacOs()) {
			executor = "/usr/local/bin/node";			
		} else if (CommonUtil.isWindowsOS()) {
			executor = "C:/Program Files/nodejs/node.exe";			
		}
	}
	
	public AbstractServer(String serverName,String successfullMsg, String... commandArgs) {
		this.serverName = serverName;
		startCommand = new CommandLine(executor);
		startCommand.addArguments(commandArgs);
		this.successfullMsg = successfullMsg;
	}
	public AbstractServer(){
		
	}

	public void startServer() {

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);

		log.info("------------------------");
		log.info("start " + serverName);
		log.info("------------------------");

		CollectingLogOutputStream out = new CollectingLogOutputStream();
		PumpStreamHandler psh = new PumpStreamHandler(out);
		executor.setStreamHandler(psh);
		int i = 0;
		
		log.info("isServerStarted:"+isServerStarted);
		
		
		while (!isServerStarted && i < RETRY_TIMES) {
			log.info("try to start " + serverName + " " + i + " time");

			try {
				
				executor.execute(startCommand, resultHandler);
			} catch (ExecuteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<String> str = out.getLines();
			String welcomeMsg = "";
			for (String s : str) {
				log.info(s);
				if (s.contains(successfullMsg)) {
					welcomeMsg = s;
					log.info("welcomeMsg:" + welcomeMsg);
				}
			}

			if (welcomeMsg.contains(successfullMsg)) {
				isServerStarted = true;
			} else {
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}

		if (isServerStarted == true) {
			log.info("--------------------------------------");
			log.info(serverName+" is started successfully");
			log.info("--------------------------------------");
		} else {
			log.info("----------------------------------------");
			log.info(serverName + " is not started, will not run case");
			log.info("----------------------------------------");
			System.exit(0);
		}

	}

	public void stopServer() {

	}

	public Boolean isServerStarted(String port) throws IOException {
		String str;
		if (!CommonUtil.isMacOs()) {
			// str = CommonUtil.execCmd("tasklist |findstr
			// node.exe").toString().trim();
			str = CommonUtil.execCmd("netstat -aon|findstr \""+port+"\"").toString().trim();
			log.info("check whether server is started");
			String[] lines = str.split("\n");

			if (str.length() > 0 && lines.length >= 1 && str.contains("LISTENING")) {
				isServerStarted = true;
				return true;
			}
		} else {
			str = CommonUtil.execCmd("lsof -i tcp:"+port).toString().trim();
			String[] lines = str.split("\n");
			if (lines.length >= 1 && lines.length >= 1 && str.contains("LISTEN")) {
				isServerStarted = true;
				return true;
			}
		}
		log.info("server is not started");
		isServerStarted = false;
		return false;
		
	}

}


class CollectingLogOutputStream extends LogOutputStream {
	private final List<String> lines = new LinkedList<String>();

	@Override
	protected void processLine(String line, int level) {
		lines.add(line);
	}

	public List<String> getLines() {
		return lines;
	}
}
