@echo off
echo [INFO] ===========Install Test .===========
cd %~dp0
cd ../../../
call mvn  compile war:war -Ptest -Dmaven.test.skip=true

echo [INFO] ===========Install Test OK .===========

set "GRUNTBAT=src/main/assembly/grunt.build.bat"

call "%GRUNTBAT%"

pause 