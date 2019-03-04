package adapter;

public interface ChooseOptions {
	public void printCarPrice(String make, String model, String year);
	public void printOptionChoice(String make, String model, String year, String optionSet);
	public void chooseOption(String make, String model, String year, String optionset, String option);
	public void printOptionPrice(String make, String model, String year, String optionSet);

}