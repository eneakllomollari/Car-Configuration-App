package client;

public class ClientDriver {
	public static void main(String args[]) {
		new CarModelOptionsIO("127.0.0.1", 4444).start();
	}
}