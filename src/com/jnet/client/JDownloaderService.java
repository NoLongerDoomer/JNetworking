package com.jnet.client;

/**
 * Interface for JdownloadService
 * 
 * @author Swarup Singh Rajpurohit
 *
 */
public interface JDownloaderService {

	/**
	 * Downloads and saves file to current directory
	 * 
	 * @param url
	 */
	void downloadFile(String url);

	/**
	 * Dowloads file and saves to location provided
	 * 
	 * @param url
	 * @param saveLocation
	 */
	void downloadFile(String url, String saveLocation);
}
