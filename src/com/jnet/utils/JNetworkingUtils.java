package com.jnet.utils;

import java.net.URL;

public class JNetworkingUtils {

	public static String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}

	public static String getFileNameFromUrl(String url) {
		int lastSlash = url.lastIndexOf("/");

		if (lastSlash > 0)
			return url.substring(lastSlash + 1);
		else
			return "file";
	}

	public static String getFileNameFromUrl(URL url) {
		String path = url.getPath();

		return getFileNameFromUrl(path);
	}
}
