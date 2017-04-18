package com.yanxiu.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.testng.log4testng.Logger;

import com.sun.jna.platform.win32.WinBase.PROCESS_INFORMATION;

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
			jsFile = "C:/Users/admin/AppData/Roaming/npm/node_modules/anyproxy/bin.js";
		
		}
		String ruleFilePath = AnyProxy.class.getResource("/").getPath();
		File f = new File(ruleFilePath,ruleFile);
		ruleFile = f.getAbsolutePath();
		
	}
	public AnyProxy() {
		super(serverName, successfullMsg, jsFile, "--port", port, "--rule",  ruleFile);		
	}

	public Boolean isServerStarted() throws IOException{
		return isServerStarted(port);
	}
	
	public void stopServer(){
		log.info("start stop anyproxy");
		String str="";
		if (!CommonUtil.isMacOs()) {
			// str = CommonUtil.execCmd("tasklist |findstr
			// node.exe").toString().trim();
			try {
				str = CommonUtil.execCmd("netstat -aon|findstr \"8001\"").toString().trim();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("check whether server is started");
			String[] lines = str.split("\n");

			if (str.length() > 0 && lines.length >= 1 && str.contains("LISTENING")) {
				for (String line : lines){
					System.out.println(line);
					String[] s = line.split(" ");
					String pid = s[s.length-1];
					log.info("pid:"+pid);
					try {
						CommonUtil.execCmd("taskkill /pid  "+pid+" -F");
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//taskkill /pid  6568 -F
			}
		} else {
			try {
				str = CommonUtil.execCmd("lsof -i tcp:4723").toString().trim();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] lines = str.split("\n");
			if (lines.length >= 1 && lines.length >= 1 && str.contains("LISTEN")) {
				for (String line : lines){
					System.out.println(line);
					String[] s = line.split(" ");
					String pid = s[s.length-1];
					log.info("pid:"+pid);
					try {
						CommonUtil.execCmd("kill  "+ pid);
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		log.info("end stop anyproxy");
	}
}
