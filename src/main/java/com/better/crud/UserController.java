package com.better.crud;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    //?
    UserRepos userRepos;


    @GetMapping("/signup")
    public String showSingUpForm(User user){
        return "add-user";
    }

    @GetMapping("/adduser")
    public String addUser(@Valid User user , BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }
        //?
        userRepos.save(user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model){
        model.addAttribute("user", userRepos.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)  {
        User user = userRepos.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Недействительный id пользователя:" + id));

        model.addAttribute("user",user);
        return "update-user";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id")Long id, @Valid User user,
                             BindingResult  result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "update-user";
        }

        userRepos.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")Long id, Model model){
        User user= userRepos.findById(id).orElseThrow(( )-> new IllegalArgumentException("Недействительный id пользоваьтеля:" + id) );

        userRepos.delete(user);
        return "redirect:/index";
    }

}
