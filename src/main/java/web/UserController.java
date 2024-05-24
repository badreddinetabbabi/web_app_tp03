package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.User;
import metier.UserMetierImpl;
import metier.UserMetierInterface;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserMetierInterface metier = null;

    public UserController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        metier = new UserMetierImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = request.getParameter("searchName");
        String searchFirstName = request.getParameter("searchFirstName");

        List<User> users;
        if ((searchName != null && !searchName.isEmpty()) || (searchFirstName != null && !searchFirstName.isEmpty())) {
            users = metier.getFilteredUsers(searchName, searchFirstName);
        } else {
            users = metier.listUsers();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("listOfUsers", users);
        request.getRequestDispatcher("UserList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String l = request.getParameter("login");
        out.println("La valeur du nom est:" + l);
        String pwd = request.getParameter("password");
        out.println("La valeur du mot de passe est:" + pwd);

        ArrayList<String> erreurs = new ArrayList<String>();
        if (l != null && l.equals("")) {
            erreurs.add("Champs login vide. Merci de spécifier une valeur.");
        }
        if (pwd != null && pwd.equals("")) {
            erreurs.add("Champs password vide. Merci de spécifier une valeur.");
        }
        if (erreurs.isEmpty()) {
            User u = metier.getUserByLoginAndPassword(l, pwd);
            if (u != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", u);
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            } else {
                erreurs.add("Utilisateur non reconnu.");
                request.setAttribute("login", l);
                request.setAttribute("password", pwd);
                request.setAttribute("tab_err", erreurs);
                request.getRequestDispatcher("UserConnexion.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("login", l);
            request.setAttribute("password", pwd);
            request.setAttribute("tab_err", erreurs);
            request.getRequestDispatcher("UserConnexion.jsp").forward(request, response);
        }
    }
}
