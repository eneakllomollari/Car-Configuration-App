package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import exception.AutoException;
import model.Automobile;

public class FileIO {
	@SuppressWarnings("resource")
	public Automobile buildAuto(String fname) throws AutoException {
		Automobile a = new Automobile();
		BufferedReader buff = null;
		FileReader fR = null;
		try {
			fR = new FileReader(fname);
			buff = new BufferedReader(fR);
			boolean eof = false;
			boolean DEBUG = false;
			int i = 0;
			int optionsetindex = 0;
			int optionindex = 0;
			while (!eof) {
				String line = buff.readLine();
				if (DEBUG)
					System.out.println(line);
				if (line == null)
					eof = true;
				else {
					if (i == 0)
						this.initializegeneraldata(line, a);
					else if (i == 1 || i == 12 || i == 15 || i == 19 || i == 22) {
						this.buildoptionset(optionsetindex, line, a);
						optionsetindex++;
						optionindex = 0;
						if (DEBUG)
							a.print();
						if (optionsetindex > a.getOpSet().size())
							throw new AutoException(5, "Too many option sets in file");
					} else {
						this.buildoption(optionsetindex - 1, optionindex, line, a);
						optionindex++;
						if (DEBUG)
							a.print();
					}
				}
				i++;
			}
			buff.close();
		} catch (NullPointerException e) {
			throw new AutoException(1, "Null Pointer Exception");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new AutoException(2, "Array Index Out Of Bounds Exception");
		} catch (FileNotFoundException e) {
			throw new AutoException(3, "File Not Found Exception");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			throw new AutoException(11, "Invalid format error");
		}
		return a;
	}

	public void writeToDisk(Automobile a) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(a.getModel() + ".ser"));
			out.writeObject(a);
			out.close();
		} catch (IOException e) {
			System.out.printf("Exception -- %s", e.toString());
		}
	}

	public Automobile readFromDisk(String fname) {
		Automobile a = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname));
			a = (Automobile) in.readObject();
			in.close();
		} catch (IOException e) {
			System.out.printf("Exception -- %s", e.toString());
		} catch (ClassNotFoundException e) {
			System.out.printf("Exception -- %s", e.toString());
		}
		return a;
	}

	public Automobile buildAuto(Properties props) {
		Automobile auto = new Automobile();
		auto.setMake(props.getProperty("CarMake"));
		auto.setModel(props.getProperty("CarModel"));
		auto.setYear(props.getProperty("CarYear"));
		auto.setBasePrice(Float.parseFloat(props.getProperty(("CarBasePrice"))));
		auto.setOpSetSize(Integer.parseInt(props.getProperty("CarOptionSetCount")));
		auto.setChoiceSize(Integer.parseInt(props.getProperty("CarOptionSetCount")));
		for (int i = 0; i < auto.getOpSet().size(); i++) { // i is option set index
			String opsetname = props.getProperty("OptionSet" + Integer.toString(i + 1));
			int opsetsize = Integer.parseInt(props.getProperty("OptionSetSize" + Integer.toString(i + 1)));
			auto.setoptsetname(i, opsetname);
			auto.setOptionSize(i, opsetsize);
			for (int k = 0; k < opsetsize; k++) { // k is option index
				char ch = chooseoptionchar(k);
				String optionname = props.getProperty("OptionName" + Integer.toString(i + 1) + Character.toString(ch));
				float price = Float.parseFloat(
						props.getProperty("OptionPrice" + Integer.toString(i + 1) + Character.toString(ch)));
				auto.setOption(i, k, optionname, price);
			}
		}
		return auto;
	}

	/**
	 * Initializes the Automobile object with the general data
	 * 
	 * @param l the line that hold the data to be used for initializing separated by
	 *          delimiter ","
	 * @param a the Automobile object that will be assigned with the data in the
	 *          line
	 * @throws AutoException
	 * 
	 */
	private void initializegeneraldata(String l, Automobile a) {
		StringTokenizer tokenizer = new StringTokenizer(l);

		a.setMake(tokenizer.nextToken(","));
		a.setModel(tokenizer.nextToken(","));
		a.setYear(tokenizer.nextToken(","));
		float basePrice = Float.parseFloat(tokenizer.nextToken(","));
		int optionsetsize = Integer.parseInt(tokenizer.nextToken(","));
		try {
			if (optionsetsize <= 0) {
				throw new AutoException(6, "Invalid number of option set size");
			}
			a.setOpSetSize(optionsetsize);
			a.setChoiceSize(optionsetsize); // choice size is the same as option set
		} catch (AutoException e) {
			optionsetsize = (int) e.fix(e.getErrorno());
			a.setOpSetSize(optionsetsize);
			a.setChoiceSize(optionsetsize);
		}
		try {
			if (basePrice < 0)
				throw new AutoException(9, "Base price negative");
			a.setBasePrice(basePrice);
		} catch (AutoException e) {
			a.setBasePrice((float) e.fix(e.getErrorno()));
		}
	}

	/**
	 * Initializes the Automobile object with the right OptionSet
	 * 
	 * @param index the index of the option set object that has be built inside the
	 *              Automobile object
	 * @param l     the line that hold the data with the size of the name of the
	 *              option set
	 * @param a     the automotive object that will be assigned the data of the line
	 */
	private void buildoptionset(int index, String l, Automobile a) {
		StringTokenizer tok = new StringTokenizer(l);
		String opsetname = tok.nextToken(",");
		a.setoptsetname(index, opsetname);
		int optionsize = Integer.parseInt(tok.nextToken(","));
		a.setOptionSize(index, optionsize);
	}

	/**
	 * Initializes the Automobile object with the Option properties
	 * 
	 * @param optionsetindex the index of the option set inside the auto object that
	 *                       is to be build
	 * @param optionindex    the index of the option object inside the optionset
	 *                       object, which is inside the automotive object
	 * @param l              the line that contains the name and price of the option
	 *                       separated by a comma "," delimiter
	 * @param a              the automotive object that is to be initialized
	 */
	private void buildoption(int optionsetindex, int optionindex, String l, Automobile a) {
		StringTokenizer tokenizer = new StringTokenizer(l);
		float price = 0.0f;
		try {
			String name = tokenizer.nextToken(",");
			price = Float.parseFloat(tokenizer.nextToken(","));
			if (name == null)
				throw new AutoException(7, "Invalid option name");
			a.setOption(optionsetindex, optionindex, name, price);
		} catch (AutoException e) {
			a.setOption(optionsetindex, optionindex, (String) e.fix(e.getErrorno()), price);
		} finally {
		}
	}

	private char chooseoptionchar(int k) {
		switch (k) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		case 8:
			return 'i';
		case 9:
			return 'j';
		case 10:
			return 'k';
		case 11:
			return 'l';
		case 12:
			return 'm';
		case 13:
			return 'n';
		case 14:
			return 'o';
		case 15:
			return 'p';
		case 16:
			return 'q';
		case 17:
			return 'r';
		case 18:
			return 's';
		case 19:
			return 't';
		case 20:
			return 'u';
		case 21:
			return 'v';
		case 22:
			return 'w';
		case 23:
			return 'x';
		case 24:
			return 'y';
		case 25:
			return 'z';
		}
		return 0;
	}
}
