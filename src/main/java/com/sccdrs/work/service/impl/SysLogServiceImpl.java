package com.sccdrs.work.service.impl;

import com.sccdrs.work.entity.SysLog;
import com.sccdrs.work.entity.User;
import com.sccdrs.work.mapper.SysLogMapper;
import com.sccdrs.work.mapper.UserMapper;
import com.sccdrs.work.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcy
 * @date 2019/8/5 16:59
 * @Description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public int saveLog(SysLog sysLog){
        return sysLogMapper.saveLog(sysLog);
    }
}
