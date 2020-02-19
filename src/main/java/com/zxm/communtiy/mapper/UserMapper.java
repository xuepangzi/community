package com.zxm.communtiy.mapper;

import com.zxm.communtiy.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (accound_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accoundId},#{name},#{token},#{tmgCreate},#{tmgModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id = #{creator}")
    User findById(@Param("creator") int creator);

    @Select("select * from user where accound_id = #{accoundId}")
    User findByAccoundId(@Param("accoundId") String accoundId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{tmgModified},avatar_url=#{avatarUrl} where accound_id = #{accoundId}")
    void updateByAccoundId(User user);
}
