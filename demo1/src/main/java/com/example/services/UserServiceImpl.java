package com.example.services;

import com.example.dao.UserDAOImpl;
import com.example.model.Role;
import com.example.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    private final UserDAOImpl userDAO;

    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void addUser(User user) {
//        userRepository.save(user);
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
//        userRepository.save(user);
        userDAO.addUser(user);
    }

    @Transactional
    public void removeUser(int id) {
//        userRepository.deleteById(id);
        userDAO.removeUser(id);
    }

    public User getUserById(int id) {
        Optional<User> opt = userDAO.getUserById(id);
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get();
        }

    }

    public List<User> listUsers() {
//        return userRepository.findAll();
       return userDAO.listUsers();
    }



    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
      return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
