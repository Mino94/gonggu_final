package com.gonggu.site.Login.repository;

import com.gonggu.site.Login.model.UserDto;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    UserDto save(UserDto userDto);

//    UserDto findBy(int userId);
    UserDto findByUserIdAndPassword(String userId, String password);

    UserDto findByUserId(String userId);

    UserDto findById(int id);

    List<UserDto> findByIdIn(List<Integer> userIdList);
    //비밀번호 체크

    //google cloud message를 쓰기 위해서 token을 db table에 등록
    @Query(value = "INSERT INTO MEMBER_TOKEN VALUES (NULL, :userId, :token)", nativeQuery = true)
    @Modifying
    @Transactional
    int saveMemberToken(@Param(value="userId") Integer userId, @Param(value="token") String token);

    @Query(value = "DELETE FROM MEMBER_TOKEN WHERE USER_ID = :userId", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteMemberToken(@Param(value="userId") Integer userId);
}
