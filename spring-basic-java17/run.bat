@echo off
echo Starting Spring Boot Application...
set param=%1
#set paramB=%2
java -jar target/demo-0.0.1-SNAPSHOT.jar   %param%
echo Application stopped.
EXIT 0
