package service;

import database.JdbcUserRepository;
import model.User;
import repository.UserRepository;

public class UserService {

    private UserRepository repository;

    public UserService() {
        repository = new JdbcUserRepository();
    }

    public boolean login(String username, String password) {

        User user = repository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

}