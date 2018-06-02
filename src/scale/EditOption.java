package scale;

import java.util.ArrayList;

import adapter.ProxyAutomotive;
import exception.AutoException;

public class EditOption extends ProxyAutomotive implements Runnable {
	private int opnum;
	private int threadno;
	private ArrayList<String> input;
	private Thread thread;

	public EditOption(int opnum, int threadno, ArrayList<String> input) {
		this.opnum = opnum;
		this.input = input;
		this.threadno = threadno;
		this.thread = new Thread(this);
	}
	public void start() {
		thread.start();
	}
	public void run() {
		try {
			System.out.printf("Starting operation %d!\n", this.opnum);
			System.out.printf("\nStarting thread #%d!\n", this.threadno);
			this.ops(opnum, input);
			System.out.printf("Stopping operation #%d!\n", this.opnum);
			System.out.printf("Stopping thread #%d!\n", this.threadno);
		} catch (AutoException ae) {
			ae.fix(ae.getErrorno());
		}
	}

	/*
	 * opnum = 1 print auto input = make, model, year opnum = 2 print car price
	 * input = make, model, year opnum = 3 print option choice input = make,
	 * model,year, optionset opnum = 4 print option price input = make, model, year,
	 * optionset opnum = 5 update option price input = make, model, year,
	 * optionname, newprice opnum = 6 choose option input = make, model, year,
	 * optionset, option opnum = 7 update option set name input = make, model,
	 * year,optionsetname, newname
	 */
	synchronized public void ops(int opnum, ArrayList<String> input) throws AutoException {
		String make = input.get(0);
		String model = input.get(1);
		String year = input.get(2);

		switch (this.opnum) {
		case 1:
			this.printAuto(make, model, year);
			break;
		case 2:
			this.printCarPrice(make, model, year);
			break;
		case 3:

			this.printOptionChoice(make, model, year, input.get(3));
			break;
		case 4:
			this.printOptionPrice(make, model, year, input.get(3));
			break;
		case 5:
			this.updateOptionPrice(make, model, year, input.get(3), Float.parseFloat(input.get(4)));
			break;
		case 6:
			this.chooseOption(make, model, year, input.get(3), input.get(4));
			break;
		case 7:
			this.updateOptionSetName(make, model, year, input.get(3), input.get(4));
			break;
		default:
			throw new AutoException(20, "Invalid opnum passed in ops()");
		}

	}
}