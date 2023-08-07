package com.jnet.client;

public class JDownloaderMain {

	public static void main(String[] args) {

		String downloadUrl = null;
		String saveLocation = null;

		if (!(args.length == 1) && !(args.length == 2)) {
			System.err.println("Invalid number of arguements");
			return;
		}

		JDownloaderService downloaderService = new SingleThreadedJDownloaderImpl();

		if (args.length == 1) {
			downloadUrl = args[0];
			downloaderService.downloadFile(downloadUrl);
			
		} else if (args.length == 2) {

			downloadUrl = args[0];
			saveLocation = args[1];
			downloaderService.downloadFile(downloadUrl, saveLocation);
		}

	}
}
