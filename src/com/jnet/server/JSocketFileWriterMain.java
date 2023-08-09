package com.jnet.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class JSocketFileWriterMain {
	private final static int PORT = 55515;

	public static void main(String[] args) {
		try {
			
			
			ServerSocket serverSocket = new ServerSocket(PORT);

//			foreverRunningLoop: while (true) {
			try (Socket socket = serverSocket.accept()) {
				OutputStream outputStream = socket.getOutputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				FileInputStream inputStream = new FileInputStream("D:\\misc\\download\\jquery-1.11.3.min.js");

				String request = bufferedReader.readLine();
				System.out.println(Arrays.toString(request.split(" ")));
//				while ((request = bufferedReader.readLine()) != null)
//					System.out.println(request);

				PrintWriter writer = new PrintWriter(outputStream, true);

				writer.println("HTTP/1.1 200 OK");
				writer.println("Content-Type: application/octet-stream");
				writer.println("Content-Disposition: attachment; filename=jquery-1.11.3.min.js");
				writer.println();
				
				File file = new File(request);
				
				byte[] buffer = new byte[4096];
				int bytesRead;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();
				socket.close();

//					break foreverRunningLoop;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				serverSocket.close();
			}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
