package com.itheim.dao;

import com.itheim.Member;
import org.apache.ibatis.annotations.Select;

public interface memberDao {
    @Select("select * from member where id=#{memberId}")
    public Member findById(String memberId) throws Exception;
}
