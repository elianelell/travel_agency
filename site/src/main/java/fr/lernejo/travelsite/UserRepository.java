package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private final List<User> userList = new ArrayList<User>();
    private final UserService userService;

    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUserFromUserName(String userName) {
        return userList.stream().filter(user -> user.getUserName().equals(userName)).findFirst().get();
    }
}
