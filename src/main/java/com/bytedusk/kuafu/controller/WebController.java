package com.bytedusk.kuafu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@RequestMapping("/biz")
public class WebController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest servletRequest) throws Exception {
        String loginTime = sdf.format(new Date());
        logger.info("登录时间：{}",loginTime);
        return ResponseEntity.ok("200");
    }

}