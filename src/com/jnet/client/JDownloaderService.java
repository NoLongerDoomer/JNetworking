package com.jnet.client;

public interface JDownloaderService {

	void downloadFile(String url);

	void downloadFile(String url, String saveLocation);
}
