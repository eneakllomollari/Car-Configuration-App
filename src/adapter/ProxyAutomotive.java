package adapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import exception.AutoException;
import model.Automobile;
import util.FileIO;
import scale.*;

public abstract class ProxyAutomotive {
	private static LinkedHashMap<String, Automobile> a = new LinkedHashMap<String, Automobile>();

	public static LinkedHashMap<String, Automobile> getA() {
		return a;
	}

	public int getAutoSize() {
		return a.size();
	}
	public Automobile getAuto(String s) {
		return a.get(s);
	}

	public static void setA(LinkedHashMap<String, Automobile> a) {
		ProxyAutomotive.a = a;
	}

	public void updateOptionSetName(String make, String model, String year, String optionsetname, String newnmae) {
		a.get(make + model + year).updateOptionSetName(optionsetname, newnmae);
	}

	public void updateOptionPrice(String make, String model, String year, String optionname, float newprice) {
		a.get(make + model + year).updateOptionPrice(optionname, newprice);
	}

	public ArrayList<String> getCarNames() {
		ArrayList<String> arrlist = null;
		if (a.size() == 0) {
			return arrlist;
		} else {
			arrlist = new ArrayList<String>();
			Set<String> set = a.keySet();
			for (String s : set)
				arrlist.add(s);

			return arrlist;
		}

	}

	public void buildAuto(String fname) {
		FileIO f = new FileIO();
		Automobile temp = null;
		try {
			temp = f.buildAuto(fname);
		} catch (AutoException e) {
			e.printStackTrace();
		}
		String key = temp.getMake() + temp.getModel() + temp.getYear();
		a.put(key, temp);

	}

	public void sendAuto(ObjectOutputStream o, String key) {
		Automobile auto = ProxyAutomotive.a.get(key);
		try {
			o.writeObject(auto);
			o.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addAutoToLHM(Properties p) {
		FileIO f = new FileIO();
		Automobile temp = f.buildAuto(p);
		String key = temp.getMake() + temp.getModel() + temp.getYear();
		a.put(key, temp);
	}

	public void printAuto(String make, String model, String year) {
		a.get(make + model + year).print();
	}

	public void chooseOption(String make, String model, String year, String optionset, String option) {
		a.get(make + model + year).setOptionChoice(optionset, option);
	}

	public void printCarPrice(String make, String model, String year) {
		System.out.printf("The price of your %s %s %s is $%.2f!\n", year, make, model,
				a.get(make + model + year).getPrice());
	}

	public void printOptionChoice(String make, String model, String year, String optionSet) {
		System.out.printf("You choose %s to be your %s!\n", a.get(make + model + year).getOptionChoice(optionSet),
				optionSet);
	}

	public void printOptionPrice(String make, String model, String year, String option) {
		System.out.printf("The price of your %s is $%.2f!\n", option,
				a.get(make + model + year).getOptionChoicePrice(option));
	}

	public void operation(int opnum, int threadNo, ArrayList<String> input) {
		EditOption ep = new EditOption(opnum, threadNo, input);
		EditOption ep1 = new EditOption(opnum, threadNo + 1, input);

		ep.start();
		ep1.start();
	}
}