package com.bytedusk.kuafu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTickJob extends QuartzJobBean {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Tick.. Tick.. ,time is {}", simpleDateFormat.format(new Date()));
    }
}