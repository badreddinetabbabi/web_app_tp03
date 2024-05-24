<%@ page language="java" import="java.util.ArrayList, metier.User" %>
<%@ include file="entete.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Consultation</title>
</head>
<body>
    <div>
        <h1>Liste des utilisateurs</h1>
        <hr>
        <!-- Search form -->
        <form action="UserController" method="get">
            <label for="searchName">Nom:</label>
            <input type="text" name="searchName" id="searchName">
            <label for="searchFirstName">Prénom:</label>
            <input type="text" name="searchFirstName" id="searchFirstName">
            <button type="submit">Rechercher</button>
        </form>
        <hr>
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Login</th>
                <th>Mot de passe</th>
                <th colspan="2">Actions</th>
            </tr>
            <%
                ArrayList<User> users = (ArrayList<User>) session.getAttribute("listOfUsers");
                if (users != null) {
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        out.println("<tr>");
                        out.println("<td>" + user.getNom() + "</td>");
                        out.println("<td>" + user.getPrenom() + "</td>");
                        out.println("<td>" + user.getLogin() + "</td>");
                        out.println("<td>" + user.getPassword() + "</td>");
                        out.print("<td><a href='UserEditionController?id=" + user.getId() + "&mode=Edition'>Modifier</a></td>");
                        out.println("<td><a href='UserEditionController?id=" + user.getId() + "&mode=Suppression' onclick='return confirm(\"Voulez-vous vraiment supprimer cet utilisateur ?\")'>Supprimer</a></td>");
                        out.println("</tr>");
                    }
                }
            %>
        </table>
        <hr>
        <a href="UserForm.jsp">Ajouter</a>
    </div>
</body>
</html>
