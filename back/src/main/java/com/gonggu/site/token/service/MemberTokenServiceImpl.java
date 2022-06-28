package com.gonggu.site.token.service;

import com.gonggu.site.token.model.MemberTokenDto;
import com.gonggu.site.token.repository.MemberTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberTokenServiceImpl implements MemberTokenService{

    @Autowired
    MemberTokenRepository memberTokenRepository;

    @Override
    public String getMemberToken(Integer userId) {
        return memberTokenRepository.findByUserId(userId).getToken();
    }
}
