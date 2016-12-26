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
	private static final File expectedDir = new File(baseDir,"expected");
	private static final File failedDir = new File(baseDir,"fail");
	
	
	public static boolean hasDiff(String fileName,Rectangle container) throws IOException{
		File actualImage = new File(actualDir,fileName);
		File expectedImage = new File(expectedDir,fileName);
		Screenshot actualScreenshot = new Screenshot(getSubImage(actualImage,container));
		Screenshot expectedScreenshot = new Screenshot(getSubImage(expectedImage,container));
		ImageDiff diff = new ImageDiffer().makeDiff(actualScreenshot, expectedScreenshot);
		if(diff.hasDiff()){
			BufferedImage diffImage = diff.getMarkedImage();
			ImageIO.write(diffImage , "png", new File(failedDir,fileName+".png"));
			return true;
		}

		return false;
	}
	
	private static BufferedImage getSubImage(File image,Rectangle container) throws IOException{
		return ImageIO.read(image).getSubimage(container.x, container.y, container.width, container.width);
	}
}
