package com.sdocean.video.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sdocean.common.model.Result;
import com.sdocean.common.model.ZTreeModel;
import com.sdocean.file.action.FileUpload;
import com.sdocean.frame.model.ConfigInfo;
import com.sdocean.frame.util.JsonUtil;
import com.sdocean.log.service.OperationLogService;
import com.sdocean.menu.model.CurrMenu;
import com.sdocean.page.model.NgColumn;
import com.sdocean.page.model.PageResult;
import com.sdocean.page.model.UiColumn;
import com.sdocean.role.model.RoleModel;
import com.sdocean.sms.model.SmsSettingModel;
import com.sdocean.station.model.StationModel;
import com.sdocean.users.model.SysUser;
import com.sdocean.users.service.UsersManager;

@Controller
public class VideoAction {
	
	@Resource
	OperationLogService logService;
	@Autowired
	private ConfigInfo info;
	
	@RequestMapping("video_init.do")
	public ModelAndView video_init(HttpServletRequest request,  
	        HttpServletResponse response)throws Exception{
		    ModelAndView mav = new ModelAndView("/"+info.getPageVision()+"/video/videoinfo");
	        return mav;  
	}
	
	/*
	 * 下载维护文件
	 */
	@RequestMapping(value="exportVideoFile.do")
	public void exportVideoFile(
			@RequestParam("fileName") String fileName,
			@RequestParam("filePath") String filePath,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String path = info.getFilePath()+"//"+filePath;
		FileUpload fileUpload = new FileUpload();
		fileUpload.downFile(fileName,path, request, response);
	}	
}
