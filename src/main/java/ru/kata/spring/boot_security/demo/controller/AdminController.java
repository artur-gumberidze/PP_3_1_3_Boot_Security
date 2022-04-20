package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.data.domain.PageRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

 @GetMapping("/admin")
 public String findAll(Model model, @RequestParam(defaultValue = "0") int page, Principal principal) {
        User user = userService.findByUsername(principal.getName());
     List<User> users = userService.findAll();
     model.addAttribute("onlyUser", user);
     model.addAttribute("users",users);
     model.addAttribute("data",userRepository.findAll(PageRequest.of(page,4)));
     model.addAttribute("currentPage",page);
     return "allUsersExample";
 }

    @GetMapping("delete/")
    public String deleteUser( Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

/*    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(@ModelAttribute("person") @Valid User user, BindingResult bindingResult){
        //Role role = new Role(1L,"ROLE_USER");
        //Set<Role> s = new HashSet<>();
        //s.add(role);
        //user.setRoles(s);
        userService.saveUser(user);
        return "redirect:/admin";
    } */

    @GetMapping("findOne/")
    @ResponseBody
    public User findOne(Long id){
        User u = userRepository.findById(id).orElse(null);
        return u;
    }

    @PostMapping("/save")
    public String save (User u, @RequestParam String[] roles1){
        //Role role = new Role(2L,"ROLE_USER");
        //Set<Role> s = new HashSet<Role>();
        List <Role> l = roleByName(roles1);

        u.setPassword(passwordEncoder().encode(u.getPassword()));
        //s.add(role);

        u.setRoles(l);
        userService.saveUser(u);
        return "redirect:/admin";
    }

    public List<Role> roleByName(String[] roles){
        List<Role> l = new ArrayList<>();
        for (int i = 0; i < roles.length; i++){
            if (roles[i].equals("USER")){
                l.add(new Role(2L,"ROLE_USER"));
            }
            if (roles[i].equals("ADMIN")){
                l.add(new Role(1L,"ROLE_ADMIN"));
            }
        }
        return l;
    }
}
