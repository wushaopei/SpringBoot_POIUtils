package com.example.poiutis.utils.XWPFDocuments;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class POIWordUtil{

    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     *
     * @param inputUrl  模板存放地址
     * @param outputUrl 新文档存放地址
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     * @return 成功返回true, 失败返回false
     */
    public static boolean changWords(String inputUrl, String outputUrl,
                                    Map<String, Object> textMap, List<String[]> tableList, Integer size, HttpServletResponse response,String filename) {

        //模板转换默认成功
        boolean changeFlag = true;

        try {
            //获取docx解析对象
            CustomXWPFDocument document = new CustomXWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //解析替换文本段落对象
            POIWordUtil.changeText(document, textMap);
            //解析替换表格对象
            POIWordUtil.changeTable(document, textMap, tableList);

            //生成新的word
            File file = new File(outputUrl);
                if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
                //直接输出到临时目录下
                FileOutputStream stream = new FileOutputStream(file);
                document.write(stream);
                stream.close();
//            try {
//                Thread.sleep(600L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        } catch (IOException e) {
            e.printStackTrace();

            changeFlag = false;
        }


        return changeFlag;

    }
    public static boolean changWord(String inputUrl, String outputUrl,
                                     Map<String, Object> textMap, List<String[]> tableList, HttpServletResponse response,String filename) {

        //模板转换默认成功
        boolean changeFlag = true;

        try {
            //获取docx解析对象
            CustomXWPFDocument document = new CustomXWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //解析替换文本段落对象
            POIWordUtil.changeText(document, textMap);
            //解析替换表格对象
            POIWordUtil.changeTable(document, textMap, tableList);
            String name=filename+".docx";
            response.setContentType("octets/stream");
            // 防止乱码
//            response.addHeader("Content-Disposition", "attachment;filename="
//                    + URLEncoder.encode(name, "UTF-8"));
                response.setHeader("Content-Disposition", "attachment; filename=" + new String((filename+".docx").getBytes("GB2312"),"ISO8859-1"));
                response.setContentType("application/x-msdownload;");
//                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                document.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();

            changeFlag = false;
        }

        return changeFlag;

    }


    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, Object> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    run.setText((String) changeValue(run.toString(), textMap), 0);
                }
            }
        }

    }

    /**
     * 替换表格对象方法
     *
     * @param document  docx解析对象
     * @param textMap   需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     */
    public static void changeTable(CustomXWPFDocument document, Map<String, Object> textMap,
                                   List<String[]> tableList) {
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            if (table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (checkText(table.getText())) {
                    List<XWPFTableRow> rows = table.getRows();
                    //遍历表格,并替换模板
                    eachTable(document, rows, textMap);
                } else {
//                  System.out.println("插入"+table.getText());
                    insertTable(table, tableList);
                }
            }
        }
    }


    /**
     * 遍历表格，包含对图片内容的替换
     *
     * @param rows    表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(CustomXWPFDocument document, List<XWPFTableRow> rows, Map<String, Object> textMap) {
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if (checkText(cell.getText())) {
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
//                            run.setText(changeValue(run.toString(), textMap),0);

                            Object ob = changeValue(run.toString(), textMap);
                            if (ob instanceof String) {
                                run.setText((String) ob, 0);
                            } else if (ob instanceof Map) {
                                run.setText("", 0);

                                Map pic = (Map) ob;
                                int width = Integer.parseInt(pic.get("width").toString());
                                int height = Integer.parseInt(pic.get("height").toString());
                                int picType = getPictureType(pic.get("type").toString());
                                byte[] byteArray = (byte[]) pic.get("content");
                                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                                try {

                                    String ind = document.addPictureData(byteInputStream, picType);
                                    document.createPicture(run, ind, document.getNextPicNameNumber(picType), width, height);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        for (int i = 1; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for (int i = 1; i < rows.size(); i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i - 1)[j]);
            }
        }
    }

    /**
     * 判断文本中时候包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = false;
        if (text.indexOf("$") != -1) {
            check = true;
        }
        return check;
    }

    /**
     * 匹配传入信息集合与模板
     *
     * @param value   模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static Object changeValue(String value, Map<String, Object> textMap) {
        Object obj = null;
        Set<Map.Entry<String, Object>> textSets = textMap.entrySet();
        for (Map.Entry<String, Object> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${" + textSet.getKey() + "}";
            if (value.indexOf(key) != -1) {
                obj = textSet.getValue();
            }
        }

        if (!(obj instanceof Map)) {
            //模板未匹配到区域替换为空
            if (obj != null && checkText(obj.toString())) {
                obj = "";
            }
        }
        return obj;
    }

    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * 将输入流中的数据写入字节数组
     *
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
        return byteArray;
    }

}