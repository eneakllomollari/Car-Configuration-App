package server;

import java.util.ArrayList;
import java.util.Properties;
import model.Automobile;

public interface AutoServer {
	public void addAutoToLHM(Properties out);
	public int getAutoSize();
	public ArrayList<String> getCarNames();
	public Automobile getAuto(String a);
}
