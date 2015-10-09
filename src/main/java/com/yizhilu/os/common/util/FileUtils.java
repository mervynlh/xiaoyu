/**
 * 
 */
package  com.yizhilu.os.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author Administrator 文件管理操作实用类
 */
public class FileUtils {
	/**
	 * 新建目录
	 * 
	 * @param filePath
	 */
	public static void createPath(String filePath) {
		filePath = filePath.toString();// 中文转换
		java.io.File myFilePath = new java.io.File(filePath);
		if (!myFilePath.exists()) myFilePath.mkdirs();
	}

	/**
	 * 新建文件
	 * 
	 * @param filePath
	 */
	public static void createFile(String filePath) throws FileUtilException {
		try {
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) myFilePath.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUtilException("创建文件错误!");
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) throws FileUtilException {
		try {
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.exists())
				myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("删除文件错误!");
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFile(String sourceFilePath, String distFilePath)	throws FileUtilException {
		try {
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(sourceFilePath);
			FileOutputStream fs = new FileOutputStream(distFilePath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件拷贝错误!");
		}
	}
	/**
	 * 文件拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFile(File file, String distFilePath,String fileName)	throws FileUtilException {
		try {
			File f = new File(distFilePath);
			if(!f.exists()){
				f.mkdirs();
			}
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(file);
			FileOutputStream fs = new FileOutputStream(distFilePath+fileName);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件拷贝错误!");
		}
	}

	/**
	 * 文件夹拷贝
	 * 
	 * @param sourceFilePath
	 * @param distFilePath
	 */
	public static void copyFilePath(String sourceFilePath, String distFilePath)	throws FileUtilException {
		try {
			(new File(distFilePath)).mkdirs();
			File[] file = (new File(sourceFilePath)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					file[i].toString();
					FileInputStream input = new FileInputStream(file[i]);
					FileOutputStream output = new FileOutputStream(distFilePath
							+ "/" + (file[i].getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件夹拷贝错误!");
		}
	}

	/**
	 * 读到流中
	 * 
	 * @param filePath
	 * @return
	 * @throws FileUtilException
	 */
	public static InputStream fileToStream(String filePath) throws FileUtilException {
		try {
			File file = new File(filePath);
			if (file.exists())
				return new FileInputStream(file);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化输出流错误!");
		}

	}

	/**
	 * 读文件到字节数组中
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static byte[] fileToByte(File file) throws FileUtilException {
		FileInputStream is=null;
		try {
			byte[] dist = null;
			if (file.exists()) {
				is = new FileInputStream(file);
				dist = new byte[is.available()];
				is.read(dist);
			}
			return dist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化字节数组错误!");
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * 读文件到字节数组中
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static byte[] fileToByte(String filePath) throws FileUtilException {
		FileInputStream is=null;
		try {
			File file = new File(filePath);
			byte[] dist = null;
			if (file.exists()) {
				is = new FileInputStream(file);
				dist = new byte[is.available()];
				is.read(dist);
			}
			return dist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileUtilException("文件转化字节数组错误!");
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args[]
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String[] args) throws IOException {
		FileWriter fw = new FileWriter(filePath + fileName);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < args.length; i++) {
			out.write(args[i]);
			out.println();
			out.flush();
		}
		fw.close();
		out.close();
	}

	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args) throws IOException {
		FileWriter fw = new FileWriter(filePath + fileName);
		fw.write(args);
		fw.close();
	}
	
	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径+文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String args) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(args);
		fw.close();
	}
	
	/**
	 * 文件的写入
	 * 
	 * @param filePath(文件路径+文件名)
	 * @param args 要写入的内容
	 * @param isUTF8 是否以UTF-8的文件编码写入文件
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String args,boolean isUTF8) throws IOException {
		if(isUTF8){
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
			out.write(args);
			out.flush();
			out.close();
		}else{
			FileWriter fw = new FileWriter(filePath);
			fw.write(args);
			fw.close();
		}
	}
	/**
	 * 文件的写入
	 * 
	 * @param filePath文件路径
	 * @param fileName 文件名
	 * @param args 要写入的内容
	 * @param isUTF8 是否以UTF-8的文件编码写入文件
	 * @throws IOException
	 */
	public static void writeFile(String filePath,String fileName, String args,boolean isUTF8) throws IOException {
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		if(isUTF8){
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath+fileName),"UTF-8");
			out.write(args);
			out.flush();
			out.close();
		}else{
			FileWriter fw = new FileWriter(filePath+fileName);
			fw.write(args);
			fw.close();
		}
	}

	/**
	 * 创建与删除文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @return 创建成功返回true
	 * @throws IOException
	 */
	public static boolean createAndDeleteFile(String filePath, String fileName)	throws IOException {
		boolean result = false;
		File file = new File(filePath, fileName);
		if (file.exists()) {
			file.delete();
			result = true;
		} else {
			file.createNewFile();
			result = true;
		}
		return result;
	}

	/**
	 * 创建和删除目录
	 * 
	 * @param folderName
	 * @param filePath
	 * @return 删除成功返回true
	 */
	public static boolean createAndDeleteFolder(String folderName, String filePath) {
		boolean result = false;
		try {
			File file = new File(filePath + folderName);
			if (file.exists()) {
				file.delete();
				System.out.println("目录已经存在，已删除!");
				result = true;
			} else {
				file.mkdir();
				System.out.println("目录不存在，已经建立!");
				result = true;
			}
		} catch (Exception ex) {
			result = false;
			System.out.println("CreateAndDeleteFolder is error:" + ex);
		}
		return result;
	}

	/**
	 * 输出目录中的所有文件及目录名字
	 * 
	 * @param filePath
	 */
	public static void readFolderByFile(String filePath) {
		File file = new File(filePath);
		File[] tempFile = file.listFiles();
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].isFile()) {
				System.out.println("File : " + tempFile[i].getName());
			}
			if (tempFile[i].isDirectory()) {
				System.out.println("Directory : " + tempFile[i].getName());
			}
		}
	}

