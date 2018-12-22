package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.ProxyAutomotive;

public class Driver5 {

	public static void main(String[]args) {
		CreateAuto ca = new BuildAuto();
		ca.buildAuto("/home/enea/Documents/Java Playground/Lab 5/src/Mazda Protege5");
		System.out.println(ProxyAutomotive.getA().size());
	}
}
