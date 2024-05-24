package metier;
import java.util.List;

public class TestMetier {
    public static void main(String[] args) {
        // Appel à la couche « Services »
        UserMetierInterface metier = new UserMetierImpl();
        
        // Test d'ajout
        metier.addUser(new User("Ben Saleh", "Mohamed", "11", "22"));
        System.out.println(" \n");

        // Test d'affichage de la liste totale des objets "User"
        List<User> users = metier.listUsers();
        System.out.println("Liste des utilisateurs avant suppression :");
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println(" \n");

        // Test d'affichage d'un objet "User" en donnant le login et le password
        User u = metier.getUserByLoginAndPassword("11", "22");
        System.out.println(u);
        System.out.println(" \n");

        // Tester la mise à jour
        u.setNom("Sallemi");
        metier.updateUser(u);
        List<User> users2 = metier.listUsers();
        System.out.println("Liste des utilisateurs après mise à jour :");
        for (User u2 : users2) {
            System.out.println(u2);
        }
        System.out.println(" \n");

        // Test de suppression
        System.out.println("Suppression de l'utilisateur avec id=0");
        metier.deleteUser(0);

        // Afficher la liste après suppression
        List<User> users3 = metier.listUsers();
        System.out.println("Liste des utilisateurs après suppression :");
        for (User u3 : users3) {
            System.out.println(u3);
        }
        System.out.println(" \n");
    }
    
    
}
