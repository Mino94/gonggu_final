package com.gonggu.site.token.service;

import com.gonggu.site.token.model.MemberTokenDto;

public interface MemberTokenService {

    String getMemberToken(Integer userId);

}