	/**
	 * 检查文件中是否为一个空
	 * 
	 * @param filePath
	 * @param fileName
	 * @return 为空返回true
	 * @throws IOException
	 */
	public static boolean fileIsNull(String filePath, String fileName) throws IOException {
		boolean result = false;
		FileReader fr = new FileReader(filePath + fileName);
		if (fr.read() == -1) {
			result = true;
			System.out.println(fileName + " 文件中没有数据!");
		} else {
			System.out.println(fileName + " 文件中有数据!");
		}
		fr.close();
		return result;
	}

	/**
	 * 读取文件中的所有内容
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void readAllFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath + fileName);
		int count = fr.read();
		while (count != -1) {
			count = fr.read();
			if (count == 13) {
				fr.skip(1);
			}
		}
		fr.close();
	}

	/**
	 * 一行一行的读取文件中的数据
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void readLineFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath + fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
	
	/**
	 * 一行一行的读取文件中的数据,转换成字符串
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static String readLineFile(String filePath)	throws IOException {
		StringBuffer sb = new StringBuffer();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return sb.toString();
	}
	
	/**********************************/


	/**
	 * 读取文件已byte流的形式返回,并删除临时文件;
	 * 
	 * @param path
	 *            :文件在服务器上的绝对路径;
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream file2ByteArrayInputStream(String fileName)
			throws Exception {
		try {
			File file = new File(fileName);
			return file2ByteArrayInputStream(file);
		} catch (Exception e) {
			throw new Exception("将文件转换为流的过程中出现错误!");
		}
	}

	/**
	 * 读取文件已byte流的形式返回,并删除临时文件;
	 * 
	 * @param path
	 *            :文件在服务器上的绝对路径;
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream file2ByteArrayInputStream(File file)
			throws Exception {
		try {
			FileInputStream is = new FileInputStream(file);
			byte[] b = new byte[is.available()];
			is.read(b);
			is.close();
//			file.delete();
			return new ByteArrayInputStream(b);
		} catch (Exception e) {
			throw new Exception("将文件转换为流的过程中出现错误!");
		}
	}
	/**
   * 从url读取文本
   * @param strURL String url地址
   * @return String
   */
	public String readFromURL(String strURL) {
		try{
			URL url = new URL(strURL);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			String rtnStr="";
			while((str=in.readLine())!=null){
				rtnStr = rtnStr+new String(str.getBytes(), "GB2312");
			}
			in.close();
			return rtnStr;
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	/**
   * 从输入流中读取内容
   * @param is InputStream 输入流对象
   * @throws Exception
   * @return String
   */
	public String readFromIS(InputStream is) throws Exception{
		try {			
			String strRtn = "";
			int length = is.available();
			byte[] buf = new byte[length];
			while ((is.read(buf, 0, length)) != -1) {
				strRtn = strRtn + new String(buf, 0, length, "GB2312");
			}			
			return strRtn;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			is.close();
		}
	}
	
	/**
	 * 将文件夹清空
	 * @param staticPath
	 */
	public static void clearFile(String staticPath) {
		File file = new File(staticPath);
		deleteFile(file);
		file.mkdirs();
	}

	/**
	 * 递归删除文件夹下内容
	 * @param file
	 */
	public static void deleteFile(File file){ 
		   if(file.exists()){ 
		    if(file.isFile()){ 
		     file.delete(); 
		    }else if(file.isDirectory()){ 
		     File files[] = file.listFiles(); 
		     for(int i=0;i<files.length;i++){ 
		      deleteFile(files[i]); 
		     } 
		    } 
		    file.delete(); 
		   }else{ 
		    System.out.println("所删除的文件不存在！"+'\n'); 
		   } 
		} 
}
