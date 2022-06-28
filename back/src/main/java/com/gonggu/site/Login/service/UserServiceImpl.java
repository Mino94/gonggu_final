package com.gonggu.site.Login.service;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.Login.repository.UserRepository;
import com.google.auto.value.extension.serializable.SerializableAutoValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto regiUser(UserDto userDto)
    {
        if(userRepository.findByUserId(userDto.getUserId())==null ){

            return userRepository.save(userDto);
        } else {
            return null;
        }

    }

    @Override
    public UserDto login(UserDto userDto){

        return userRepository.findByUserIdAndPassword( userDto.getUserId(), userDto.getPassword());
    }

    @Override
    public boolean checkUser(int id) {
        return userRepository.findById(id) != null;
    }

    @Override
    public Map<String, String> getBankAccount(int id) {
        UserDto userDto = userRepository.findById(id);
        Map<String, String> bankAccountMap= new HashMap<>(1);
        bankAccountMap.put("bank", userDto.getBank());
        bankAccountMap.put("bankAccount", userDto.getBankaccount());
        bankAccountMap.put("name", userDto.getName());
        return bankAccountMap;
    }

    @Override
    public void setGoogleToken(Integer userId, String fcmToken) {
        userRepository.deleteMemberToken(userId);
        userRepository.saveMemberToken(userId, fcmToken);
    }

    @Override
    public boolean checkId(String userId) {
        UserDto userDto = userRepository.findByUserId(userId);
        return userDto == null;
    }
}
