<%@page language="java" import="java.util.ArrayList, metier.User"%>
<%@include file="entete.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Fiche User</title>
</head>
<body >
		Veuillez saisir les propriétés de l'utilisateur...
		<hr>
		
		<%
		
	      //gestion des erreurs
			ArrayList erreurs = (ArrayList) request.getAttribute("err");
			if (erreurs != null) 
			{
				%>
				<div class="erreur">
				<%
				out.println("<ul>");
				for (int i = 0; i < erreurs.size(); i++) {
					out.println("<li> " + (String) erreurs.get(i) + "</li>");
				}
				out.println("</ul>");
				%>
				</div>
			<%
			}
			
			
			// gestion de la saisie de  l'utilisateur en cours
			String login =null;
			String password =null;
			String nom =null;
			String prenom =null;
			int id =0;
			User u = (User)request.getAttribute("user");
			if (u!=null)
			{
			 id=u.getId();
			 login = u.getLogin();
			 password = u.getPassword();
			 nom = u.getNom();
			 prenom = u.getPrenom();
			}
			
			// Correction des  valleurs nulles
			if (login==null)login="";
			if (password==null)password="";
			if (nom==null)nom="";
			if (prenom==null)prenom="";
		%>
		
		<form action="UserEditionController" method="POST">
			<table>
			<tr>
				<td><input type="hidden" name="id" value ="<%=id%>"/></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" value ="<%=login%>"/></td>
			</tr>
			<tr>
				<td>Mot de passe:</td>
				<td><input type="password" name="password" value ="<%=password%>"/></td>
			</tr>
			<tr>
				<td>Nom:</td>
				<td><input type="text" name="nom" value ="<%=nom%>"/></td>
			</tr>
			<tr>
				<td>Prénom:</td>
				<td><input type="text" name="prenom" value ="<%=prenom%>"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="ok" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
	</form>

</body>
</html>