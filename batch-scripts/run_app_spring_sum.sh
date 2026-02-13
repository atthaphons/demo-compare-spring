#!/bin/bash

# ตั้งค่า Path และชื่อไฟล์
JAR_NAME="spring-batch-app.jar --spring.profiles.active=sum "  # <-- เปลี่ยนเป็นชื่อไฟล์จริงของคุณ
LOG_FILE="/app/scripts/logs/java_batch_manual.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[$TIMESTAMP] Starting Java Batch..." >> $LOG_FILE
PARAM1=$1
PARAM2=$2
PARAM3=$3
PARAM4=$4
PARAM5=$5
PARAM6=$6

# สั่งรัน Java
# -Duser.timezone=Asia/Bangkok ช่วยให้ Log ใน Java ตรงกับเวลาไทยเป๊ะๆ
java -Duser.timezone=Asia/Bangkok -jar /app/scripts/$JAR_NAME "$PARAM1" "$PARAM2" "$PARAM3" "$PARAM4" "$PARAM5" "$PARAM6" >> $LOG_FILE 2>&1

echo "[$TIMESTAMP] Batch Finished." >> $LOG_FILE