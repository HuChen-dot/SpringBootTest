package com.zeyu.demo.util.excle.in;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zeyu.demo.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import com.zeyu.demo.util.excle.in.Excels.Type;


/**
 * @program: SpringBootTest
 * @description: excle表格导入成list
 * @author: chenhu
 * @create: 2020-11-24 19:11
 **/
@Slf4j
public class ExcelUtils<T> {
    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 100000;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Type type;
    /**
     * 工作薄对象
     */
    private Workbook wb;
    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(InputStream is) throws InvalidFormatException, InstantiationException, IllegalAccessException, IOException {
        return importExcel(StrUtil.EMPTY, is);
    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * @param sheetName 表格索引名
     * @param is        输入流
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName, InputStream is) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        this.type = Type.IMPORT;
        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
        Sheet sheet = null;
        if (StrUtil.isNotEmpty(sheetName)) {
            // 如果指定sheet名,则取指定sheet中的内容.
            sheet = wb.getSheet(sheetName);
        } else {
            // 如果传入的sheet名不存在则默认指向第1个sheet.
            sheet = wb.getSheetAt(0);
        }
        System.err.println("sheet:" + sheet);
        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }
        //获取去除空行后的行数
        int rows = sheet.getPhysicalNumberOfRows();
        //判断行数是否大于设置的行数
        if (rows > sheetSize) {
            throw new IOException("文件太大，默认" + sheetSize + "条");
        }
        //如果行数大于0
        if (rows > 0) {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表格第一行的数据
            Row heard = sheet.getRow(0);

            //获取列个数
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (ObjectUtil.isNotNull(cell)) {
                    //获取表格中的表头
                    String value = this.getCellValue(heard, i).toString();
                    //把表头和对应的位置存进map中
                    cellMap.put(value, i);
                } else {
                    cellMap.put(null, i);
                }
            }
            // 得到类的所有field（字段）
            Field[] allFields = clazz.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            //循环遍历字段集合
            for (int col = 0; col < allFields.length; col++) {
                //获取字段数组中的一个字段
                Field field = allFields[col];
                //获得字段上面的注解
                Excels attr = field.getAnnotation(Excels.class);
                //进行判断；判断注解上面的值是否和自定义的枚举值是否匹配
                if (attr != null && (attr.type() == Type.ALL || attr.type() == Type.IMPORT)) {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    //通过自己设置的注解的名字，从map中获得值
                    Integer column = cellMap.get(attr.name());
                    //把当前字段在表格中的位置和字段一起放进map中
                    fieldsMap.put(column, field);
                }
            }
            //下标从1开始；默认第一行是表头
            for (int i = 1; i < rows; i++) {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                //初始化对象
                T entity = clazz.newInstance();
                //循环字段map集合
                for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet()) {
                    //row是遍历出的每一行；entry.getKey()是取出这一行中第几列的数据
                    Object val = this.getCellValue(row, entry.getKey());
                    // 从map中得到对应列的field.
                    Field field = fieldsMap.get(entry.getKey());
                    // 取得字段类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    //把表格中的值转换成，字段的类型
                    if (String.class == fieldType) {
                        val = Convert.toStr(val);
                    } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                        val = Convert.toInt(val);
                    } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                        val = Convert.toLong(val);
                    } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                        val = Convert.toDouble(val);
                    } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                        val = Convert.toFloat(val);
                    } else if (BigDecimal.class == fieldType) {
                        val = Convert.toBigDecimal(val);
                    } else if (Date.class == fieldType) {
                        //如果当前时间是String类型
                        if (val instanceof String) {
                            //转换时间
                            val = DateUtils.parseDate(val);
                        } else if (val instanceof Double) {
                            //转换时间
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    }
                    if (ObjectUtil.isNotNull(fieldType)) {
                        String propertyName = field.getName();
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                //把对象放进集合中
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 获取单元格值
     *
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (ObjectUtil.isNotNull(cell)) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new DecimalFormat("0.00").format(val);
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

}
