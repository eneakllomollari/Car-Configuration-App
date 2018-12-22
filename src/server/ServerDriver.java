package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import adapter.BuildAuto;

public class ServerDriver {
	public static void main(String args[]) {
		Properties[] props = new Properties[2];
		props[0] = new Properties();
		props[1] = new Properties();
		try {
			props[0].load(
					new FileInputStream("/home/enea/Documents/Java Playground/Lab 5/src/Mazda Protege5 Properties"));
			props[1].load(new FileInputStream("/home/enea/Documents/Java Playground/Lab 5/src/Ford Wagon Properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AutoServer as = new BuildAuto();
		as.addAutoToLHM(props[0]);
		as.addAutoToLHM(props[1]);

		new Server().run();
	}
}