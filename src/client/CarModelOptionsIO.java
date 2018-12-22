package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class CarModelOptionsIO implements Runnable {
	private Socket sock;
	private Thread thread;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public CarModelOptionsIO(String host, int port) {
		thread = new Thread(this);
		try {
			this.sock = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CarModelOptionsIO(Socket socket) {
		this.thread = new Thread(this);
		this.sock = socket;
	}

	public void start() {
		thread.start();
	}

	public void run() {
		if (this.openconnection()) {
			this.handleconnection();
			this.closeconnection();
		} else
			System.out.println("\nConnection could not be etablished with server\n");
	}

	public boolean openconnection() {
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			return true;
		} catch (IOException e) {
			System.out.println(e.getCause());
		}
		return false;
	}

	public void handleconnection() {
		int command = 0;
		try {
			do {
				BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				System.out.printf(
						"\n\t\t\t\tWelcome in your garage\n\n\t\t\t1.Configure\n\t\t\t2.Upload\n\t\t\t3.Exit\n\t\t\tEnter your choice: ");

				command = Integer.parseInt(keyboard.readLine());
				if (command == 1) {
					out.writeObject("CONFIG");
					@SuppressWarnings("unchecked")
					ArrayList<String> models = (ArrayList<String>) in.readObject();
					if (models == null) {
						System.out.printf("\n\tYou have not added any car to your garage!\n");
					} else {
						System.out.printf("\nChoose you car from below!\n");
						for (int i = 0; i < models.size(); i++) {
							System.out.printf("\t%d. %s\n", i + 1, models.get(i));
						}
						System.out.printf("\nEnter your choice here: ");
						int choice = Integer.parseInt(keyboard.readLine());

						out.writeObject(models.get(choice - 1));
						Automobile a = (Automobile) in.readObject();
						if (a != null)
							configure(a);
					}
				} else if (command == 2) {
					out.writeObject("UPLOAD");
					System.out.printf("\n\tEnter the properties file path: ");
					String fname = keyboard.readLine();

					Properties props = new Properties();
					props.load(new FileInputStream(fname));
					out.writeObject(props);
			
					String response = (String)in.readObject();
					System.out.println(response);
				} else if (command == 3) {
					out.writeObject("QUIT");
					System.out.println("\n\tClosing...");
				} else {
					System.out.printf("Invalid input entered [%d]", command);
				}
			} while (command != 3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void closeconnection() {
		try {
			this.out.close();
			this.in.close();
			this.sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void configure(Automobile a) {

		System.out.printf("---\nConfiguration menu\n---\n");
		int opsetchoice;
		try {
			do {
				@SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				System.out.printf(
						"1. Color\n2. Transmission\n3. Brakes\n4. Side Impact Airbag\n5. Power Moonroof\n0. EXIT\n\nEnter the option set that you want to configure: \n");
				opsetchoice = keyboard.nextInt();
				if (opsetchoice == 1) {
					this.configureColor(a);
				} else if (opsetchoice == 2) {
					this.configureTransmission(a);
				} else if (opsetchoice == 3) {
					this.configureBrakes(a);
				} else if (opsetchoice == 4) {
					this.configureAirbag(a);
				} else if (opsetchoice == 5) {
					this.configureMoonRoof(a);
				} else if (opsetchoice == 0) {
					break;
				} else {
					System.out.printf("\nInvalid choice. Please make sure to enter a valid choice!\n");
				}
			} while (opsetchoice != 0);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		this.printConfiguredAuto(a);
	}

	private void configureMoonRoof(Automobile a) {
		int choice;
		try {
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			System.out.printf("\nPower Moonroof:");
			a.printOpSet("Power Moonroof");
			System.out.printf("Enter your choice here: ");
			choice = keyboard.nextInt();
			String option = null;
			switch (choice) {
			case 1:
				option = "present";
			case 2:
				option = "notpresent";
			}
			a.setOptionChoice("Power Moonroof", option);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void configureAirbag(Automobile a) {
		int choice;
		try {
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			System.out.printf("\nSide Impact Airbag:");
			a.printOpSet("Side Impact Airbag");
			System.out.printf("Enter your choice here: ");
			choice = keyboard.nextInt();
			String option = null;
			switch (choice) {
			case 1:
				option = "present";
			case 2:
				option = "notpresent";
			}
			a.setOptionChoice("Side Impact Airbag", option);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void configureBrakes(Automobile a) {
		int choice;
		try {
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			a.printOpSet("Brakes");
			System.out.printf("Enter your choice here: ");
			choice = keyboard.nextInt();
			String option = null;
			switch (choice) {
			case 1:
				option = "ABS";
			case 2:
				option = "ABS with Advance Trac";
			case 3:
				option = "standard";
			}
			a.setOptionChoice("Brakes", option);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void configureTransmission(Automobile a) {
		int choice;
		try {
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			a.printOpSet("Transmission");
			System.out.printf("Enter your choice here: ");
			choice = keyboard.nextInt();
			String option = null;
			switch (choice) {
			case 1:
				option = "automatic";
			case 2:
				option = "standard";
			}
			a.setOptionChoice("Transmission", option);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void configureColor(Automobile a) {
		int choice;
		try {
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			System.out.printf("\nColor menu:");
			a.printOpSet("Color");
			System.out.printf("Enter your choice here: ");
			choice = keyboard.nextInt();
			String selectedColor = index2Color(choice);
			a.setOptionChoice("Color", selectedColor);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private String index2Color(int choice) {
		switch (choice) {
		case 1:
			return "Fort Knox Gold Clearcoat Metallic";
		case 2:
			return "Liquid Grey Clearcoat Metallic";
		case 3:
			return "Infra-Red Clearcoat";
		case 4:
			return "Grabber Green Clearcoat Metallic";
		case 5:
			return "Sangria Red Clearcoat Metallic";
		case 6:
			return "French Blue Clearcoat Metallic";
		case 7:
			return "Twilight Blue Clearcoat Metallic";
		case 8:
			return "CD Silver Clearcoat Metallic";
		case 9:
			return "Pitch Black Clearcoat";
		case 10:
			return "Cloud 9 White Clearcoat";
		default:
			return null;
		}
	}

	private void printConfiguredAuto(Automobile a) {
		System.out.printf("\n\t\tYour car configuration is show below!\n");
		System.out.printf(
				"Make: %s\nModel: %s\nYear: %s\nBase Price: %.2f\nColor: \n\t%s\n\t$%.2f\nTransmission:\n\t%s\n\t$%.2f\nBrakes:\n\t%s\n\t$%.2f\nSide Impact Airbag: \n\t%s\n\t$%.2f\nSide Impact Airbag:\n\t%s\n\t$%.2f\n\nFinal Price: $%.2f\n\n",
				a.getMake(), a.getModel(), a.getYear(), a.getBasePrice(), a.getOptionChoice("Color"),
				a.getOptionChoicePrice("Color"), a.getOptionChoice("Transmission"),
				a.getOptionChoicePrice("Transmission"), a.getOptionChoice("Brakes"), a.getOptionChoicePrice("Brakes"),
				a.getOptionChoice("Side Impact Airbag"), a.getOptionChoicePrice("Side Impact Airbag"),
				a.getOptionChoice("Side Impact Airbag"), a.getOptionChoicePrice("Side Impact Airbag"), a.getPrice());
	}
}