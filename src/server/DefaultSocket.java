package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import adapter.BuildAuto;

public class DefaultSocket {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;

	public DefaultSocket(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		if (this.openconnection()) {
			this.handlesession();
		} else {
			System.out.println("could not open connection in server");
		}
	}

	public boolean openconnection() {
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void handlesession() {
		String command = null;
		AutoServer autointerface = new BuildAuto();
		try {
			do {
				command = (String) in.readObject();
				if (command.equals("UPLOAD")) {
					autointerface.addAutoToLHM(((Properties) in.readObject()));
					out.writeObject("\n\tUpload was successful");
				} else if (command.equals("CONFIG")) {
					if (autointerface.getAutoSize() == 0) {
						out.writeObject(null);
					} else {
						out.writeObject(autointerface.getCarNames()); // write the models
						// read the model chosen, get the auto object, send it to the client
						out.writeObject(autointerface.getAuto((String) in.readObject()));
					}
				} else if (command.equals("QUIT")) {
					this.closesession();
				} else {
					System.out.println("Wrong command was received in server\n");
				}
			} while (!command.equals("QUIT"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closesession() {
		try {
			this.out.close();
			this.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}