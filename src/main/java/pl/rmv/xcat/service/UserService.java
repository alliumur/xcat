package pl.rmv.xcat.service;

import pl.rmv.xcat.model.User;

public interface UserService {
    void save(User user);
    User find(String username);
}
