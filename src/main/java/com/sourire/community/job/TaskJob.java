package com.sourire.community.job;

import cn.hutool.core.date.DateUtil;
import com.sourire.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * @author sourire
 * @version 1.0
 * @date 2020/1/19 18:20
 */
@Component
@Slf4j
public class TaskJob {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private QuestionService questionService;

    /**
     * 从启动时间开始，间隔 2s 执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate =  1000 * 60 * 60)
    public void viewCountPer() {
        log.info("viewCountPer开始执行：{}", DateUtil.formatDateTime(new Date()));
        String prefix = "viewCount:question:";
        Set<String> keys = stringRedisTemplate.keys(prefix+"*");
        for (String key : keys) {
            //获得id
            String id = key.substring(19);
            questionService.updateViewCount(Integer.valueOf(id),Long.valueOf(stringRedisTemplate.opsForValue().get(key)));
        }
    }
}
