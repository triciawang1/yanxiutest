package com.yanxiu.common;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;
import org.testng.log4testng.Logger;

import com.yanxiu.test.cases.BaseCase;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AppiumServer extends AbstractServer {

	private Logger log = Logger.getLogger(AppiumServer.class);

	private static String serverName = "Appium Server";
	private static String successfullMsg = "Welcome to Appium";
	
	private static String jsFile;
	

	static {
		if (CommonUtil.isMacOs()) {
//			executor = "/usr/local/bin/node";
			jsFile = "/usr/local/lib//node_modules/appium/build/lib/main.js";
		} else if (CommonUtil.isWindowsOS()) {
//			executor = "D:/Program Files (x86)/Appium/node.exe";
//			executor = "D:/Program Files/nodejs/node.exe";
			jsFile = "D:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js";
		}
	}

	public AppiumServer() {
		super(serverName, successfullMsg, jsFile, "--address", "127.0.0.1", "--port", "4723", "--webhook", "localhost:9876" );

	}
	
	public void startServerRemotely() throws InterruptedException{
		log.info("prepare to start server from remote");

		Thread thread = new Thread() {
			public void run() {
				RemoteRunCmd.execCmd(
						"/usr/local/bin/node  /usr/local/lib//node_modules/appium/build/lib/main.js  --address  127.0.0.1  --port  4723");
			}

		};

		thread.start();
		Thread.sleep(8000);
	}

	
	public boolean isServerStarted(Boolean isRemoteRun) throws IOException {
		String str;
		if (isRemoteRun) {
			log.info("check whether server is started from remote server");
			str = RemoteRunCmd.execCmd("lsof -i tcp:4723").toString();
			log.info(str);
			String[] lines = str.split("\n");
			if (str.length() > 0 && lines.length >= 1 && str.contains("LISTEN")) {
				isServerStarted = true;
				log.info("server is started from remote server");
				return true;
			}
		}
		if (!CommonUtil.isMacOs()) {
			// str = CommonUtil.execCmd("tasklist |findstr
			// node.exe").toString().trim();
			str = CommonUtil.execCmd("netstat -aon|findstr \"4723\"").toString().trim();
			log.info("check whether server is started");
			String[] lines = str.split("\n");

			if (str.length() > 0 && lines.length >= 1 && str.contains("LISTENING")) {
				isServerStarted = true;
				return true;
			}
		} else {
			str = CommonUtil.execCmd("lsof -i tcp:4723").toString().trim();
			String[] lines = str.split("\n");
			if (lines.length >= 1 && lines.length >= 1 && str.contains("LISTEN")) {
				isServerStarted = true;
				return true;
			}
		}
		log.info("server is not started");
		return false;
	}

	public void stopServer(Boolean isRemoteRun) {
		String[] command = null;
		if (isRemoteRun) {
			log.info("stop server:" + RemoteRunCmd.execCmd("/usr/bin/killall -KILL node"));
			return;
		} else if (CommonUtil.isMacOs()) {
			command = new String[] { "/usr/bin/killall", "-KILL", "node" };
		} else {
			command = new String[] { "cmd", "/c", "taskkill", "/F", "/IM", "node.exe" };
		}

		try {
			Runtime.getRuntime().exec(command);
			log.info("Appium server stopped.");
			isServerStarted = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

