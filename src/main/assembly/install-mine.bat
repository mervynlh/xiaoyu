@echo off
echo [INFO] ===========Install mine .===========
cd %~dp0
cd ../../../
call mvn  compile war:war -Pmine -Dmaven.test.skip=true
echo [INFO] ===========delete com  .===========
cd target/demo_web_v21/WEB-INF/classes/
rd/s/q com
echo [INFO] ===========delete com  success.===========
pause