#!/bin/bash

# ตั้งค่า Path และชื่อไฟล์
JAR_NAME="manual-batch-1.0-SNAPSHOT-jar-with-dependencies.jar"  # <-- เปลี่ยนเป็นชื่อไฟล์จริงของคุณ
LOG_FILE="/app/scripts/logs/java_batch_manual.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[$TIMESTAMP] Starting Java Batch..." >> $LOG_FILE
PARAM1=$1
PARAM2=$2

# สั่งรัน Java
# -Duser.timezone=Asia/Bangkok ช่วยให้ Log ใน Java ตรงกับเวลาไทยเป๊ะๆ
java -Duser.timezone=Asia/Bangkok -jar /app/scripts/$JAR_NAME "$PARAM1" "$PARAM2" >> $LOG_FILE 2>&1

echo "[$TIMESTAMP] Batch Finished." >> $LOG_FILE