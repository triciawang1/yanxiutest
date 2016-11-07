package com.yanxiu.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.log4testng.Logger;

import com.yanxiu.test.cases.BaseCase;

public class CommonUtil {
	private static Logger log = Logger.getLogger(CommonUtil.class);
	public static void execCmd(String[] cmd){
		try {
			
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream fis=p.getInputStream();    
          
             InputStreamReader isr=new InputStreamReader(fis);    
           
             BufferedReader br=new BufferedReader(isr);    
             String line=null;    
           
            while((line=br.readLine())!=null)    
             {    
                 System.out.println("%%%%%%%%%%%%%"+line);    
             }    
		
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static StringBuilder execCmd(String cmd) throws IOException{
		ProcessBuilder builder;
		Process p;
		String os = System.getProperty("os.name");
		if(os.contains("Windows")){
			builder = new ProcessBuilder("cmd.exe","/c",cmd);

		}
		else{
//			builder = new ProcessBuilder("/bin/sh","-c", "'"+cmd+"'");

			List<String> commands = new ArrayList<String>();
			String []str = cmd.split(" ");
			for(int i=0;i<str.length;i++){
				commands.add(str[i]);
			}
			
			builder = new ProcessBuilder(commands);
//			
		}
		builder.redirectErrorStream(true);
		p = builder.start();
//			p = Runtime.getRuntime().exec(cmd);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		StringBuilder result = new StringBuilder();
		while((line=reader.readLine())!=null){
//			System.out.println(line);
			result.append(line+"\n");
		}
		
		return result;
	}
	
	public static Map<String,String> getAndroidDevices(Boolean isRemoteRun) throws IOException{
		Map<String,String> devices = new HashMap<String,String>();
//		Properties prop = System.getProperties();
//		String os = prop.getProperty("os.name");
//		System.out.println(os);
		String str="";
		if(isRemoteRun){
			str =  RemoteRunCmd.execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb devices").toString();
		}
		else if(isMacOs()){
		 str= execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb devices").toString();
		}else{
			str=execCmd("adb devices").toString();
		}
		String[] lines = str.split("\n");
		if(lines.length<=1){
			log.info("no android device connected");
			
		}
		for(int i=1;i<lines.length;i++){
			if(lines[i].contains("device")){
				lines[i]=lines[i].replaceAll("device","");
				String deviceID = lines[i].trim();
				devices.put("deviceID", deviceID);
				if(CommonUtil.isMacOs()){
				String deviceModel = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.product.model").toString();
				String deviceBrand = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.product.brand").toString();
				String osVersion = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.build.version.release").toString();
				
				String deviceName = deviceModel +" "+deviceBrand;
				
				devices.put("deviceName", deviceName);
				devices.put("osVersion", osVersion);
				}
				
			}
		}
		log.info("android device is:"+devices);
		return devices;
	}
	
	public static Map<String,String> getIOSDevices(Boolean isRemoteRun) throws IOException{
		Map<String,String> devices = new HashMap<String,String>();
		String str;
		if(isRemoteRun){
			str = RemoteRunCmd.execCmd("/usr/local/bin/idevice_id -l").toString().trim();
			log.info("remote run idevice_id -l result is:"+str);
		}else if(CommonUtil.isMacOs()){
	    str = execCmd("idevice_id -l").toString();
		}else{
			return devices;
		}
		String[] lines = str.split("\n");
		log.info("legth is :"+lines.length+str.length());
		if(lines.length<1){
			log.info("no ios device connected");
			
		}
		for(int i=0;i<lines.length;i++){
			devices.put("udid",lines[i]);
		}
		log.info("ios deviceid is:"+devices);
		return devices;
	}
	
	public static void clearAndroidData() throws IOException{
		String clearCmd="";
		if(CommonUtil.isMacOs()){
		clearCmd = "/Users/Admin/android-sdk-macosx/platform-tools/adb shell pm clear com.yanxiu.gphone.training.teacher";
		}
		else{
			clearCmd ="adb shell pm clear com.yanxiu.gphone.training.teacher";
		}
		execCmd(clearCmd);
	}
	
	public static boolean isMacOs(){
		
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		log.info("platform is:"+os);
		if(os.equals("Mac OS X")){
			return true;
		}
		
		return false;
	}
	
	public static boolean isWindowsOS(){
		
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		log.info("platform is:"+os);
		if(os.contains("Windows")){
			return true;
		}
		
		return false;
	}

}
