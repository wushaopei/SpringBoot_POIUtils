package com.example.poiutis.utils.XWPFDocuments;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 解压和压缩工具
 * @ClassName ZipExportUtils
 * @Description TODO
 * @Author wushaopei
 * @classname ZipUtil
 * @Date 2019/7/15 16:55
 * @Version 1.0
 */
@Component
public class ZipExportUtils {

    private static Logger logger = LoggerFactory.getLogger(ZipExportUtils.class);


    private static String location;

    @Value("${img.location}")
    public void setLocation(String location){
        ZipExportUtils.location = location;
    }

    /**
     * 压缩文件
     *
     * @param sourceFilePath 待压缩文件夹
     * @param zipFilePath    压缩文件存放路径
     * @param fileName       压缩文件名
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName,HttpServletResponse response) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream fos = null;
        ZipOutputStream zos = null;

        if (!sourceFile.exists()) {
            logger.info("待压缩的文件目录：" + sourceFilePath + "不存在.");
        } else {
            try {
                File zipFile = new File(zipFilePath + File.separator + fileName + ".zip");
                if (zipFile.exists()) {
                    logger.info(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
                }
                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFiles || sourceFiles.length < 1) {
                    logger.info("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                } else {
                    logger.info("开始压缩：");
//                    fos = new FileOutputStream(zipFile);
//                    zos = new ZipOutputStream(new BufferedOutputStream(fos));

                    fos =response.getOutputStream();
                    zos =new ZipOutputStream(fos);

                    response.setContentType("application/zip");
                    response.setHeader("Connection","close");//表示不能用浏览器直接打开
                    response.setHeader("Accept-Ranges","bytes");//告诉客户端允许端点续传多线程连接下载
                    response.setHeader("Content-Disposition",
                            "attachment;filename="+new String((fileName+".zip").getBytes("GB2312"),"ISO8859-1"));
                    response.setCharacterEncoding("UTF-8");

                   /* response.setContentType("application/zip");*/

                    String name=fileName+".zip";
                    response.setContentType("octets/stream");
                    // 防止乱码
//                    response.addHeader("Content-Disposition", "attachment;filename="
//                            + URLEncoder.encode(name, "UTF-8"));

                    byte[] bufs = new byte[1024 * 10];

                    for (int i = 0; i < sourceFiles.length; i++) {
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                        fis.close();
                        bis.close();
                        //删除文件
                        sourceFiles[i].delete();

                    }

                    flag = true;
                }

            } catch (FileNotFoundException e) {
                logger.info("压缩失败，文件找不到");
                throw new RuntimeException(e);
            } catch (IOException e) {
                logger.info("压缩失败，压缩输出异常");
                throw new RuntimeException(e);
            } finally {
                //关闭流
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != zos) {
                        zos.close();
                    }
                } catch (IOException e) {
                    logger.info("关闭流失败");
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    /**解压zip文件
     * @param folderLocationPath 待解压的文件
     * @param unZipFolderPath 解压后的文件夹
     * @return 返回解压后的文件绝对路径
     */
    public static String unZip(String folderLocationPath,String unZipFolderPath) {
        try {
            ZipFile zip = new ZipFile(folderLocationPath, Charset.forName("GBK"));
            String decompressionPath = unZipFolderPath;
            File pathFile = new File(decompressionPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }else {
                if(pathFile.listFiles().length!=0){
                    for(File file : pathFile.listFiles()){
                        file.delete();
                    }
                }
            }
            logger.info("开始解压");
            for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                logger.info("待解压文件：" + zipEntryName);
                InputStream in = zip.getInputStream(entry);
                String outPath = (decompressionPath + File.separator + zipEntryName).replaceAll("\\*", "/");
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                //输出文件路径信息
                logger.info("解压输出路径：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();

            }
            return decompressionPath;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解压失败");
            return null;
        }
    }

}
