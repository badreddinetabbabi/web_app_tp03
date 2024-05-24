package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMetierImpl implements UserMetierInterface {
    @Override
    public void addUser(User u) {
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn.prepareStatement(" insert into user values (0,?,?,?,?)");
            // passer les paramètres
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getLogin());
            ps.setString(4, u.getPassword());
            // executer la requête
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listUsers() {
        // Définir une liste vide pour stocker les objets "User"
        List<User> users = new ArrayList<User>();
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn.prepareStatement(" select * from User");
            // Récupérer le résultat de la requête
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                // parcourir le résultat
                while (rs.next()) {

                    // Construire un objet "User" puis lui affecter les attributs
                    // et enfin l'ajouter dans la liste
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("password"));
                    // ajouter l'objet "User" dans la liste
                    users.add(u);
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retoturner la liste
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(String l, String p) {
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        User u = null;
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn.prepareStatement(" select * from User where login =? and password = ?");
            ps.setString(1, l);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("password"));
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void updateUser(User u) {
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn
                    .prepareStatement(" update user set nom= ?, prenom=?, login=?, password =? where id=? ");
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getLogin());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn.prepareStatement(" delete from user where id=? ");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public User getUserById(int id) {
        // récupérer une connexion à la BD
        Connection conn = DBConnexion.getConnection();
        User u = null;
        try {
            // préparer la requête SQL
            PreparedStatement ps = conn.prepareStatement(" select* from User where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("password"));
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    
    @Override
    public List<User> getFilteredUsers(String searchName, String searchFirstName) {
        List<User> users = new ArrayList<>();
        Connection conn = DBConnexion.getConnection();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM User WHERE 1=1");
            if (searchName != null && !searchName.isEmpty()) {
                sql.append(" AND nom LIKE ?");
            }
            if (searchFirstName != null && !searchFirstName.isEmpty()) {
                sql.append(" AND prenom LIKE ?");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (searchName != null && !searchName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchName + "%");
            }
            if (searchFirstName != null && !searchFirstName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchFirstName + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                users.add(u);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
