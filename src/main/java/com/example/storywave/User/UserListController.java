package com.example.storywave.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*@RestController*/
@Controller
@RequestMapping("/api/userList")
public class UserListController {

    private List<User> users = new ArrayList<>();


    //포스트 조회기능
    @GetMapping
    public String list(Model model){
        model.addAttribute ("users", users);
        return "adminUserControl/user/userList";
    }
}
