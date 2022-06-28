package com.gonggu.site.Login.service;

import com.gonggu.site.Login.model.UserDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {
    public UserDto regiUser(UserDto userDto);

    public UserDto login(UserDto userDto);


    boolean checkUser(int id);

    Map<String, String> getBankAccount(int id);

    void setGoogleToken(Integer userId, String fcmToken);

    boolean checkId(String userId);
}
