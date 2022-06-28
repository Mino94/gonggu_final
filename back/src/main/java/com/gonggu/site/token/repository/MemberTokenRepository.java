package com.gonggu.site.token.repository;

import com.gonggu.site.token.model.MemberTokenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokenRepository extends JpaRepository<MemberTokenDto, Long> {
    MemberTokenDto findByUserId(Integer userId);
}
