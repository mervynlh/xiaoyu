package com.yizhilu.os.common.intercepter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    /**
     * 覆盖getParameter方法，
     * getParameterNames,getParameterValues和getParameterMap
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }
    @Override
    public String[] getParameterValues(String name) {
        String[]parameters=super.getParameterValues(name);
        if (parameters==null||parameters.length == 0) {
            return null;
        }
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = xssEncode(parameters[i]);
        }
        return parameters;
    }
    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        s=s.replaceAll("<","&lt");//&lt;＜
        s=s.replaceAll(">","&gt;");//&gt;＞
        s=s.replaceAll("'","＇");
        return s;
    }
}