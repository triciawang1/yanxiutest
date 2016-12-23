package com.yanxiu.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotUtil {

	private static final String baseDir = System.getProperty("user.dir");
	private static final File actualDir = new File(baseDir,"actual");
	private static final File expectedDir = new File(baseDir,"expected");
	private static final File failedDir = new File(baseDir,"fail");
	
	
	public static boolean hasDiff(String fileName,int statusBarHeight,int height,int width) throws IOException{
		File actualImage = new File(actualDir,fileName);
		File expectedImage = new File(expectedDir,fileName);
		Screenshot actualScreenshot = new Screenshot(getSubImage(actualImage,statusBarHeight,height,width));
		Screenshot expectedScreenshot = new Screenshot(getSubImage(expectedImage,statusBarHeight,height,width));
		ImageDiff diff = new ImageDiffer().makeDiff(actualScreenshot, expectedScreenshot);
		if(diff.hasDiff()){
			BufferedImage diffImage = diff.getMarkedImage();
			ImageIO.write(diffImage , "png", new File(failedDir,fileName+".png"));
			return true;
		}

		return false;
	}
	
	private static BufferedImage getSubImage(File image,int statusBarHeight,int height,int width) throws IOException{
		return ImageIO.read(image).getSubimage(0, statusBarHeight, width, height-statusBarHeight);
	}
}
