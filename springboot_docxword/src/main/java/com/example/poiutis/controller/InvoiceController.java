package com.example.poiutis.controller;


import com.example.poiutis.model.InvoiceOrder;
import com.example.poiutis.service.invoiceOrderService;
import com.example.poiutis.utils.Excel.ExcelModel;
import com.example.poiutis.utils.Excel.ExportExcel;
import com.example.poiutis.utils.XWPFDocuments.POIWordUtil;
import com.example.poiutis.utils.XWPFDocuments.ZipExportUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 发票管理
 * @author issuser
 *
 */
@RestController
@RequestMapping(value = "/webcode")
public class InvoiceController {

    private static Logger logger = LoggerFactory.getLogger(InvoiceController.class);



    @Autowired
    invoiceOrderService InvoiceOrderService;

    /**
     * 导出生活发票数据
     * @param request　HTTP request
     * @return 获取结果
     * @throws Exception 异常
     */
    //    @ApiOperation(value = "根据单号集合查询发票实体", notes = "根据消费订单号集合查询发票实体")
    @RequestMapping(value = "/invoiceOrder/getInvoiceOrderDOCX", method = RequestMethod.GET)
    public Object getInvoiceOrderDOCX(HttpServletRequest request,HttpServletResponse response,
                                      @RequestParam(value="invoiceOrders") List<String>  invoiceOrders)  {

        Map<String, Object> map = new HashMap<>();

        List<Object> list = new ArrayList<>();
        try{
            //根据单号查询数据
            List<InvoiceOrder> openInvoiceOrders = InvoiceOrderService.getInvoiceLists(invoiceOrders);

            Resource resource = new ClassPathResource("template");
            String Path = null;
            try {
                Path = resource.getFile().getPath();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //引入模板并导出数据
            File file = new File(Path+File.separator+"生活支出报销申请单.docx");

            int count = 1;
            String tempPath = null;

            if(file.exists()) {
                logger.info("当前查询到有效数据为 "+ invoiceOrders.size()+"  条");
                for (InvoiceOrder invoiceOrder : openInvoiceOrders) {

                    logger.info("引入开票模板");
                    //模板文件所在路径
                    String inputUrl = Path+File.separator+"生活支出报销申请单.docx";

//                    logger.info("当前开具票单数"+openingInvoiceOrders.size() + "为第"++"个");

                    //设置响应头类型
                   // OutputStream out = response.getOutputStream();

                    Map<String, Object> parametersMap = new HashMap<String, Object>();
                    parametersMap.put("companyName",invoiceOrder.getCompanyName());
                    parametersMap.put("taxNumbe",invoiceOrder.getTaxNumber());
                    parametersMap.put("companyName",invoiceOrder.getCompanyName());
                    parametersMap.put("companyAddress",invoiceOrder.getCompanyAddress());
                    parametersMap.put("companyTelephone",invoiceOrder.getCompanyTelephone());
                    parametersMap.put("accountBank",invoiceOrder.getAccountBank());
                    parametersMap.put("accountName",invoiceOrder.getCompanyName());
                    parametersMap.put("bankNumber",invoiceOrder.getBankNumber());

//                    C:\Users\wushaopei\AppData\Local\Temp\\

                    List<String[]> testList = new ArrayList<>();

                    String filename = "生活支出报销申请单";

                    //生成当前时间
                    Date d = new Date();
                    System.out.println(d);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String dateNowStr = sdf.format(d);
                    Thread.sleep(1000);
                    tempPath =System.getProperty("java.io.tmpdir")+File.separator;

                    //新生产的模板地址
                    filename = filename + dateNowStr;
                    logger.info("执行模板复制、替换 ${关键字}、生成word到指定目录下");

                    int size = openInvoiceOrders.size();

                    //当前有效数据为1时，直接由浏览器下载WORD文件
                    if( size == 1){
                        String outputUrl =  tempPath+filename+".docx";
                        System.out.println(outputUrl);
                        boolean b = POIWordUtil.changWord(inputUrl, outputUrl, parametersMap, null,response,filename);
                        //跳出循环
                        break;
                    }

                    String outputUrlZIP =  tempPath+"order"+File.separator+filename+".docx";
                    //当前有效数据超过1条时，直接由浏览器下载WORD文件
                    boolean b = POIWordUtil.changWords(inputUrl, outputUrlZIP, parametersMap, null,size,response,filename);

                    Thread.sleep(1000);
                    logger.info("执行模板生成word到指定目录下"+ count + "个");
                    count ++ ;

                }
                if(openInvoiceOrders.size() == 0){
                    throw new Exception("传入参数有误，查询不到有效的数据");
                }

                if(openInvoiceOrders.size() > 1 ){
                    String sourceFilePath = tempPath+"order/";
                    String zipFilePath = tempPath;
                    String fileName = "生活支出报销申请单";

                    //当前导出数据大于1时，将所有已生成word文档压缩为zip文件
                    //压缩文件路径使用系统临时文件路径

                    ServletOutputStream outputStream = response.getOutputStream();

                    ZipExportUtils.fileToZip(sourceFilePath,tempPath,fileName,response);
                }
            }

            //打印成功，返回状态为OK，失败为ERROR；
            map.put("state","0");
            map.put("message", "导出成功");
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", "1");
            map.put("message", e.getMessage());
            return map;
        }

    }


    //    @ApiOperation(value = "导出操作日志到xls文件", notes = "需要登录")
    @RequestMapping("/exporInvoiceOrderXls")
    @ResponseBody
    public Object exporInvoiceOrderXls( HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value="invoiceOrders") List<String>  invoiceOrders) {


        Map<String, Object> map = new HashMap<>();

        response.setContentType("octets/stream");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("sheetTitle", "消费记录");
        map2.put("header", new String[]{"订单号", "商店名称", "发票批次", "账户名称", "商店地址", "刷卡账号","商店电话","信用卡开户行名称"});
        map2.put("fields", new String[]{"invoiceOrder", "companyName", "taxNumber", "accountBank", "companyAddress","bankNumber","companyTelephone","accountName"});

        List<InvoiceOrder> invoiceOrderss = InvoiceOrderService.queryInvoiceLists(invoiceOrders,0,9999);
        map2.put("data",invoiceOrderss);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map2);

