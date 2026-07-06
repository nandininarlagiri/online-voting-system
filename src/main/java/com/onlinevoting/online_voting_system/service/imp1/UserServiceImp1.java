package com.onlinevoting.online_voting_system.service.imp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.onlinevoting.online_voting_system.entity.User;
import com.onlinevoting.online_voting_system.exception.EmailAlreadyExistsException;
import com.onlinevoting.online_voting_system.exception.UserNotFoundException;

import java.util.List;

import com.onlinevoting.online_voting_system.repository.UserRepository;
import com.onlinevoting.online_voting_system.service.UserService;


@Service
public class UserServiceImp1 implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user){
        if (userRepository.existsByEmail(user.getEmail())) {
        throw new EmailAlreadyExistsException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() ->
    new UserNotFoundException("User not found with id: " + id));
    }

    
    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

   
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

}
