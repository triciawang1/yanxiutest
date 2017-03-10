package com.yanxiu.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;

import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotUtil {

	private static final String baseDir = System.getProperty("user.dir");
	private static final File actualDir = new File(baseDir,"actual");
	private static final File expectedDir;
	private static final File failedDir = new File(baseDir,"fail");
	
	static{
		if(CommonUtil.isMacOs()){
			expectedDir = new File(new File(baseDir).getParentFile(),"expected");
			System.out.println("expected dir is:"+expectedDir);
		}else{
		expectedDir = new File(baseDir,"expected");
		}
	}
	
	public static boolean hasDiff(String fileName) throws IOException{
		File actualImage = new File(actualDir,fileName);
		System.out.println("actualImage:"+actualImage.toString());
		File expectedImage = new File(expectedDir,fileName);
		Screenshot actualScreenshot = new Screenshot(ImageIO.read(actualImage));
		Screenshot expectedScreenshot = new Screenshot(ImageIO.read(expectedImage));
		ImageDiff diff = new ImageDiffer().makeDiff(actualScreenshot, expectedScreenshot);
		if(diff.hasDiff()){
			BufferedImage diffImage = diff.getMarkedImage();
			ImageIO.write(diffImage , "png", new File(failedDir,fileName));
			return true;
		}

		return false;
	}
	

}
