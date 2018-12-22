package driver;

import adapter.BuildAuto;
import adapter.ChooseOptions;
import adapter.CreateAuto;
import adapter.UpdateAuto;

public class Driver3 {

	public static void main(String[] args) {
		/*
		 * Scanner keyboard = keyboard;
		 * System.out.printf("\nEnter the file name path: "); String fname =
		 * keyboard.nextLine(); keyboard.close();
		 */
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("/home/enea/Documents/Java Workspace/Lab 3/src/Mazda Protege5");
		a1.buildAuto("/home/enea/Documents/Java Workspace/Lab 3/src/Ford Wagon");

		// the option set name "Color" will be updated to "NewColor"
		// the price will be updated from $400 to $99.99
		UpdateAuto a2 = new BuildAuto();
		a2.updateOptionSetName("Mazda", "Protege 5", "2003", "Color", "NewColor");
		a2.updateOptionSetName("Ford", "Focus Wagon ZTW", "2014", "Color", "NewColor");

		a2.updateOptionPrice("Mazda", "Protege 5", "2003", "ABS", 400.00f);
		a2.updateOptionPrice("Ford", "Focus Wagon ZTW", "2014", "ABS", 400.00f);

		ChooseOptions a3 = new BuildAuto();

		// the first car
		a1.printAuto("Mazda", "Protege 5", "2003");

		a3.chooseOption("Mazda", "Protege 5", "2003", "NewColor", "Fort Knox Gold Clearcoat Metallic");
		a3.printOptionChoice("Mazda", "Protege 5", "2003", "NewColor");
		a3.printOptionPrice("Mazda", "Protege 5", "2003", "NewColor");

		a3.chooseOption("Mazda", "Protege 5", "2003", "Transmission", "standard");
		a3.printOptionChoice("Mazda", "Protege 5", "2003", "Transmission");
		a3.printOptionPrice("Mazda", "Protege 5", "2003", "Transmission");

		a3.chooseOption("Mazda", "Protege 5", "2003", "Brakes", "ABS");
		a3.printOptionChoice("Mazda", "Protege 5", "2003", "Brakes");
		a3.printOptionPrice("Mazda", "Protege 5", "2003", "Brakes");

		a3.chooseOption("Mazda", "Protege 5", "2003", "Side Impact Airbag", "notpresent");
		a3.printOptionChoice("Mazda", "Protege 5", "2003", "Side Impact Airbag");
		a3.printOptionPrice("Mazda", "Protege 5", "2003", "Side Impact Airbag");

		a3.chooseOption("Mazda", "Protege 5", "2003", "Power Moonroof", "notpresent");
		a3.printOptionChoice("Mazda", "Protege 5", "2003", "Power Moonroof");
		a3.printOptionPrice("Mazda", "Protege 5", "2003", "Power Moonroof");

		a3.printCarPrice("Mazda", "Protege 5", "2003");

		// the second car
		a1.printAuto("Ford", "Focus Wagon ZTW", "2014");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "NewColor", "Fort Knox Gold Clearcoat Metallic");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "NewColor");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "NewColor");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Transmission", "standard");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Transmission");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Transmission");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Brakes", "ABS");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Brakes");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Brakes");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag", "notpresent");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof", "notpresent");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof");

		a3.printCarPrice("Ford", "Focus Wagon ZTW", "2014");

	}
}