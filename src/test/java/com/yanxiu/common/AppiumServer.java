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

/**
 * 
 * @author RightQA
 *
 */
public class AppiumServer {
	private Logger log = Logger.getLogger(AppiumServer.class);
	private Boolean isServerStarted = false;
	private static final int SLEEP_TIME_MS = 50;
	private static long TIMEOUT = 20000;
	
	public void startServer(Boolean isRemoteRun) {

		CommandLine command = null;
		if (CommonUtil.isMacOs() || isRemoteRun) {
//			command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
//			command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js",
//					false);
			command = new CommandLine("/usr/local/bin/node");
			command.addArgument("/usr/local/lib//node_modules/appium/build/lib/main.js",
					false);
		} else if (CommonUtil.isWindowsOS() && !isRemoteRun) {
			command = new CommandLine("D:/Program Files (x86)/Appium/node.exe");
			command.addArgument("D:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js", false);
		}
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");
		// command.addArgument("--full-reset", false);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {

			if (!isRemoteRun) {
				log.info("------------------------");				
				log.info("start Appium server.");
				log.info("------------------------");
				
				CollectingLogOutputStream out = new CollectingLogOutputStream();
				PumpStreamHandler psh = new PumpStreamHandler(out);
				executor.setStreamHandler(psh);
				executor.execute(command, resultHandler);
  
				List<String> str = out.getLines();
                long until = System.currentTimeMillis() + TIMEOUT;
                while (!str.contains("Welcome to Appium") && (System.currentTimeMillis() < until)) {
                	Thread.sleep(SLEEP_TIME_MS);
                	str = out.getLines();
                }
               
                for(int i=0;i<str.size();i++){
                	log.info(str.get(i));
                }
                log.info("------------------------------");
                log.info("server is started successfully");
                log.info("------------------------------");
                

                

			} else {
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

		} catch (IOException e) {
			e.printStackTrace();
			log.info("appium started IOException");
		} catch (InterruptedException e) {
			log.info("appium started InterruptedException");
			e.printStackTrace();
		}
	}

	public boolean isServerStarted(Boolean isRemoteRun) throws IOException {
		String str;
		if (isRemoteRun) {
			log.info("check whether server is started from remote server");
			str = RemoteRunCmd.execCmd("lsof -i tcp:4723").toString();
			log.info(str);
			String[] lines = str.split("\n");
			if (str.length() > 0  && lines.length >= 1 && str.contains("LISTEN")){
				isServerStarted = true;
				log.info("server is started from remote server");
				return true;
			}
		}
		if (!CommonUtil.isMacOs()) {
//			str = CommonUtil.execCmd("tasklist |findstr node.exe").toString().trim();
			str = CommonUtil.execCmd("netstat -aon|findstr \"4723\"").toString().trim();
			log.info("check whether server is started");
			String[] lines = str.split("\n");
			
			if (str.length() > 0 && lines.length >= 1 && str.contains("LISTENING")){
				isServerStarted = true;
				return true;
			}
		} else {
			str = CommonUtil.execCmd("lsof -i tcp:4723").toString().trim();
			String[] lines = str.split("\n");
			if (lines.length >= 1 && lines.length >= 1 && str.contains("LISTEN")){
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class CollectingLogOutputStream extends LogOutputStream {
    private final List<String> lines = new LinkedList<String>();
    @Override protected void processLine(String line, int level) {
        lines.add(line);
    }   
    public List<String> getLines() {
        return lines;
    }
}