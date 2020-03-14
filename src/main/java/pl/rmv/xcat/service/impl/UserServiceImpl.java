package pl.rmv.xcat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rmv.xcat.model.User;
import pl.rmv.xcat.repository.RoleRepository;
import pl.rmv.xcat.repository.UserRepository;
import pl.rmv.xcat.service.UserService;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));//TODO
        userRepository.save(user);
    }

    @Override
    public User find(String username){
        return userRepository.findByUsername(username);
    }
}
