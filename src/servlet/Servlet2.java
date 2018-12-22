package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adapter.BuildAuto;
import model.Automobile;
import server.AutoServer;

@WebServlet("/form")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AutoServer auto = new BuildAuto();

		String make = request.getParameter("make/model/year");
		String color = request.getParameter("color");
		String brakes = request.getParameter("brakes");
		String transmission = request.getParameter("transmission");
		String airbag = request.getParameter("airbag");
		String moonroof = request.getParameter("moonroof");

		Automobile a = auto.getAuto(make);
		a.setOptionChoice("Color", color);
		a.setOptionChoice("Transmission", transmission);
		a.setOptionChoice("Brakes", brakes);
		a.setOptionChoice("Side Impact Airbag", airbag);
		a.setOptionChoice("Power Moonroof", moonroof);

		out.println("<HTML>");
		out.println("<body style=\"background-color:powderblue;\">");
		out.println("<HEAD>");
		out.println("<style>\n" + "table, th, td {\n" + "    border: 2px solid black;\n"
				+ "    border-collapse: collapse;\n" + "}\n" + "th, td {\n" + "    padding: 25px;\n" + "}\n"
				+ "</style>");
		out.println("<TITLE>Your personal car configuration</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<TABLE ALIGN=\"CENTER\"  BORDER = 2>");

		out.println("<H1 align=\"center\">Your car configuration</H1>");

		out.println("<TR>");
		out.println(("<TH>" + a.getMake() + a.getModel() + a.getYear()).toString() + "\t</TH>");
		out.println("<TH>Base Price</TH>");
		out.println("<TH> $" + Float.toString(a.getBasePrice()) + "</TH>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("<TH>Color</TH>");
		out.println(("<TH>" + a.getOptionChoice("Color") + "</TH>"));
		out.println(("<TH> $" + a.getOptionChoicePrice("Color")) + "</TH>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("<TH>Brakes </TH>");
		out.println(("<TH>" + a.getOptionChoice("Brakes") + "</TH>"));
		out.println(("<TH> $" + a.getOptionChoicePrice("Brakes")) + "</TH>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("<TH>Transmission</TH>");
		out.println(("<TH>" + a.getOptionChoice("Transmission") + "</TH>"));
		out.println(("<TH>$" + a.getOptionChoicePrice("Transmission")));
		out.println("</TH>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("<TH>Side Impact Airbag</TH>");
		out.println(("<TH>" + a.getOptionChoice("Side Impact Airbag") + "</TH>"));
		out.println(("<TH>$" + a.getOptionChoicePrice("Side Impact Airbag")));
		out.println("</TH>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("<TH>Power Moonroof</TH>");
		out.println(("<TH>" + a.getOptionChoice("Power Moonroof") + "</TH>"));
		out.println(("<TH> $" + a.getOptionChoicePrice("Power Moonroof")) + "</TH>");
		out.println("</TR>");

		out.println("<TR><TR></TR><TR></TR>");
		out.println("<TH>Final price: </TH>");
		out.println("<TH></TH><TH> $" + Float.toString(a.getPrice()));

		out.println("</TABLE>");
		out.println("</BODY>");
		out.println("</HTML>");

	}
}
