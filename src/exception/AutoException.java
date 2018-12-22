package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import adapter.FixAuto;

public class AutoException extends Exception implements FixAuto {
	private int errorno;
	private String errormsg;

	public AutoException(int errorno) {
		this.errorno = errorno;
		this.printmyproblem();
	}

	public AutoException(String errormsg) {
		this.errormsg = errormsg;
		this.printmyproblem();
	}

	public AutoException(int errorno, String errormsg) {
		this.errorno = errorno;
		this.errormsg = errormsg;
		this.printmyproblem();
		this.writetologfile();
	}

	private void writetologfile() {
		try {
			DateFormat d1 = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Date date = new Date();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("errlog.txt", true)));
			writer.println("[" + d1.format(date) + "] " + this.errormsg);
			writer.close();
		} catch (IOException e) {
			System.out.println("IO Error, try restarting the process");
			System.exit(1);
		} finally {
		}
	}

	public int getErrorno() {
		return errorno;
	}

	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Object fix(int errorno) {
		Fix1to20 f1 = new Fix1to20();

		switch (errorno) {
		case 1:
			return f1.fix1(errorno);
		case 2:
			return f1.fix2(errorno);
		case 3:
			return f1.fix3(errorno);
		case 4:
			return f1.fix4(errorno);
		case 5:
			return f1.fix5(errorno);
		case 6:
			return f1.fix6(errorno);
		case 7:
			return f1.fix7(errorno);
		case 8:
			return f1.fix8(errorno);
		case 9:
			return f1.fix9(errorno);
		case 10:
			return f1.fix10(errorno);
		case 11:
			return f1.fix10(errorno);
		case 12:
			return f1.fix10(errorno);
		case 13:
			return f1.fix10(errorno);
		case 14:
			return f1.fix10(errorno);
		case 15:
			return f1.fix10(errorno);
		case 16:
			return f1.fix10(errorno);
		case 17:
			return f1.fix10(errorno);
		case 18:
			return f1.fix10(errorno);
		case 19:
			return f1.fix10(errorno);
		case 20:
			return f1.fix10(errorno);
		}
		return null;
	}

	public void printmyproblem() {
		System.out.printf("\nError #%d\tError message:%s\n", this.errorno, this.errormsg);
	}
}