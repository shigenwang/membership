package com.future.membership.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtils {
	/**
	 * 创建工作簿
	 * 
	 * @return
	 */
	public static HSSFWorkbook createWorkBook() {
		return new HSSFWorkbook();
	}

	/**
	 * 创建表格
	 * 
	 * @param hssfWorkbook
	 * @param title
	 * @return
	 */
	public static HSSFSheet createSheet(HSSFWorkbook hssfWorkbook, String title) {
		HSSFSheet sheet = hssfWorkbook.createSheet(title);
		sheet.setDefaultColumnWidth(15);
		return sheet;
	}

	/**
	 * 报表导出,<b>暂不支持图片导出</b>
	 * 
	 * @param sheet
	 * @param headers
	 * @param dataSet
	 * @param pattern
	 * @param <T>
	 */
	public static <T> void fillSheet(HSSFSheet sheet, String[] headers, Collection<T> dataSet, String pattern) {

		// 创建标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> iterator = dataSet.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = iterator.next();
			Field[] fromFields = t.getClass().getDeclaredFields();

			List<Field> fieldsList = new ArrayList<Field>();
			for (int i = 0, n = fromFields.length; i < n; i++) {
				fieldsList.add(fromFields[i]);
			}

			final Field[] fields = fieldsList.toArray(new Field[fieldsList.size()]);

			for (int i = 0, n = fields.length; i < n; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else {
						textValue = value.toString();
					}

					if (textValue != null) {
						Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 报表导出,<b>暂不支持图片导出</b>
	 * 
	 * @param sheet
	 * @param headers
	 * @param dataSet
	 * @param pattern
	 */
	public static void fillSheet(HSSFSheet sheet, String[] headers, String[][] dataSet, String pattern) {

		// 创建标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		for (int index = 0, rowCount = dataSet.length; index < rowCount; index++) {
			row = sheet.createRow(index + 1);
			String[] rowDatas = dataSet[index];
			for (int i = 0, n = rowDatas.length; i < n; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(rowDatas[i]);
			}
		}
	}

	/**
	 * 合并单元格,注意这里面
	 * 
	 * @param workbook
	 *            excel表格引用对象
	 * @param outputStream
	 *            输出流
	 * @param sheetPages
	 *            合并excel表格的哪个sheet
	 * @param list
	 *            哪些单元格需要合并,int[]输入的是四个数字(开始行，结束行，开始列，结束列)
	 */
	public static void mergeTableCell(HSSFWorkbook workbook, OutputStream outputStream, List<int[]> list,
			int... sheetPages) {
		for (int sheetPage : sheetPages) {
			HSSFSheet sheet = workbook.getSheetAt(sheetPage);
			for (int[] ranges : list) {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(ranges[0], ranges[1], ranges[2], ranges[3]);
				if (ranges[1] > ranges[0] || ranges[3] > ranges[2]) {
					sheet.addMergedRegion(cellRangeAddress);
				}
			}
		}
		ExcelUtils.write2OutStream(workbook, outputStream);
	}

	public static void write2OutStream(HSSFWorkbook workbook, OutputStream outputStream) {
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean replaceModel(int sheetIndex, List<ExcelModelReplaceDto> datas, InputStream sourceStream,
			OutputStream targetStream) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(sourceStream);
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			for (ExcelModelReplaceDto dto : datas) {
				HSSFRow row = sheet.getRow(dto.getRow());
				if (row == null) {
					row = sheet.createRow(dto.getRow());
				}
				HSSFCell cell = HSSFCellUtil.createCell(row, dto.getColumn(), dto.getValue());
			}

			workbook.write(targetStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	public static List<Object> excelForList(MultipartFile file, Class<?>  beanclazz,Boolean titleExist) {
        List<Object> list = new ArrayList<Object>();
        try {
            // IO流读取文件
            InputStream input = file.getInputStream();
            // 创建文档
//            Workbook wb = new HSSFWorkbook(input);
            Workbook wb = new XSSFWorkbook(input);
            // 得到第一张工作表
            Sheet sheet = wb.getSheetAt(0);
            int i;
            if (titleExist) {
                i = 1;
            } else {
                i = 0;
            }
            // 行的遍历
            for (; i <= sheet.getLastRowNum(); i++) {
                // 得到行
                Row row = sheet.getRow(i);
                // 单元格的遍历
                // 实例化对象
                Object object = beanclazz.newInstance();

                Field[] fields = beanclazz.getDeclaredFields();
                int j = 0;
                for (Field field : fields) {
                    String fieldName = field.getName();
                    PropertyDescriptor pd = new PropertyDescriptor(fieldName, beanclazz);
                    Method getMethod = pd.getWriteMethod();
                    Cell cell = row.getCell(j++);
                    try{
            		if(cell!=null){
            			switch (cell.getCellType()) {
            			case Cell.CELL_TYPE_BLANK:   //3   空白
            				break;
            			case Cell.CELL_TYPE_BOOLEAN:   //4  布尔
            				// 返回布尔类型的值
                            boolean value = cell.getBooleanCellValue();
                            getMethod.invoke(object, value);
                            System.out.println(object);
                            System.out.println(value);
            				break;
            			case Cell.CELL_TYPE_ERROR:   //5   error
            				break;
            			case Cell.CELL_TYPE_FORMULA:  //2
            				break;
            			case Cell.CELL_TYPE_NUMERIC:   //0
            				// 返回数值类型的值
                            Double d = cell.getNumericCellValue();
                            int value1 = d.intValue();
                            System.out.println("数字：" + value1);
                            if(DateUtil.isCellDateFormatted(cell)){
                            	getMethod.invoke(object, cell.getDateCellValue());
                            	System.out.println("date:" + cell.getDateCellValue());
                            }else{
                            	DecimalFormat df = new DecimalFormat("#");
                            	String str = df.format(cell.getNumericCellValue());
                            	getMethod.invoke(object, str);
//                            	getMethod.invoke(object, new Integer(value1));
                            }
            				break;
            			case Cell.CELL_TYPE_STRING:   //1
            				// 返回字符串类型的值
                            String value2 = cell.getStringCellValue();
                            getMethod.invoke(object, new String(value2));
                            System.out.println("value: " +value2);
            				break;
            			default:
            				break;
            			}
            		}
                }catch (Exception e) {
                   System.out.println();
                }
                }
                list.add(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

	public static class Example {
		private String user;
		private int id;
		private String name;
		private int age;
		private Date birthday;

		public Example(String user,int id, String name, int age, Date birthday) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.birthday = birthday;
			this.user = user;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
	}

	/**
	 * excel 模版
	 */
	public enum ExcelModel {

	}

	public static class ExcelModelReplaceDto {

		public ExcelModelReplaceDto(int row, int column, String value) {
			this.row = row;
			this.column = column;
			this.value = value;
		}

		private int row;
		private int column;
		private String value;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	
	
	
	public static void main(String[] args) throws FileNotFoundException {

		// 自定义
		List<Example> list = new ArrayList<Example>();

		for (int i = 0; i < 10; i++) {
			Example example = new Example("ss",i, "dsf", i + 10, new Date());
			list.add(example);
		}

		HSSFWorkbook workBook = ExcelUtils.createWorkBook();
		HSSFSheet sheet = ExcelUtils.createSheet(workBook, "test");
		String[] headers = new String[] {"用户", "ID", "name", "年龄", "生日" };
		ExcelUtils.fillSheet(sheet, headers, list, "yyyy-MM-dd");
		File file = new File("D:\\owned.xls");
		
		FileOutputStream outputStream = new FileOutputStream(file);
		
		//需要合并的行数
	    int rowSize=list.size();

	    //合并excel单元格时候需要传进去的合并哪些行和列int[]{1,size,2,4}代表四个点{开始行,结束行,开始列,结束列}
	    List<int[]> mergeCoordinateList=new ArrayList<int[]>();
	    int[] workCoordinate1=new int[]{1,rowSize,0,0};
//	    int[] workCoordinate2=new int[]{1,rowSize,1,1};
	    mergeCoordinateList.add(workCoordinate1);
//	    mergeCoordinateList.add(workCoordinate2);
	    ExcelUtils.mergeTableCell(workBook,outputStream,mergeCoordinateList,0);
	    
		ExcelUtils.write2OutStream(workBook, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
