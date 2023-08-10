package com.jnet.server.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import com.jnet.server.beans.FileDetails;
import com.jnet.server.beans.HttpRequest;
import com.jnet.server.enums.HttpMethod;
import com.jnet.server.helpers.RequestParser;
import com.jnet.server.helpers.ResponseBuilder;

public class ClientHandler implements Runnable {
	private String rootDirectory;
	private Socket socket;

	public ClientHandler(Socket socket, String rootDirectory) {
		this.rootDirectory = rootDirectory;
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

			String request = bufferedReader.readLine();
			
			System.out.println("Received Request : " + request + " From " + socket.getLocalAddress().getHostAddress());
			
			HttpRequest httpRequest = RequestParser.parse(request);
			
			if (httpRequest != null && httpRequest.getHttpMethod() == HttpMethod.GET) {
				String requestedPath = httpRequest.getRequestDir();

				if (requestedPath.startsWith("/download/")) {
					String fileName = requestedPath.substring("/download/".length());
					FileSender.sendFile(socket, rootDirectory + "/" + fileName);
				} else {
					FileManager fileManager = new FileManager(rootDirectory);
					if (fileManager.isDirectory(requestedPath)) {
						List<FileDetails> filesInRequestedPath = fileManager.listFile(requestedPath);
						String htmlResponse = ResponseBuilder.buildDirectoryResponse(requestedPath, filesInRequestedPath);

						bufferedWriter.write(htmlResponse);
						bufferedWriter.flush();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
