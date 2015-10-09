package com.yizhilu.os.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class FileExportImportUtil {

    InputStream os;
    List<List<String>> list = new ArrayList<List<String>>();

    //创建工作本
    public HSSFWorkbook demoWorkBook = new HSSFWorkbook();
    //创建表
    public HSSFSheet demoSheet = demoWorkBook.createSheet("Sheet1");

    /**
     * 创建行
     *
     * @param cells
     * @param rowIndex
     */
    public void createTableRow(List<String> cells, short rowIndex) {
        //创建第rowIndex行
        HSSFRow row = demoSheet.createRow((short) rowIndex);
        for (short i = 0; i < cells.size(); i++) {
            //创建第i个单元格
            HSSFCell cell = row.createCell((short) i);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(cells.get(i));
        }
    }

    /**
     * 创建整个Excel表
     *
     * @throws SQLException
     */
    public void createExcelSheeet() throws SQLException {
        for (int i = 0; i < list.size(); i++) {
            createTableRow((List<String>) list.get(i), (short) i);
        }
    }

    /**
     * 导出表格
     *
     * @param sheet
     * @param os
     * @throws IOException
     */
    public InputStream exportExcel(HSSFSheet sheet) throws IOException {
        sheet.setGridsPrinted(true);
        HSSFFooter footer = sheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " +
                HSSFFooter.numPages());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            demoWorkBook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] ba = baos.toByteArray();
        os = new ByteArrayInputStream(ba);
        return os;
    }


    public InputStream export(List<List<String>> zlist) {
        InputStream myos = null;
        try {
            list = zlist;
            createExcelSheeet();
            myos = exportExcel(demoSheet);
            return myos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "表格导出出错，错误信息 ：" + e + "\n错误原因可能是表格已经打开。");
            e.printStackTrace();
            return null;
        } finally {
            try {
                os.close();
                if (myos != null) myos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public HSSFWorkbook getHSSFWorkbook(List<List<String>> zlist) {
        try {
            list = zlist;
            createExcelSheeet();
            //myos= exportExcel(demoSheet);
            demoSheet.setGridsPrinted(true);
            HSSFFooter footer = demoSheet.getFooter();
            footer.setRight("Page " + HSSFFooter.page() + " of " +
                    HSSFFooter.numPages());
            return demoWorkBook;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "表格导出出错，错误信息 ：" + e + "\n错误原因可能是表格已经打开。");
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 创建多文件压缩包
     * @param response
     * @param dir 文件路径
     * @param srcfile 文件file集合
     * @param expName 文件名
     */
    public static void createRar(HttpServletResponse response,String dir,List<File> srcfile,String expName){
    	 
    	 if(!new File(dir).exists()){//检测生成路径
             new File(dir).mkdirs();
         }
         File zipfile = new File(dir+"/"+expName+".rar");
         FileUtils.deleteFile(zipfile);//删除之前的压缩文件
         for(int i=0;i<srcfile.size();i++){//删除之前的xls
        	 FileUtils.deleteFile(new File(dir+"/"+expName+i+".xls"));
         }
         FileExportImportUtil.zipFiles(srcfile, zipfile);//生成压缩文件
         try {
        	// 设置response的Header 
             response.addHeader("Content-Disposition", "attachment;filename="+new String(zipfile.getName().getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
             response.addHeader("Content-Length", "" + zipfile.length());
             
	         InputStream fis = new BufferedInputStream(new FileInputStream(zipfile)); 
	         byte[] buffer = new byte[fis.available()]; 
	         fis.read(buffer); 
	         fis.close(); 
	          
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
	         toClient.write(buffer); 
	         toClient.flush(); 
	         toClient.close(); 
         } catch (IOException e) {
   			e.printStackTrace(); 
   		 }

    }
    /**
     * 创建excel
     * @param headName 表头
     * @param list 数据字符串集合
     * @param expName 文件名
     * @param dir 文件路径
     * @return
     * @throws Exception
     */
    public static File createExcel(String[] headName, List<List<String>> list, String expName,String dir) throws Exception {
    	// 格式化时间
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 创建表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        for (int y = 0; y < headName.length; y++) {//循环表头信息
            cell = row.createCell((short) y);
            cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
            cell.setCellValue(headName[y]);
        }
        for (int x = 0; x < list.size(); x++) {//循环数据信息
            row = sheet.createRow(x + 1);
            List<String> rowString = list.get(x);
            for (int i = 0; i < rowString.size(); i++) {
                cell = row.createCell((short) i);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellValue(rowString.get(i));
            }
        }
        File file = new File(dir + "/" + expName + ".xls");//生成excel文件
        if (!new File(dir).exists()) {
            new File(dir).mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        return file;
    }
    /** 
  	  * 压缩文件  
  	  *   
  	  * @param srcfile File[] 需要压缩的文件列表  
  	  * @param zipfile File 压缩后的文件  
  	  * @author 
  	  */  
  	public static void zipFiles(List<File> srcfile, File zipfile) {
  		byte[] buf = new byte[1024];
  		String ZIP_ENCODEING = "GBK"; 
  		try {
  			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
  			out.setEncoding(ZIP_ENCODEING);
  			for (int i = 0; i < srcfile.size(); i++) {
  				File file = srcfile.get(i);
  				FileInputStream in = new FileInputStream(file);
  				out.putNextEntry(new ZipEntry(file.getName()));
  				int len;
  				while ((len = in.read(buf)) > 0) {
  					out.write(buf, 0, len);
  				}
  				out.closeEntry();
  				in.close();
  			}
  			out.close();
  		} catch (IOException e) {
  			e.printStackTrace(); 
  		}
  	}
}
    
    
  

