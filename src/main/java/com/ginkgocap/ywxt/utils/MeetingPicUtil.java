package com.ginkgocap.ywxt.utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeetingPicUtil {

	private static final Logger logger = LoggerFactory.getLogger(MeetingPicUtil.class);
	public static BufferedImage getBufferedImage(String imgUrl) {
		URL url = null;
		InputStream is = null;
		BufferedImage img = null;
		try {
			url = new URL(imgUrl);
			is = url.openStream();
			img = ImageIO.read(is);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return img;
	}
}
