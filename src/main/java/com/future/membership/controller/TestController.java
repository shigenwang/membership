package com.future.membership.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.EquipmentDtoExample;
import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.EquipmentTypeDtoExample;
import com.future.membership.bean.GymDto;
import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.TeacherDtoExample;
import com.future.membership.bean.bo.excel.EquipmentImExcel;
import com.future.membership.mapper.EquipmentDtoMapper;
import com.future.membership.mapper.EquipmentTypeDtoMapper;
import com.future.membership.mapper.GymDtoMapper;
import com.future.membership.mapper.TeacherDtoMapper;
import com.future.membership.util.ExcelUtils;
import com.future.membership.util.SendMailUtil;

@RestController
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private GymDtoMapper gymDtoMapper;
	
	@Autowired
	private EquipmentTypeDtoMapper typeMapper;
	
	@Autowired
	private TeacherDtoMapper teacherMapper;
	
	@Autowired
	private EquipmentDtoMapper equipmentMapper;
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		return "test";
	}
	@RequestMapping(value="/testIndex",method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/list/{page}/{pagenum}",method = RequestMethod.GET)
	  public String getPortraitList(@PathVariable("page") int page,@PathVariable("pagenum") int pagenum,
			  @RequestParam Map<String, Object> paramMap,
		      @RequestParam(defaultValue = "0", value = "ranktype") Integer ranktype,
		      @RequestParam(defaultValue = "0", value = "rankrend") Integer ranktrend){
		  logger.info("list ===== page={},pagenum={}",page,pagenum);
		  String keyword = "";
		    if (paramMap != null) {
		      keyword = paramMap.get("keyword") != null ? (String) paramMap.get("keyword") : null;
		      logger.info("keyword={}",keyword);
		    }
		    
		    logger.info("Portrait getPortraits:, other: {}", new Object[]{ page, pagenum});
		    return "success";
	  }
	
	@RequestMapping(value="/test2",method=RequestMethod.GET)
	public String test2(){
		if(HttpStatus.OK.toString().equals("200")){
			logger.info("test success HttpStatus ");
		}else{
			logger.info("test error HttpStatus ");
		}
		return "success";
	}
	
	@RequestMapping(value="/list")
	public String list(){
		GymDto gym = gymDtoMapper.selectByPrimaryKey(1);
		logger.info(JSON.toJSONString(gym));
		return "success";
	}
	@RequestMapping(value="/sendMail")
	public String testSendMail(){
		MembershipDto membership = new MembershipDto();
		membership.setEmail("979300544@qq.com");
		System.out.println("send start");
		SendMailUtil.send(membership);
		System.out.println("send end");
		return "success";
	}
	
	@RequestMapping(value="/testHead")
	public String testHead(){
		return "head";
	}
	@RequestMapping(value="/testIndex")
	public String testIndex(){
		return "index";
	}
	
	@RequestMapping(value="/testLeft")
	public String testLeft(){
		return "userLeft";
	}
	
	@ResponseBody
	@RequestMapping(value="/testSelect")
	public String testSelect(){
		TeacherDtoExample example = new TeacherDtoExample();
		example.createCriteria().andIdEqualTo(1);
		List<TeacherDto> teachers = teacherMapper.selectByExample(example);
		logger.info("teachers={}",JSON.toJSON(teachers));
		return null;
	}
	
	
	
	@RequestMapping(value="/testimExcel",method=RequestMethod.POST)
	@ResponseBody
	public String imExcel(@RequestParam(value="excelFile",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		logger.info("file.size={}",file.getSize());
		List<Object> list = ExcelUtils.excelForList(file, EquipmentImExcel.class, true);
		for (Object object : list) {
			EquipmentImExcel equipment = (EquipmentImExcel) object;
			System.out.println("equipment.getCreateTime="+ equipment.getCreateTime());
			logger.info("equipment={}",JSON.toJSONString(equipment));
			EquipmentDto dto = new EquipmentDto();
			dto.setDamage(Integer.parseInt(equipment.getDamage()));
			BeanUtils.copyProperties(equipment, dto);
			EquipmentTypeDtoExample example = new EquipmentTypeDtoExample();
			example.createCriteria().andNameEqualTo(equipment.getTypeName());
			EquipmentTypeDto typeDto = typeMapper.selectByExample(example).get(0);
			dto.setType_id(typeDto.getId());
			EquipmentDtoExample example2 = new EquipmentDtoExample();
			example2.createCriteria().andCodeEqualTo(dto.getCode());
			List<EquipmentDto> dtos = equipmentMapper.selectByExample(example2);
			if(CollectionUtils.isEmpty(dtos)){
				equipmentMapper.insertSelective(dto);
			}else{
				dto.setId(dtos.get(0).getId());
				equipmentMapper.updateByPrimaryKey(dto);
			}
			logger.info("dto={}",JSON.toJSONString(dto));
		}
		return null;
	}
}
