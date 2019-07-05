package com.equals.reader.services;

import com.equals.reader.models.security.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
