package adapter;

public interface ChooseOptions {
	void printCarPrice(String make, String model, String year);
	void printOptionChoice(String make, String model, String year, String optionSet);
	void chooseOption(String make, String model, String year, String optionset, String option);
	void printOptionPrice(String make, String model, String year, String optionSet);
}