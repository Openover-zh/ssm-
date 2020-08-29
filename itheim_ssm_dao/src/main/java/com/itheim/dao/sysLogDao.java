package com.itheim.dao;

import com.itheim.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface sysLogDao {
    @Select("select * from sysLog")
    List<SysLog> findAll();
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from sysLog where username=#{username}")
    List<SysLog> findByName(String username);
}
