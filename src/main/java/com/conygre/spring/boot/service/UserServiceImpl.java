package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.User;
import com.conygre.spring.boot.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repo;

    @Autowired
    public void setRepo(UserRepository repo) { this.repo = repo; }

    @Override
    public List<User> getUsers() { return repo.findAll(); }

    @Override
    public User getUserById(int id) {
        Optional<User> result = repo.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public void addToUser(User user) { repo.save(user); }

    @Override
    public void deleteUser(int id) { repo.deleteById(id); }

    @Override
    public void updateUser(User user) {
        if(getUserById(user.getId()) != null) {
            repo.save(user);
        }
    }
}
