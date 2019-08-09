package com.sccdrs.work.service.impl;

import com.sccdrs.work.entity.JobEntity;
import com.sccdrs.work.mapper.QuartzMappers;
import com.sccdrs.work.quartz.DynamicJob;
import com.sccdrs.work.service.DynamicJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wcy
 * @date 2019/8/7 17:09
 * @Description:
 */
@Service
public class DynamicJobServiceImpl implements DynamicJobService {

    @Autowired
    QuartzMappers quartzMapper;

    @Override
    public JobEntity getJobEntityById(Integer id) {
        return quartzMapper.getById(id);
    }

    @Override
    public List<JobEntity> loadJobs() {
        List<JobEntity> list = quartzMapper.getAllList();
        return list;
    }

    @Override
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("group", job.getGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("JobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
    return map;
    }

    @Override
    public JobDetail geJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class).
                withIdentity(jobKey).
                withDescription(description).
                setJobData(map).storeDurably().build();
    }

    @Override
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger().
                withIdentity(job.getName(),job.getGroup()).
                withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).
                build();
    }

    @Override
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getGroup());
    }
}
