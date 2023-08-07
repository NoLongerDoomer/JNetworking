package com.jnet.client;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.jnet.utils.JNetworkingUtils;

/**
 * Single Threaded implementaion of downloading service
 * 
 * @author Swarup Singh Rajpurohit
 *
 */
public class SingleThreadedJDownloaderImpl implements JDownloaderService {

	@Override
	public void downloadFile(String url) {

		// gets current directory
		String currentDirectory = JNetworkingUtils.getCurrentDirectory();
		System.out.println("Passing current directory as " + currentDirectory);
		downloadFile(url, currentDirectory);
	}

	@Override
	public void downloadFile(String downloadUrl, String saveLocation) {
		System.out.println("received URL as " + downloadUrl);
		System.out.println("File save location as + " + saveLocation);
		URL url = null;
		InputStream openStream = null;
		OutputStream outputStream = null;
		try {
			url = new URL(downloadUrl);

			openStream = url.openStream();

			// extracting file name from url
			String fileNameFromUrl = JNetworkingUtils.getFileNameFromUrl(downloadUrl);

			System.out.println("File name extracted from url + " + fileNameFromUrl);

			// file will be written as path + file name
			outputStream = new FileOutputStream(saveLocation + "\\" + fileNameFromUrl);

			byte[] buffer = new byte[4096];
			int bytes_read;

			while ((bytes_read = openStream.read(buffer)) != -1)
				outputStream.write(buffer, 0, bytes_read);

			System.out.println("File saved : " + saveLocation + "\\" + fileNameFromUrl);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing resources to avoid memory leak
			try {
				openStream.close();
				outputStream.close();
				// could be null pointer exception while closing null resources
			} catch (Exception fe) {
			}

		}

	}

}
