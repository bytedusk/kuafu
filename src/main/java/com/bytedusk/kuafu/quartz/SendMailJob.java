package com.bytedusk.kuafu.quartz;

import com.bytedusk.kuafu.controller.WebController;
import com.bytedusk.kuafu.util.SendMail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class SendMailJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WebController webController;

    @Value("${gt.mail.to}")
    private String mailTo;

    @Value("${gt.task.dayInterval}")
    private String dayInterval;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("准备开始发送邮件 ,time is {}", simpleDateFormat.format(new Date()));

        LocalDate now = LocalDate.now();
        LocalDate startDate = now.plus(-Integer.valueOf(dayInterval), ChronoUnit.DAYS);
        String startDateStr = startDate.format(dtf);
        String endDateStr = now.format(dtf);

        String subject = "通知";
        String content = "这是测试内容";

        sendMail.sendSimpleMail(mailTo, subject, content);

        logger.info("邮件发送已完成 ,time is {}", simpleDateFormat.format(new Date()));
    }
}