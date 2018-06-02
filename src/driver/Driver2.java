package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;

public class Driver2 {

	public static void main(String[] args) {

		/*
		 * Scanner keyboard = new Scanner(System.in);
		 * System.out.printf("\nEnter the file name path: "); String fname =
		 * keyboard.nextLine(); keyboard.close();
		 */
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("/home/enea/Documents/Java Workspace/Lab 3/src/Mazda Wagon", "");
		a1.printAuto("Mazda Focus", "Wagon ZTW", "2003");

		// the option set name "Color" will be updated to "NewColor"
		// the price will be updated from $400 to $99.99
		UpdateAuto a2 = new BuildAuto();
		a2.updateOptionSetName("Mazda Focus", "Wagon ZTW", "2003", "Color", "NewColor");
		a2.updateOptionPrice("Mazda Focus", "Wagon ZTW", "2003", "ABS", 99.99f);

		a1.printAuto("Mazda Focus", "Wagon ZTW", "2003");
	}
}