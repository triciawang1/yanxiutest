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

public class CommonUtil {
	public static void execCmd(String[] cmd){
		try {
			
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream fis=p.getInputStream();    
          
             InputStreamReader isr=new InputStreamReader(fis);    
           
             BufferedReader br=new BufferedReader(isr);    
             String line=null;    
           
            while((line=br.readLine())!=null)    
             {    
                 System.out.println(line);    
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
			System.out.println(commands);
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
		System.out.println(result);
		return result;
	}
	
	public static Map<String,String> getAndroidDevices() throws IOException{
		Map<String,String> devices = new HashMap<String,String>();
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		System.out.println(os);
		String str = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb devices").toString();
		String[] lines = str.split("\n");
		if(lines.length<=1){
			System.out.println("no android device connected");
			
		}
		for(int i=1;i<lines.length;i++){
			if(lines[i].contains("device")){
				lines[i]=lines[i].replaceAll("device","");
				String deviceID = lines[i].trim();
				String deviceModel = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.product.model").toString();
				String deviceBrand = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.product.brand").toString();
				String osVersion = execCmd("/Users/Admin/android-sdk-macosx/platform-tools/adb -s "+deviceID+" shell getprop ro.build.version.release").toString();
				
				String deviceName = deviceModel +" "+deviceBrand;
				devices.put("deviceID", deviceID);
				devices.put("deviceName", deviceName);
				devices.put("osVersion", osVersion);
			}
		}
		
		return devices;
	}
	
	public static Map<String,String> getIOSDevices() throws IOException{
		Map<String,String> devices = new HashMap<String,String>();
		String str = execCmd("idevice_id -l").toString();
		String[] lines = str.split("\n");
		if(lines.length<1){
			System.out.println("no ios device connected");
			
		}
		for(int i=0;i<lines.length;i++){
			devices.put("udid",lines[i]);
		}
		System.out.println(devices);
		return devices;
	}
	
	public static void clearAndroidData() throws IOException{
		String clearCmd = "/Users/Admin/android-sdk-macosx/platform-tools/adb shell pm clear com.yanxiu.gphone.training.teacher";
		execCmd(clearCmd);
	}

}
