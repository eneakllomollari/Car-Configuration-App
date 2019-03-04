package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String make, String model, String year, String oldoptionsetname, String newoptionsetnmae);
	public void updateOptionPrice(String make, String model, String year, String optionname, float newprice);
}