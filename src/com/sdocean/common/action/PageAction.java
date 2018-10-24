package com.sdocean.common.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdocean.beach.model.BeachAllConfig;
import com.sdocean.beach.model.BeachDataModel;
import com.sdocean.beach.model.BeachDegreeModel;
import com.sdocean.beach.model.BeachGroupModel;
import com.sdocean.beach.model.BeachStatisModel;
import com.sdocean.beach.service.BeachService;
import com.sdocean.common.model.PageStyle;
import com.sdocean.dataQuery.model.StatisModel;
import com.sdocean.frame.model.ConfigInfo;
import com.sdocean.frame.util.JsonUtil;
import com.sdocean.log.service.OperationLogService;
import com.sdocean.station.model.StationModel;
import com.sdocean.station.service.StationService;
import com.sdocean.users.model.SysUser;

@Controller
public class PageAction {

	private static Logger log = Logger.getLogger(PageAction.class);  
	@Autowired
	private ConfigInfo info;
	
	
	/*
	 * 设定菜单的位置
	 */
	@RequestMapping(value="/changeTopMenuPageStyle.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String changeTopMenuPageStyle(HttpServletRequest request,
						HttpServletResponse response) {
		HttpSession session = request.getSession();
		PageStyle pageStyle = (PageStyle) session.getAttribute("pageStyle");
		if(pageStyle==null) {
			pageStyle = new PageStyle();
		}
		int menuStyle = pageStyle.getMenuStyle();
		if(menuStyle==0) {
			menuStyle = 1;
		}else {
			menuStyle = 0;
		}
		pageStyle.setMenuStyle(menuStyle);
		session.setAttribute("pageStyle", pageStyle);
		return JsonUtil.toJson(pageStyle);
	}
	
}
