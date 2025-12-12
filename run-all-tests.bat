@echo off
rem Run all TestNG tests once via Maven
setlocal
set MAVEN_OPTS=-Xms256m -Xmx1024m
mvn clean test
exit /b %ERRORLEVEL%