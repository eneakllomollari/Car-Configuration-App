<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="adapter.*"%>
<%@page import="server.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body style="background-color: silver;">
<head>
<style>
table, th, td {
	border: 2px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 30px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Configuration page</title>
</head>
<body>
	<%
		AutoServer auto = new BuildAuto();
		Properties props = new Properties();
		Properties props1 = new Properties();
		Properties props2 = new Properties();
		try {
			props.load(
					new FileReader("/home/enea/Documents/Tomcat Java Workspace/Lab 6/src/Ford Focus Properties"));
			auto.addAutoToLHM(props);
			props1.load(
					new FileReader("/home/enea/Documents/Tomcat Java Workspace/Lab 6/src/Ford Wagon Properties"));
			auto.addAutoToLHM(props1);
			props2.load(new FileReader(
					"/home/enea/Documents/Tomcat Java Workspace/Lab 6/src/Mazda Protege5 Properties"));
			auto.addAutoToLHM(props2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> models = auto.getCarNames();
	%>
	<form method="post" action="form">
		<table align="center">
			<tr>
				<th>Make/Model</th>
				<th><select name="make/model/year">
						<%
							for (int i = 0; i < models.size(); i++) {
						%>
						<option value=<%=models.get(i).toString()%>><%=models.get(i)%></option>
						<%
							}
						%>
				</select></th>
			</tr>
			<tr>
				<th>Color</th>
				<th><select name="color" size=5>
						<option value="Fort Knox Gold Clearcoat Metallic" selected>Fort
							Knox Gold Clearcoat Metallic</option>
						<option value="Liquid Grey Clearcoat Metallic">Liquid
							Grey Clearcoat Metallic</option>
						<option value="Infra-Red Clearcoat">Infra-Red Clearcoat</option>
						<option value="Grabber Green Clearcoat Metallic">Grabber
							Green Clearcoat Metallic</option>
						<option value="Sangria Red Clearcoat Metallic">Sangria
							Red Clearcoat Metallic</option>
						<option value="French Blue Clearcoat Metallic">French
							Blue Clearcoat Metallic</option>
						<option value="Twilight Blue Clearcoat Metallic">Twilight
							Blue Clearcoat Metallic</option>
						<option value="CD Silver Clearcoat Metallic">CD Silver
							Clearcoat Metallic</option>
						<option value="Pitch Black Clearcoat">Pitch Black
							Clearcoat</option>
						<option value="Pitch Black Clearcoat">Pitch Black
							Clearcoat</option>
				</select></th>
			</tr>
			<tr>
				<th>Brakes</th>
				<th><select name="brakes" size=2>
						<option value="ABS" selected>ABS</option>
						<option value="ABS with Adavance Trac">ABS with Advance
							Trac</option>
						<option value="standard">Standard</option>
				</select></th>
			</tr>
			<tr>
				<th>Transmission</th>
				<th><select name="transmission">
						<option value="automatic" selected>Automatic</option>
						<option value="standard">Standard</option>
				</select></th>
			</tr>
			<tr>
				<th>Airbag</th>
				<th><select name="airbag">
						<option value="present" selected>Present</option>
						<option value="notpresent">Not present</option>
				</select></th>
			</tr>
			<tr>
				<th>Power Moon roof</th>
				<th><select name="moonroof">
						<option value="present" selected>Present</option>
						<option value="notpresent">Not present</option>
				</select></th>
			</tr>
			<tr>
				<th></th>
				<th><input type="submit" value="Submit"><input
					type="reset"></th>
			</tr>
		</table>
	</form>
</body>
</html>