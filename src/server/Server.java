package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serversocket;

	public Server() {
		try {
			serversocket = new ServerSocket(8080);
			System.out.println("Server is now running and waiting for connections\n");
		} catch (IOException e) {
			System.out.println("Server is already running\n");
		}
	}

	public void run() {
		DefaultSocket defserver = null;
		while (true) {
			try {
				Socket socket = serversocket.accept(); // waits for connections
				defserver = new DefaultSocket(socket);
				defserver.run();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
