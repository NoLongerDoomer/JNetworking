package com.jnet.server.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import com.jnet.server.beans.HttpHeaders;

public class FileSender {

	public static void sendFile(Socket socket, String filePath) {
		File file = new File(filePath);

		try (FileInputStream fileInputStream = new FileInputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream())) {

			if (file.exists() && file.isFile()) {

				StringBuilder builder = new StringBuilder();
				builder.append(HttpHeaders.HTTP_OK);
				builder.append(HttpHeaders.CONTENT_DISPOSITION_FILE).append(file.getName()).append("\r\n");
				builder.append(HttpHeaders.CONTENT_LENGTH).append(file.length()).append("\r\n");
				builder.append(HttpHeaders.CONTENT_TYPE).append(HttpHeaders.APPLICATION_OCTET).append("\r\n\r\n");

				String headers = builder.toString();

				bufferedOutputStream.write(headers.getBytes());

				byte[] buffer = new byte[4096];
				int bytesRead;

				while ((bytesRead = fileInputStream.read(buffer)) != -1) {
					bufferedOutputStream.write(buffer, 0, bytesRead);
				}

				bufferedOutputStream.flush();
				bufferedOutputStream.close();

			} else {
				String fileNotFoundResponse = "Http/1.1 404 Not Found\r\n\r\n";
				bufferedOutputStream.write(fileNotFoundResponse.getBytes());
				bufferedOutputStream.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
