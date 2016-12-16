package com.yanxiu.common;

import java.io.IOException;
import java.net.InetAddress;

import org.testng.log4testng.Logger;



public class WifiProxy {
	private static Logger log = Logger.getLogger(WifiProxy.class);
	private static String ipAddress;
	private static String port;

	static{
		log.info("static initialize block");
		port = "8001";
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
			ipAddress = ia.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setWifiProxy() throws IOException {
		log.info("start to set wifi proxy to:"+ipAddress+":"+port);
		String setCmd = "adb shell am start -n tk.elevenk.proxysetter/.MainActivity -e host " + ipAddress + " -e port "
				+ port + " -e ssid SRT -e key www.yanxiu.com";
		CommonUtil.execCmd(setCmd);
	}

	public static void clearWifiProxy() throws IOException {
		log.info("start to clear wifi proxy");
		String clearCmd = "adb shell am start -n tk.elevenk.proxysetter/.MainActivity -e ssid SRT -e clear true -e key www.yanxiu.com";
		CommonUtil.execCmd(clearCmd);
	}

}
