package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.entity.LoginEntity;
import com.example.HMS_MANAGEMENT.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login_chk")
    public String loginChk(@RequestParam("id") String userId, @RequestParam("pw") String password, HttpSession session, Model model){
        if (loginService.authenticate(userId, password)) {
            LoginEntity user = loginService.findByUserId(userId);
            session.setAttribute("user",user);
            // 로그인 성공
            return "redirect:/";
        } else {
            // 로그인 실패 처리
            return "redirect:/login?error";
        }

    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }

}
