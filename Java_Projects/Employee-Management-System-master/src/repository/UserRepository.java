package repository;

import java.util.List;

import model.User;

public interface UserRepository {

    void save(User user);

    User findByUsername(String username);

    List<User> findAll();

}