package com.future.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownLoadController {

	@RequestMapping(value="/downloadFile",method=RequestMethod.GET)
	public String downloadFile(HttpServletResponse response, HttpServletRequest request){
		String storeName="sss.txt";
		String contentType = "application/octet-stream";  
		try {
			download(request, response, storeName, contentType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void download(HttpServletRequest request, HttpServletResponse response, String storeName,
			String contentType) throws UnsupportedEncodingException, IOException {
		request.setCharacterEncoding("UTF-8");  
		BufferedInputStream bis = null;  
		BufferedOutputStream bos = null;  
  
		//获取项目根目录
		String ctxPath = request.getSession().getServletContext()  
				.getRealPath("");  
		
		//获取下载文件露肩
		String downLoadPath = ctxPath + storeName;  
  
		//获取文件的长度
		long fileLength = new File(downLoadPath).length();  

		System.out.println("downLoadPath="+downLoadPath);
		
		//设置文件输出类型
		response.setContentType("application/octet-stream");  
		response.setHeader("Content-disposition", "attachment; filename="  
				+ new String(storeName.getBytes("utf-8"), "ISO8859-1")); 
		//设置输出长度
		response.setHeader("Content-Length", String.valueOf(fileLength));  
		//获取输入流
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		//输出流
		bos = new BufferedOutputStream(response.getOutputStream());  
		byte[] buff = new byte[2048];  
		int bytesRead;  
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
			bos.write(buff, 0, bytesRead);  
		}  
		//关闭流
		bis.close();  
		bos.close();  
	}  
		
}
