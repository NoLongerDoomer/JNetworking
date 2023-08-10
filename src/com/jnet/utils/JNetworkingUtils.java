package com.jnet.utils;

import java.net.URL;

/**
 * Utility class for networking project
 * 
 * @author Swarup Singh Rajpurohit
 *
 */
public class JNetworkingUtils {
	
	private JNetworkingUtils() {}

	public static String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}

	public static String getFileNameFromUrl(URL url) {
		String path = url.getPath();

		return getFileNameFromUrl(path);
	}

	public static String getFileNameFromUrl(String url) {
		int lastSlash = url.lastIndexOf("/");

		if (lastSlash > 0)
			return url.substring(lastSlash + 1);
		else
			return "file";
	}

}
