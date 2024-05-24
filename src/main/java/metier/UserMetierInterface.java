package metier;

import java.util.List;

public interface UserMetierInterface {
    void addUser(User u);

    List<User> listUsers();

    User getUserByLoginAndPassword(String l, String p);

    void updateUser(User u);

    void deleteUser(int id);

    User getUserById(int id);
    
    // Add this method declaration
    List<User> getFilteredUsers(String searchName, String searchFirstName);
}
