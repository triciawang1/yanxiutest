package com.yanxiu.common;



import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import java.io.IOException;

/**
 * 
 * @author RightQA
 *
 */
public class AppiumServer {

	public void startServer() {

		CommandLine command=null;
		if(CommonUtil.isMacOs()){
		command = new CommandLine(
				"/Applications/Appium.app/Contents/Resources/node/bin/node");
		command.addArgument(
				"/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js",
				false);
		}else{
			command = new CommandLine(
					"D:/Program Files (x86)/Appium/node.exe");
			command.addArgument(
					"D:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js",
					false);
		}
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");
//		command.addArgument("--full-reset", false);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			System.out.println("Appium server started.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		String[] command=null;
		if(CommonUtil.isMacOs()){
			command = new String[]{ "/usr/bin/killall", "-KILL", "node" };
		}
		else{
			command = new String[]{"cmd","/c","taskkill","/F","/IM","node.exe"};
		}
		
		try {
			Runtime.getRuntime().exec(command);
			System.out.println("Appium server stopped.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}