package com.jnet.server.services.impls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

import com.jnet.server.services.HostFileService;
import com.jnet.server.services.JSocketExposeService;

public class HostFileSingleThreadedImpl implements HostFileService {

	@Override
	public void exposeFileToSocket(final String directory, final int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			while (true) {
				try (Socket socket = serverSocket.accept()) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					String readLine = bufferedReader.readLine();

					if (readLine.startsWith("GET")) {
						System.out.println("Received GET from " + socket.getInetAddress().getHostAddress());
						String[] pathAndProtocol = readLine.split(" ");
						System.out.println("Request Data : " + pathAndProtocol[1]);
						OutputStream outputStream = socket.getOutputStream();
						PrintWriter printWriter = new PrintWriter(outputStream, true);
						JSocketExposeService service = new JSocketExposeServiceImpl();
						if ("/".equalsIgnoreCase(pathAndProtocol[1])) {
							service.writeDirectoryToBrowser(printWriter, directory, null, outputStream);
						} else if ("/exit".equalsIgnoreCase(pathAndProtocol[1])) {
							break;
						} else {
							service.writeDirectoryToBrowser(printWriter, directory,
									URLDecoder.decode(pathAndProtocol[1].substring(1), "UTF-8"), outputStream);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
