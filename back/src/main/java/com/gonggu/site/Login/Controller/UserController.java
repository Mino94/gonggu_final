package com.gonggu.site.Login.Controller;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.Login.service.UserService;
import com.gonggu.site.aspect.TokenRequired;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.config.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("user") //"/user"가 아니라 "user"로 사용하는 이유?
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;

    @PostMapping("/login")
    public Map login(@RequestBody UserDto userDto) {
        UserDto loginUser = userService.login(userDto);
        String token=securityService.createToken(loginUser.getId().toString());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("id",loginUser.getId());
        return map;
    }

    @GetMapping("/token")
    @TokenRequired
    public String getToken()
    {
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();

        String tokenBearer=request.getHeader("Authorization");

        String subject=securityService.getSubject(tokenBearer);
        return  subject;
    }

    @PostMapping("/join")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.regiUser(userDto);
    }

    @GetMapping("/checkUser")
    public boolean checkUser(HttpServletRequest request) {
        int id = securityService.getIdAtToken(request);
        return userService.checkUser(id);
    }

    @GetMapping("/getBankAccount/{id}")
    public Map<String, String> getBankAccount(@PathVariable int id) {
        return userService.getBankAccount(id);
    }

    @PostMapping("/setGoogleToken")
    public void setGoogleToken(@RequestBody Map<String, String> paramMap) {
        userService.setGoogleToken(Integer.parseInt(paramMap.get("userId")), paramMap.get("fcmToken"));
    }

    @GetMapping("/checkId/{id}")
    public boolean checkId(@PathVariable String id) {
        return userService.checkId(id);
    }
}
