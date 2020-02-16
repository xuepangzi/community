package com.zxm.communtiy.mapper;

import com.zxm.communtiy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (accound_id,name,token,gmt_create,gmt_modified) values (#{accoundId},#{name},#{token},#{tmgCreate},#{tmgModified})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
