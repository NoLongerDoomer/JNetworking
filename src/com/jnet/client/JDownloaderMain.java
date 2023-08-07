package com.jnet.client;

/**
 * Main entry point to run Jdownload Manager that downloads a remote file and
 * saves it to a location (if saved location passed otherwise saves to current
 * directory)
 * 
 * @author Swarup Singh Rajpurohit
 *
 */
public class JDownloaderMain {

	public static void main(String[] args) {

		String downloadUrl = null;
		String saveLocation = null;

		// return is number of arguements are invalid
		if (!(args.length == 1) && !(args.length == 2)) {
			System.err.println("Invalid number of arguements");
			return;
		}

		// temporarily uses single thread downloader implementation
		JDownloaderService downloaderService = new SingleThreadedJDownloaderImpl();

		if (args.length == 1) {
			downloadUrl = args[0];

			// downloads and saves to current location
			downloaderService.downloadFile(downloadUrl);

		} else if (args.length == 2) {

			downloadUrl = args[0];
			saveLocation = args[1];

			// downloads and saved to location passed as arguement
			downloaderService.downloadFile(downloadUrl, saveLocation);
		}

	}
}
