package exception;

import java.util.Scanner;

public class Fix1to20 {

	public Object fix1(int errorno) {
		return null;
	}

	public Object fix2(int errorno) {
		return null;
	}

	public Object fix3(int errorno) {
		return null;
	}

	public Object fix4(int errorno) {
		return null;
	}

	public Object fix5(int errorno) {
		return null;
	}

	public Object fix6(int errorno) {
		System.out.printf("\n***There was an error reading the option set size\n***Please \nEnter the correct size:");
		Scanner key = new Scanner(System.in);
		int size = key.nextInt();
		while (size <= 0) {
			System.out.printf("Please \nEnter a valid positive number: ");
			size = key.nextInt();
		}
		key.close();
		return size;
	}

	public Object fix7(int errorno) {
		return null;
	}

	public Object fix8(int errorno) {
		return null;
	}

	public Object fix9(int errorno) {
		System.out.printf("\n***There was  an error reading the price of the car.\n***Please \nEnter the base price: ");
		Scanner key = new Scanner(System.in);
		float f = key.nextFloat();
		while (f <= 0.0f) {
			System.out.printf("Please \nEnter a valid positive number: ");
			f = key.nextFloat();
		}
		key.close();
		return f;
	}

	public Object fix10(int errorno) {
		return null;
	}

	public Object fix11(int errorno) {
		return null;
	}

	public Object fix12(int errorno) {
		return null;
	}

	public Object fix13(int errorno) {
		return null;
	}

	public Object fix14(int errorno) {
		return null;
	}

	public Object fix15(int errorno) {
		return null;
	}

	public Object fix16(int errorno) {
		return null;
	}

	public Object fix17(int errorno) {
		return null;
	}

	public Object fix18(int errorno) {
		return null;
	}

	public Object fix19(int errorno) {
		return null;
	}

	public Object fix20(int errorno) {
		return null;
	}
}