@echo off
echo [INFO] ===========Install release .===========
cd %~dp0
cd ../../../
call mvn clean package -Prelease -Dmaven.test.skip=true 
echo [INFO] ===========delete com  .===========
cd target/demo_web_v21/WEB-INF/classes/
rd/s/q com
echo [INFO] ===========delete com  success.===========
pause