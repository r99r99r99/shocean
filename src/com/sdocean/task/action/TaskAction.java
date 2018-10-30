package com.sdocean.task.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdocean.task.service.TaskService;

@Component
public class TaskAction {

	@Autowired
	private TaskService taskService;
	
	
	@Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void taskCycle() {
       // System.out.println("使用SpringMVC框架配置定时任务");
        taskService.sendMag();
    }
}
