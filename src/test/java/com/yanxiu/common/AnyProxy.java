package com.yanxiu.common;

import java.io.File;

import org.testng.log4testng.Logger;

public class AnyProxy extends AbstractServer {
	private static Logger log = Logger.getLogger(AnyProxy.class);

	private static String ruleFile = "rule_mock.js";
	private static final String port = "8001";
	private static final String serverName = "Any proxy";
//	private static final String executor = "D:/Program Files/nodejs/node.exe";
	private static final String successfullMsg = "Http proxy started at";
	private static String jsFile;
	
	static{
		
		if (CommonUtil.isMacOs()) {
			jsFile = "/usr/local/lib/node_modules/anyproxy/bin.js";			
		} else if (CommonUtil.isWindowsOS()) {
			jsFile = "C:/Users/Administrator/AppData/Roaming/npm/node_modules/anyproxy/bin.js";
		
		}
		String ruleFilePath = AnyProxy.class.getResource("/").getPath();
		File f = new File(ruleFilePath,ruleFile);
		ruleFile = f.getAbsolutePath();
		
	}
	public AnyProxy() {
		super(serverName, successfullMsg, jsFile, "--port", port, "--rule",  ruleFile);		
	}

}
