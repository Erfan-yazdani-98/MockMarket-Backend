package com.erfanyazdani.MockMarketBackend.conroller;

import com.erfanyazdani.MockMarketBackend.dto.SubCategoryRequest;
import com.erfanyazdani.MockMarketBackend.model.User;
import com.erfanyazdani.MockMarketBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getAllUsers(){
        return "All Users";
    }
    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id){
        return "User "+id;
    }
    @PostMapping
    public String createAUser(@RequestBody User user){
        return "user: "+user.getUsername()+"\npass: "+user.getPassword()+"\nrole: "+user.getRole();
    }
    @PostMapping({"register"})
    public ResponseEntity<User> register(@RequestBody User user) {
        User responseUser= this.userService.saveUser(user);
        if (responseUser != null) {
            return ResponseEntity.ok(responseUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping({"login"})
    public ResponseEntity<User> login(@RequestBody User user)
    {
        User responseUser= this.userService.getUser(user);
        if (responseUser != null) {
            return ResponseEntity.ok(responseUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
