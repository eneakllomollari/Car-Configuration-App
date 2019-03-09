package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.ProxyAutomotive;

public class Driver5 {

	public static void main(String[] args) {
		CreateAuto ca = new BuildAuto();
		ca.buildAuto("/home/enea/Desktop/Car-Configuration-App/Mazda0Protege5");
		System.out.println(ProxyAutomotive.getA().size());
	}
}
