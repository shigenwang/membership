package com.future.membership.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.future.membership.enums.DownloadType;
import com.future.membership.service.EquipmentDtoService;
import com.future.membership.service.MembershipService;
import com.future.membership.util.DateUtil;

@Controller
public class DownLoadController {

	@Autowired
	private EquipmentDtoService equipmentService;
	
	@Autowired
	private MembershipService membershipService;
	
	@RequestMapping(value="/download/{type}",method=RequestMethod.GET)
	public String download(@PathVariable("type") int type,HttpServletResponse response, HttpServletRequest request){
		 //得到业务tid
        String tid=UUID.randomUUID().toString();
    try {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        Date date = new Date();
        DownloadType downlaodType = DownloadType.valueOf(type);
        String filename=tid+DateUtil.formatYYMMDDHH(date)+"_"+downlaodType.getValue()+".xls";

        String agent = (String)request.getHeader("USER-AGENT");
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
        {
            filename = "=?UTF-8?B?" + filename + "?=";
        }
        else
        {
            filename =  java.net.URLEncoder.encode(filename, "UTF-8");
        }

        response.setHeader("Content-Disposition", "attachment;fileName="+filename);

        OutputStream outputStream = response.getOutputStream();
        
        switch (downlaodType) {
		case EQUIPMENT:
			equipmentService.download(outputStream);
			break;
		case MEMBERSHIP:
			membershipService.downLoad(outputStream);
			break;

		default:
			break;
		}
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
	}
	
	@RequestMapping(value="/downloadFileZip",method=RequestMethod.GET)
	public String downloadZip(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition","attachment; filename=\"images.zip\"");

        List<String> fileNames = Arrays.asList("aaa.txt","bbb.txt","sss.txt");
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

        for(String fileName : fileNames) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
          //获取项目根目录
    		String ctxPath = request.getSession().getServletContext()  
    				.getRealPath("");
            FileInputStream inputStream = new FileInputStream(ctxPath + "/"+fileName);
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }

        zipOutputStream.closeEntry();
        zipOutputStream.close();
		return null;
	}

}
