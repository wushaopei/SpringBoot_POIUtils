package com.example.poiutis.utils.CSV;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.Date;  
  
import org.apache.commons.lang.time.DateFormatUtils;  
  
import com.Ostermiller.util.CSVPrint;  
import com.Ostermiller.util.CSVPrinter;  

public class CsvFilePrinter {
	
	private CSVPrint csvPrint;  
	  
    /**  
     *   
     * @param fileName 文件路径  
     * @param append 是否支持追加  
     * @throws IOException  
     */  
    public CsvFilePrinter(String fileName,boolean append) throws IOException {   
    	File file = new File(fileName);  
        if(!file.exists()){  
            csvPrint = new CSVPrinter(new FileWriter(fileName,append));  
            init();  
        }else{  
            csvPrint = new CSVPrinter(new FileWriter(fileName,append));  
            if(!append){  
                init();  
            }  
        }  
           
    }  
    
    // 表头
    public void init() throws IOException{  
        write(new String[]{"id","mac","val","date"});  
    }  
     
    public void write(String[] values) throws IOException {    
        csvPrint.writeln(values);  
    }     
    
    
    public static void main(String[] args) throws Exception {     
//	 	String csvFile = "J:\\eclipse_1\\ToolsSourceCode\\ToolsSourceCode\\felixlaunch\\LinkSignal\\2020041160741.csv";  
	 	
	 	// 使用绝对路径获取工程目录路径
	    String csvFile = System.getProperty("user.dir")+File.separator+"src\\main\\resources"+File.separator + "diagrams\\202100411160741.csv";
        CsvFilePrinter print;
		try {
//			print = new CsvFilePrinter("demo.csv",false);
			print = new CsvFilePrinter(csvFile,false);
		    for(int i=0;i<10;i++){
	            print.write(new String[]{"L03","1.91E+14","L02_1ToL03_1","L02_1", "L03_1","1E+28"});  
	        } 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }     

}
