package com.aplikasi.chapter4.binarfud.controller;

import com.aplikasi.chapter4.binarfud.entity.Merchant;
import com.aplikasi.chapter4.binarfud.entity.User;
import com.aplikasi.chapter4.binarfud.repository.MerchantRepository;
import com.aplikasi.chapter4.binarfud.repository.UserRepository;
import com.aplikasi.chapter4.binarfud.service.UserService;
import com.aplikasi.chapter4.binarfud.utils.SimpleStringUtils;
import com.aplikasi.chapter4.binarfud.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TemplateResponse response;

    @PostMapping(value ={"/add","/add/"})
    public ResponseEntity<Map> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<Map>(userService.addUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(response.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @PutMapping(value={"/update", "/update/"})
    public ResponseEntity<Map> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<Map>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(response.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @DeleteMapping(value={"/delete", "/delete/"})
    public ResponseEntity<Map> deleteUser(@RequestBody User user) {
        try {
            return new ResponseEntity<Map>(userService.deleteUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(response.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
    @GetMapping(value={"/{id}", "/{id}/"})
    public ResponseEntity<Map> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Map>(userService.getByID(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(response.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
    @GetMapping(value = {"/listUsers", "/listUsers/"})
    public ResponseEntity<Map> list(
            @RequestParam() Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        try {
            Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);

            Specification<User> spec =
                    ((root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (username != null && !username.isEmpty()) {
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
                        }
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                    });
            Page<User> list = userRepository.findAll(spec, show_data);

            Map map = new HashMap();
            map.put("data",list);
            return new ResponseEntity<Map>(map, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Map>(response.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}

