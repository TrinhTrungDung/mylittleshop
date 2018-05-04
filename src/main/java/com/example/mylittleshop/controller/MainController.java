package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Account;
import com.example.mylittleshop.model.Password;
import com.example.mylittleshop.repository.AccountRepository;
import com.example.mylittleshop.support.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    AccountRepository accountRepository;


    @GetMapping("/")
    String index(Authentication authentication) {
        if(authentication != null){
            //System.out.println(authentication);
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if ("ROLE_MANAGER".equals(auth.getAuthority())){
                    return "redirect:/admin";
                }
            }
            return "redirect:/employee";
        }
        else return "redirect:/login";
    }

    @GetMapping("/admin")
    String admin(){
        return "admin";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    String changePassword(@RequestParam Map<String, String> password, Model model, Principal principal, RedirectAttributes redirectAttributes){
        String username = principal.getName();
        System.out.println(password.get("old_password"));
        System.out.println(password.get("new_password"));
        System.out.println(password.get("confirm_password"));
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String old_password = accountRepository.findByUsername(username).getPassword();
        if(!passwordEncoder.matches(password.get("old_password"),old_password)){
            redirectAttributes.addFlashAttribute("message",new Message("Wrong old password!",Message.Type.DANGER));
            return "redirect:/admin";
        }

        if(password.get("new_password").equals(password.get("confirm_password"))){

            Account account = accountRepository.findByUsername(username);
            account.setPassword(passwordEncoder.encode(password.get("new_password")));
            accountRepository.save(account);
            redirectAttributes.addFlashAttribute("message",new Message("Success",Message.Type.DANGER));
            return "redirect:/admin";
        }
        else{
            redirectAttributes.addFlashAttribute("message",new Message("Passwords not match",Message.Type.DANGER));
            return "redirect:/admin";
        }


    }




}
