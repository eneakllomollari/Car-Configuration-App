package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Enea Kllomollari
 * @CWID 20303401
 **/
public class Automobile implements Serializable {

	private static final long serialVersionUID = 1L;
	private float basePrice;
	private String model;
	private String make;
	private String year;
	private ArrayList<Option> choice;
	private ArrayList<OptionSet> opSet;

	public Automobile() {
		this.basePrice = 0.0f;
		this.model = this.make = this.model = this.year = null;
		this.choice = null;
		this.opSet = null;
	}

	public Automobile(float basePrice, String model, String make, String year, ArrayList<Option> choice,
			ArrayList<OptionSet> opSet) {
		this.basePrice = basePrice;
		this.model = model;
		this.make = make;
		this.year = year;
		this.choice = choice;
		this.opSet = opSet;
	}

	public String getOptionChoice(String name) {
		for (int i = 0; i < opSet.size(); i++) {
			OptionSet o = opSet.get(i);
			if (o.getName().equals(name)) {
				return choice.get(i).getName();
			}
		}
		return null;
	}

	public float getOptionChoicePrice(String setName) {
		for (int i = 0; i < opSet.size(); i++) {
			OptionSet o = opSet.get(i);
			if (o.getName().equals(setName)) {
				return choice.get(i).getPrice();
			}
		}
		System.out.printf("Error! Option set was not found");
		return 0.0f;
	}

	public void setOptionChoice(String optionSetName, String optionName) {
		for (int i = 0; i < opSet.size(); i++) {
			OptionSet ops = opSet.get(i);
			if (ops.getName().equals(optionSetName)) {
				Option o = ops.findoption(optionName);
				choice.add(i,o);
				return;
			}
		}
	}

	public float getPrice() {
		float price = 0.0f;
		for (int i = 0; i < choice.size(); i++)
			price += choice.get(i).getPrice();
		return price += this.basePrice;
	}
	
	public void setChoiceSize(int size) {
		this.choice = new ArrayList<Option>(size);
		for(int i = 0; i < size;i++)
			this.choice.add(i,new Option());
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String modelName) {
		this.model = modelName;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public ArrayList<OptionSet> getOpSet() {
		return opSet;
	}

	public void setoptsetname(int i, String name) {
		opSet.get(i).setName(name);
	}

	public void setOption(int opset, int op, String name, float price) {

		this.opSet.get(opset).getOp().get(op).setName(name);
		this.opSet.get(opset).getOp().get(op).setPrice(price);
	}

	public void updateOptionPrice(String optionname, float price) {
		for (int i = 0; i < this.opSet.size(); i++) {
			for (int j = 0; j < this.opSet.get(i).getOp().size(); j++) {
				if (this.opSet.get(i).getOp().get(j).getName().equals(optionname))
					this.opSet.get(i).getOp().get(j).setPrice(price);
			}
		}
	}

	public int getOptionSize(int i) {
		return this.opSet.get(i).getOptionSize();
	}

	/**
	 * Sets this size of the option inside optionset at index i
	 * 
	 * @param i
	 *            the index of the optionset
	 * @param size
	 *            the size of the the option inside the optionset at index i
	 */
	public void setOptionSize(int i, int size) {
		this.opSet.get(i).setOptionSize(size);
	}

	public OptionSet getOpSet(int i) {
		return opSet.get(i);
	}

	public void setOpSet(ArrayList<OptionSet> opSet) {
		this.opSet = opSet;
	}

	/**
	 * Sets the size of the optionsize at index i
	 * 
	 * @param i
	 *            the size of the option set array
	 */
	public void setOpSetSize(int size) {
		this.opSet = new ArrayList<OptionSet>(size);
		for (int i = 0; i < size; i++)
			this.opSet.add(i, new OptionSet());
	}

	public int findoptionset(String name) {
		for (int i = 0; i < this.opSet.size(); i++) {
			if (opSet.get(i).getName().equals(name)) {
				// opSet.get(i).print();
				return i;
			}
		}
		System.out.printf("\nOption Set not found!\n");
		return -1;
	}

	public Option findoption(int i, String name) {
		return this.opSet.get(i).findoption(name);
	}

	public boolean deleteoptionset(String name) {
		for (int i = 0; i < this.opSet.size(); i++) {
			if (this.opSet.get(i).getName().equals(name)) {
				this.opSet = null;
				return true;
			}
		}
		return false;
	}

	public boolean deleteoption(int i, String name) {
		return this.opSet.get(i).deleteoption(name);
	}

	public boolean updateOptionSetName(String oname, String nname) {
		int index = this.findoptionset(oname);
		if (index != -1) {
			this.opSet.get(index).setName(nname);
			return true;
		}
		return false;
	}

	public boolean updateOptionName(String oldOptionSetName, String oname, String nname) {
		int index = this.findoptionset(oldOptionSetName);
		if (index != -1) {
			return this.opSet.get(index).updateOption(oname, nname);
		}
		return false;
	}

	public void print() {
		System.out.printf("\nMake: %s\nModel: %s\nYear: %s\nBase price: $%.2f\n", this.make, this.model, this.year,
				this.basePrice);
		for (int i = 0; i < this.opSet.size(); i++)
			opSet.get(i).print();
	}

	public void printOpSet(String string) {
		int index = this.findoptionset(string);
		this.opSet.get(index).print();
	}

}