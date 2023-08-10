package com.jnet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.jnet.server.services.ClientHandler;

public class JServerSocketManager {

	private int port;
	private String rootDirectory;

	public JServerSocketManager(int port, String rootDirectory) {
		super();
		this.port = port;
		this.rootDirectory = rootDirectory;
	}

	public void startServer() {
		try (ServerSocket socket = new ServerSocket(port)) {

			System.out.println("Start Server, listerning to port : " + port);

			while (true) {
				Socket clientSocket = socket.accept();

				ClientHandler clientHandler = new ClientHandler(clientSocket, rootDirectory);

				Thread thread = new Thread(clientHandler);
				thread.start();
			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

}