        String title = "日常消费刷卡发票记录";

        Map<String, short[]> mergedRegion = new HashMap<String, short[]>();
        mergedRegion.put("通用标题名称合并", new short[] {0, 0, 0, 100});

        ExportExcel<InvoiceOrder> ex = new ExportExcel<>();

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个标题样式
        HSSFCellStyle headerStyle = ex.cellStyle(workbook);
        // 生成一个内容样式
        HSSFCellStyle textStyle = ex.cellStyle(workbook);
        // 生成标题字体
        ex.setFont(workbook, headerStyle, (short) 10, HSSFFont.BOLDWEIGHT_NORMAL);
        // 生成内容字体
        ex.setFont(workbook, textStyle, HSSFFont.BOLDWEIGHT_NORMAL);

        try {
            OutputStream out = response.getOutputStream();
            // 导出文件名称添加当前时间
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //防止乱码
//            response.addHeader("Access-Control-Expose-Headers", "Content-Type,Content-Disposition");
//            response.addHeader("Content-Disposition",
//                    "attachment;filename=" + URLEncoder.encode(title, "GBK") + date
//                            + ".xls");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((title+".xls").getBytes("GB2312"),"ISO8859-1"));


            list.forEach(m -> {
                ExcelModel excelModel = new ExcelModel();
                excelModel.setWorkbook(workbook);
                excelModel.setTitle(title);
                excelModel.setFields((String[]) m.get("fields"));
                excelModel.setHeader((String[]) m.get("header"));
                excelModel.setHeaderRow(2);
                excelModel.setMergedRegion(mergedRegion);
                excelModel.setHeaderStyle(headerStyle);
                excelModel.setTextStyle(textStyle);
                excelModel.setTitles((String) m.get("sheetTitle"));

                // 生成一页表格
                HSSFSheet sheet = workbook.createSheet((String) m.get("sheetTitle"));
                excelModel.setSheet(sheet);
                ex.exportExcel(excelModel, (Collection<InvoiceOrder>) m.get("data"));
            });

            try {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                out.close();
            }

            map.put("state","0");
            map.put("message", "导出成功");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state", "1");
            map.put("message", e.getMessage());
            return map;
        }
    }
}

