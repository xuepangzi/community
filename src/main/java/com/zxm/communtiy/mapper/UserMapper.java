package com.zxm.communtiy.mapper;

import com.zxm.communtiy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    @Insert("insert into user (accound_id,name,token,gmt_create,gmt_modified) values (#{accoundId},#{name},#{token},#{tmgCreate},#{tmgModified})")
    public void insert(User user);
}
