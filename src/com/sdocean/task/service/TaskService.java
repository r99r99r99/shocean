package com.sdocean.task.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.socket.TextMessage;

import com.sdocean.frame.model.ConfigInfo;
import com.sdocean.frame.util.JsonUtil;
import com.sdocean.station.dao.StationCommDao;
import com.sdocean.users.model.SysUser;
import com.sdocean.websocket.service.MsgScoketHandle;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class TaskService {

	@Autowired
    private MsgScoketHandle msgScoketHandle;
	
	@ResponseBody
    @RequestMapping("sendMsg")
    public String sendMag(){
        SysUser user = new SysUser();
        user.setId(1);
        user.setRealName("系统");
        
        TextMessage textMessage = new TextMessage(JsonUtil.toJson(user));
        msgScoketHandle.sendMessageToUser(user,textMessage);
        return "200";
    }
}
