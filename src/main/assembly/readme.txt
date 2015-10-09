
#项目部署说明
过滤文件在src/main/filter中数据库、memcached配置
打包 开发 环境执行install-mine.bat
打包上 线  环境执行install-release.bat
tomcat7插件执行tomcat7-mine.bat，默认启动80端口 访问sns.supergenius.cn 需写hosts



#开发基本结构:
1.包名：com.supergenius.sns
2.静态资源文件src/main/webapp/static
 JSP统一放在src/main/webapp/WEB-INF/jsp
系统管理资源文件：src/main/webapp/static/admin/
jsp:src/main/webapp/jsp/admin/
3.系统管理class统一放在sysuser包下
action:com.supergenius.sns.action.sysuser
dao:com.supergenius.sns.dao.sysuser
dao实现:com.supergenius.sns.dao.impl.sysuser
service:com.supergenius.sns.service.sysuser
service实现:com.supergenius.sns.service.impl.sysuser
目的：后台如果需要独立运行可以只保留
公用类、sysuser包、/static/admin、/jsp/admin/ 四部分即可
