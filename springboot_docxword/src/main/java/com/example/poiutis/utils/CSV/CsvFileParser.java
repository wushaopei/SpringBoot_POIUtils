package com.example.poiutis.utils.CSV;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.InputStream;
import java.util.Arrays;

import com.Ostermiller.util.CSVPrint;
import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.ExcelCSVParser;  
import com.Ostermiller.util.LabeledCSVParser;
import org.apache.commons.lang.time.DateFormatUtils;  

import com.Ostermiller.util.CSVPrint;  
import com.Ostermiller.util.CSVPrinter;  

/**
 * @ClassName CsvFileParser.java
 * @Description TODO
 * @Author wushaopei
 * @Date 2020年4月16日
 * @Version 1.0
 */
public class CsvFileParser {
	
	private LabeledCSVParser csvParser;//csv解析器，对于第一行的表头信息，自动加载为索引关键字     
    
    private int currLineNum = -1;//文件所读到行数     
    
    private String[] currLine = null;//用来存放当前行的数据    
       
    private CSVPrint csvPrint;  
	  
    /**  
     *   
     * @param fileName 文件路径  
     * @param append 是否支持追加  
     * @return 
     * @throws IOException  
     */  
    public void CsvFilePrinter(String fileName,boolean append) throws IOException {   
        File file = new File(fileName);  
             
        if(!file.exists()){  
            csvPrint = new CSVPrinter(new FileWriter(fileName,append));   
        }else{  
            csvPrint = new CSVPrinter(new FileWriter(fileName,append));  
        }  
           
    }  
     
    public void write(String[] values) throws IOException {    
        csvPrint.writeln(values);  
    }     

    
    /*   
     *  构造函数，   
     *  Param: in InputStream 要解析的信息流   
     *  throws IOException   
     */      
    
    public CsvFileParser(InputStream in) throws IOException {    
            csvParser = new LabeledCSVParser(new ExcelCSVParser(in));    
            currLineNum = csvParser.getLastLineNumber();    
    }  
      
    public CsvFileParser(String fileName) throws IOException {   
        InputStream in = new FileInputStream(fileName);  
        csvParser = new LabeledCSVParser(new ExcelCSVParser(in));    
        currLineNum = csvParser.getLastLineNumber();   
    }  
    
    
      
    /*   
     * 检查是否还有数据   
     *   
     * return ture 还有一行数据，false 没有数据   
     */    
    public boolean hasMore() throws IOException {    
    	
        currLine = csvParser.getLine();    
        currLineNum = csvParser.getLastLineNumber()-1;    
        if (null == currLine)    
            return false;    
        return true;    
    }   
    
    /*   
     * 获取全部数据
     *   
     * return ture 还有一行数据，false 没有数据   
     */    
    public String[][] getAllValue() throws IOException {    
    	
    	String [][] csvStr = csvParser.getAllValues();
       
       
        return csvStr;    
    }   
    
    /*   
     * 返回当前行数据，关键字所指向的数据   
     * param:String filedName 该行的表头   
     * return:String 返回当前行数据，关键字所指向的数据   
     */    
    public String getByFieldName(String fieldName) {    
        return csvParser.getValueByLabel(fieldName);    
    }     
    
    /*   
     * 关闭解析器   
     *   
     *    
     */    
    public void close() throws IOException {    
        csvParser.close();     
    }     
    
    /*   
     * 读取当前行数据   
     *   
     *  return String[] 读取当前行数据   
     */    
    public String[] readLine() throws IOException {    
//        currLine = csvParser.getLine();     
        currLineNum = csvParser.getLastLineNumber()-1;     
        return currLine;    
    }     
    
   public int getCurrLineNum(){     
         return currLineNum;     
   }      
    
    public static void main(String[] args) throws Exception {     
    
	    String csvFile = System.getProperty("user.dir")+File.separator+"src\\main\\resources"+File.separator + "diagrams\\2020041160741.csv";

    	
         //创建解析信息流    
        InputStream in=new FileInputStream(new File("demo.csv"));     
    
       //实例解析器CsvFileParser     
        CsvFileParser parser=new CsvFileParser(in);     
        
       //读取数据    
        while(parser.hasMore()){
        	
       
        	String [] str =   parser.readLine();
        	Arrays.asList(str).forEach(curr -> System.out.print(curr + " "));
//            System.out.print(parser.getByFieldName("time")+" ");//time 系表头数据    
//            System.out.print(parser.getByFieldName("total")+" ");    

           System.out.println();

        }    
      
            
    
    }     

}
