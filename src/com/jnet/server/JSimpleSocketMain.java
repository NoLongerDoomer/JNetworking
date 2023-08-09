package com.jnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JSimpleSocketMain {

	private final static int PORT = 55515;

	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(PORT);

			runningServerLoop: while (true) {
				try (Socket client = socket.accept()) {

					String hostAddress = client.getInetAddress().getHostAddress();

					System.out.println("Connected to : " + hostAddress);

					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
					PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);

					String message;

					while ((message = bufferedReader.readLine()) != null) {
						System.out.println(hostAddress + " sent : " + message);
						printWriter.println("Received : " + message);

						if ("stop_server".equalsIgnoreCase(message)) {
							System.out.println("Received message to shut down from : " + hostAddress);
							printWriter.println("Shutting Down!");
							socket.close();
							break runningServerLoop;
						} else if ("Ping".equalsIgnoreCase(message)) {
							printWriter.println("Pong!");
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
