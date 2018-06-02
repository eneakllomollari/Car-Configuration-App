package driver;

import java.util.ArrayList;
import java.util.Scanner;

import adapter.*;

public class Driver4 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String fname1 = "/home/enea/Documents/Java Workspace/Lab 3/src/Mazda Protege5";
		String fname2 = "/home/enea/Documents/Java Workspace/Lab 3/src/Ford Wagon";
		CreateAuto ca = new BuildAuto();
		ca.buildAuto(fname2,"");
		ca.buildAuto(fname1,"");
		Scalable scale = new BuildAuto();

		System.out.printf(
				"\t\t\tWelcome to your garage!\n\t\t\nYour cars are shown below!\n\t1.Ford Focus Wagon ZTW\n\t2.Mazda Protege 5\nPlease enter your choice(1 or 2): ");
		int carch = Integer.parseInt(keyboard.nextLine());
		int opchoice;
		do {
			ArrayList<String> in = new ArrayList<String>();
			switch (carch) {
			case 1:
				in.add("Ford");
				in.add("Focus Wagon ZTW");
				in.add("2014");
				break;
			case 2:
				in.add("Mazda");
				in.add("Protege 5");
				in.add("2003");
				break;
			default:
				System.out.printf("Invalid choice %d", carch);
				break;
			}
			System.out.printf(
					"\nPlease choose between the operations below:\n\t1.Print Auto\n\t2.Print Car Price\n\t3.Print Option Choice\n\t4.Print Option Price\n\t5.Update Option Price\n\t6.Choose Option\n\t7.Update Option Set Name\n\t0.Exit\nPlease enter your choice: ");
			opchoice = Integer.parseInt(keyboard.nextLine());
			switch (opchoice) {
			case 1:
			case 2:
				scale.operation(opchoice, 10, in);
				break;
			case 3:
			case 4:
				System.out.printf("\nEnter an option set: ");
				in.add(keyboard.nextLine());
				scale.operation(opchoice, 20, in);
				break;
			case 5:
				System.out.printf("\nEnter an option name and new price: ");
				in.add(keyboard.nextLine());
				in.add(keyboard.nextLine());
				scale.operation(opchoice, 30, in);
				break;
			case 6:
				System.out.printf("\nEnter an option set and an option: ");
				in.add(keyboard.nextLine());
				in.add(keyboard.nextLine());
				scale.operation(opchoice, 40, in);
				break;
			case 7:
				System.out.printf("\nEnter an option set and a new option set: ");
				in.add(keyboard.nextLine());
				in.add(keyboard.nextLine());
				scale.operation(opchoice, 50, in);
				break;
			case 0:
				System.out.printf("\n\t\t\tGoodbye\n");
				break;
			default:
				System.out.println("\nInvalid choice\n");
			}
		} while (opchoice != 0);

		keyboard.close();
	}
}
