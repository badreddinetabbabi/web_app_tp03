<%@page language="java" import="metier.User"%>
<%
User us= (User)session.getAttribute("user");
if (us==null)
{
   request.getRequestDispatcher("UserConnexion.jsp").forward(request, response);
}
else
{


%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body >
  <div>
   <table border ="1" width="100%">
	<tr><td  >BienVenue   : [<%=us.getId()%>]:[<%=us.getNom()%>][<%=us.getPrenom()%>]</td>
		<td ><a  href ="accueil.jsp"> Accueil</a></td>
		<td ><a  href ="UserDeconnexionController" onclick="return confirm('Voulez vous vraiment quitter ?')"> Déconnexion</a></td>
	</tr>
	</table>
</div> 	
</body>
</html>
<%
}
%>