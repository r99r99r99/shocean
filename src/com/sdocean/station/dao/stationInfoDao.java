package com.sdocean.station.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.sdocean.common.model.Result;
import com.sdocean.common.model.SelectTree;
import com.sdocean.common.model.ZTreeModel;
import com.sdocean.device.model.DeviceModel;
import com.sdocean.frame.dao.OracleEngine;
import com.sdocean.frame.model.ConfigInfo;
import com.sdocean.frame.util.JsonUtil;
import com.sdocean.role.model.RoleModel;
import com.sdocean.station.model.StationDeviceComm;
import com.sdocean.station.model.StationInfo;
import com.sdocean.station.model.StationModel;
import com.sdocean.station.model.StationPictureModel;
import com.sdocean.station.model.StationTypeModel;
import com.sdocean.users.model.SysUser;

@Component
public class stationInfoDao extends OracleEngine {
	
	/*
	 * 获得站点  的图片列表
	 */
	public List<StationPictureModel> getStationPicListByStationType(ConfigInfo info,StationPictureModel model){
		//获得图片所在的imagePath 路径
		String imagePath = info.getFilePath();
		if(imagePath.substring(imagePath.length()-1, imagePath.length()).equals("//")){
			imagePath = imagePath + "//";
		}
		imagePath = imagePath + "//";  //数据库转义
		//获得图片所在的stationPicPath 路径
		String stationPicPath = info.getStationPicPath();
		String filePath = imagePath + stationPicPath;
		if(filePath.substring(filePath.length()-1, filePath.length()).equals("//")){
			filePath = filePath + "//";
		}
		filePath = filePath + "//";
		filePath = "getPicture.do?Path="+ filePath;
		List<StationPictureModel> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("");
		sql.append(" select a.id,a.stationid,c.title as stationname,a.type,b.value as typename,");
		sql.append(" modiname,origname,a.userid,d.realname as username,a.createtime,a.remark,concat('").append(filePath).append("',modiname) as src");
		sql.append(" from aiot_station_pic a,sys_public b,aiot_watch_point c,sys_user d");
		sql.append(" where b.parentcode = '0021'");
		sql.append(" and a.type = b.classid");
		sql.append(" and a.stationid = c.id and a.userid = d.id");
		//添加查询条件
		sql.append(" and a.stationid = ").append(model.getStationId());
		if(model!=null&&model.getType()>0){
			sql.append(" and a.type = ").append(model.getType());
		}
		list = this.queryObjectList(sql.toString(), StationPictureModel.class);
		return list;
	}
}
