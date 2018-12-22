package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Enea Kllomollari
 * @CWID 20303401
 */
public class OptionSet implements Serializable {
	private String name;
	private ArrayList<Option> op;
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOp() {
		return op;
	}

	protected int getOptionSize() {
		return op.size();
	}

	protected void setOptionSize(int size) {
		this.op = new ArrayList<Option>(size);
		for (int i = 0; i < size; i++)
			this.op.add(i, new Option());
	}

	protected void setOp(ArrayList<Option> op) {
		this.op = op;
	}

	protected void print() {
		System.out.printf("Option Set Name: %s\n", this.name);
		for (int i = 0; i < this.op.size(); i++)
			this.op.get(i).print();
	}

	protected Option findoption(String name) {
		for (int i = 0; i < this.op.size(); i++) {
			if (this.op.get(i).getName().equals(name)) {
				return this.op.get(i);
			}
		}
		System.out.printf("\nOption not found!\n");
		return null;
	}

	protected Option getOptionChoice(int i) {
		return this.op.get(i);
	}

	protected void setOptionChoice(int i, String optionName) {
		this.op.get(i).setName(optionName);
	}

	protected boolean deleteoption(String name) {
		for (int i = 0; i < this.op.size(); i++) {
			if (this.op.get(i).getName().equals(name)) {
				this.op.remove(i);
				return true;
			}
		}
		return false;
	}

	protected boolean updateOption(String oname, String nname) {
		for (int i = 0; i < this.op.size(); i++) {
			if (this.op.get(i).getName().equals(oname)) {
				this.op.get(i).setName(nname);
				return true;
			}
		}
		return false;
	}

}