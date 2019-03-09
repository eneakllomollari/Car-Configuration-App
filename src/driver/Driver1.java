package driver;

import java.util.Scanner;
import model.Automobile;
import util.FileIO;

public class Driver1 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		FileIO fileIO = new FileIO();

		try {
			System.out.print("\nEnter the path of the file that contains the data: ");
			String fname = keyboard.nextLine();
			keyboard.close();

			// build the auto object
			Automobile a = fileIO.buildAuto(fname);

			// print the auto object before serializing
			System.out.printf("\nBefore serializing!\n");
			a.print();

			// write the object to disk
			fileIO.writeToDisk(a);

			// deserialize from disk to object
			Automobile a1 = fileIO.readFromDisk("Protege 5.ser");

			// print the deserialized object
			System.out.printf("\nAfter serializing!\n");
			a1.print();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Exception -- %s", e.toString());
		}
	}
}