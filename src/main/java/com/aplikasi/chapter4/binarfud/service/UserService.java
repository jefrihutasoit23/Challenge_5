package com.aplikasi.chapter4.binarfud.service;

import com.aplikasi.chapter4.binarfud.entity.User;

import java.util.Map;

public interface UserService {
    Map addUser(User user);
    Map updateUser(User user);
    Map deleteUser(User user);
    Map getByID(Long user);
}
