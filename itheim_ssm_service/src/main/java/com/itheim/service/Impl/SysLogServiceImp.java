package com.itheim.service.Impl;

import com.itheim.SysLog;
import com.itheim.dao.sysLogDao;
import com.itheim.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-27 17:48
 **/
@Service
public class SysLogServiceImp implements SysLogService {

    @Autowired
    private sysLogDao dao;
    @Override
    public List<SysLog> findAll() {

        return dao.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        dao.save(sysLog);
    }

    @Override
    public List<SysLog> findByName(String username) {
        return dao.findByName(username);
    }
}
