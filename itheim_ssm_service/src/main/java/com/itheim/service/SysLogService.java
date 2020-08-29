package com.itheim.service;

import com.itheim.SysLog;

import java.util.List;

public interface SysLogService {
    public List<SysLog> findAll();

    void save(SysLog sysLog);

    List<SysLog> findByName(String username);
}
