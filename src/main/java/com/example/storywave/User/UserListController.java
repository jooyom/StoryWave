package com.example.storywave.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/mypage")
public class UserListController {

    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();



    //유저 조회 페이지 - 마이페이지랑 연결
    /*@GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("mypage/userList");
        modelAndView.addObject("User", users);
        return modelAndView;
    }*/

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "mypage/userList2";
    }

    @GetMapping("/mypage/{userId}")
    @ResponseBody
    public User getUserInfo(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    /*@PostMapping("/mypage/reject")
    @ResponseBody
    public String updateUserStatus(@RequestBody UserStatusUpdateRequest request) {
        boolean success = userService.updateUserStatus(request.getUserId(), request.getStatus(), request.getReason());
        return success ? "Success" : "Failure";
    }*/


   /* @PostMapping("/updateUserStatus")
    public ResponseEntity<String> updateUserStatus(
            @RequestParam Long userId,
            @RequestParam String status,
            @RequestParam String reason) {
        // Your logic to update user status
        this.updateUserStatus()
        return ResponseEntity.ok("Success");
    }*/

    @PutMapping("/updateUserStatus/{id}")
    public ResponseEntity<Map<String, String>> updateUserStatus(
            @PathVariable("id") Long id,
            @RequestBody User updateUserStatus) {

        User user = users.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지못하였습니다."));

        user.setActiveStatus(updateUserStatus.getActiveStatus());

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");

        return ResponseEntity.ok(response);
    }

}

   /* @PutMapping
    public ResponseEntity<UserDto> updateUserStatus(@PathVariable("id") Long id, @RequestBody ActiveStatus activeStatus){
        UserDto updateUserStatus = userService.updateUserStatus(id, activeStatus);

        return ResponseEntity.ok(updateUserStatus);
    }*/



/*@GetMapping("/admin/{userId}")
public String adminChangeRole(@PathVariable Long userId,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "") String keyword) throws UnsupportedEncodingException {
    userService.changeRole(userId);
    return "redirect:/users/admin?page=" + page + "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
}*/






