package com.sccdrs.work.mapper;

import com.sccdrs.work.entity.JobEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wcy
 * @date 2019/8/7 17:15
 * @Description:
 */
@Repository
public interface QuartzMappers {
    JobEntity getById(Integer id);

    List<JobEntity> getAllList();

}
