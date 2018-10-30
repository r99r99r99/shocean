package com.sdocean.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.sdocean.frame.util.JsonUtil;
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
