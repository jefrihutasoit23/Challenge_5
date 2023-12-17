package com.aplikasi.chapter4.binarfud.service.impl;

import com.aplikasi.chapter4.binarfud.entity.Merchant;
import com.aplikasi.chapter4.binarfud.entity.User;
import com.aplikasi.chapter4.binarfud.service.UserService;
import com.aplikasi.chapter4.binarfud.repository.UserRepository;
import com.aplikasi.chapter4.binarfud.utils.Config;
import com.aplikasi.chapter4.binarfud.utils.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateResponse response;

    @Override
    public Map addUser(User user) {
        try {
            log.info("add User");
            return response.sukses(userRepository.save(user));
        }catch (Exception e){
            log.error("add user error: "+e.getMessage());
            return response.Error("add user ="+e.getMessage());
        }
    }

    @Override
    public Map updateUser(User user) {
        try {
            log.info("Update User");
            if (user.getId() == null) {
                return response.Error(Config.ID_REQUIRED);
            }
            Optional<User> chekDataDBUser = userRepository.findById(user.getId());
            if (chekDataDBUser.isEmpty()) {
                return response.Error(Config.USER_NOT_FOUND);
            }
            chekDataDBUser.get().setEmail(user.getEmail());
            chekDataDBUser.get().setUsername(user.getUsername());
            chekDataDBUser.get().setPassword(user.getPassword());
            chekDataDBUser.get().setUpdated_date(new Date());

            return response.sukses(userRepository.save(chekDataDBUser.get()));
        }catch (Exception e){
            log.error("Update User error: "+e.getMessage());
            return response.Error("Update User="+e.getMessage());
        }
    }

    @Override
    public Map deleteUser(User user) {
        try {
            log.info("Delete user");
            if (user.getId() == null) {
                return response.Error(Config.ID_REQUIRED);
            }
            Optional<User> chekDataDBUser = userRepository.findById(user.getId());
            if (chekDataDBUser.isEmpty()) {
                return response.Error(Config.MERCHANT_NOT_FOUND);
            }

            chekDataDBUser.get().setDeleted_date(new Date());
            userRepository.save(chekDataDBUser.get());
            return response.sukses(Config.SUCCESS);
        }catch (Exception e){
            log.error("Delete User error: "+e.getMessage());
            return response.Error("Delete User ="+e.getMessage());
        }
    }

    @Override
    public Map getByID(Long user) {
        Optional<User> getBaseOptional = userRepository.findById(user);
        if(getBaseOptional.isEmpty()){
            return response.notFound(getBaseOptional);
        }
        return response.templateSukses(getBaseOptional);
    }
}
