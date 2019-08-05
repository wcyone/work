package com.sccdrs.work.mapper;

import com.sccdrs.work.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * @author wcy
 * @date 2019/8/5 17:01
 * @Description:
 */
@Repository
public interface SysLogMapper {
    int saveLog(SysLog sysLog);
}
