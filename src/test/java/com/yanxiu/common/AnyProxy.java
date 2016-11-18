package com.yanxiu.common;




public class AnyProxy extends AbstractServer {

	private static final String ruleFile = "C:/Users/Administrator/AppData/Roaming/npm/node_modules/anyproxy/rule_sample/rule_mock.js";
	private static final String port = "8001";
	private static final String serverName = "Any proxy";
	private static final String executor = "D:/Program Files/nodejs/node.exe";
	private static final String successfullMsg = "Http proxy started at";
	private static final String jsFile = "C:/Users/Administrator/AppData/Roaming/npm/node_modules/anyproxy/bin.js";

	public AnyProxy() {
		super(serverName, executor, successfullMsg, jsFile, "--port", port, "--rule", ruleFile);
	}

}
