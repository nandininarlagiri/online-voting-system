package com.onlinevoting.online_voting_system.service;
import com.onlinevoting.online_voting_system.entity.User;
import java.util.List;

import com.onlinevoting.online_voting_system.dto.LoginRequest;
import com.onlinevoting.online_voting_system.dto.LoginResponse;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    LoginResponse login(LoginRequest loginRequest);


}
