<%@ page language="java"
	import="java.util.ArrayList, java.util.Iterator"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	String login = (String) request.getAttribute("login");
	String password = (String) request.getAttribute("password");
	if (login == null)
		login = "";
	if (password == null)
		password = "";
	ArrayList<String> err = (ArrayList<String>) request.getAttribute("tab_err");
%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Connexion</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
</head>

<body>
	<%
		if (err != null && !err.isEmpty()) {
	%>
	<div class="erreur">
		<ul>

			<%
				for (Iterator<String> it = err.iterator(); it.hasNext();) {
			%>
			<li><%=it.next()%></li>
			<%
				}
			%>
		</ul>
	</div>
	<%
		}
	%>
	<div>Veuillez saisir vos paramètres de connexion :</div>
	<div>
		<form action="UserController" method="POST">

			<table>
				<tr>
					<td class="label">Login:</td>
					<td><input type="text" name="login" value="<%=login%>" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="password" name="password"
						value="<%=password%>" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit"
						value="ok " /> <input type="reset" value="Annuler" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>