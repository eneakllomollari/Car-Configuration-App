package driver;

import adapter.*;

public class Driver5 {

	public static void main(String[] args) {
		String fname1 = "/home/enea/Documents/Java Workspace/Lab 5/src/MazdaProtege5_Properties";
		CreateAuto create = new BuildAuto();
		create.buildAuto(fname1, "Properties");
		create.printAuto("Mazda", "Protege 5", "2003");
	}
}
