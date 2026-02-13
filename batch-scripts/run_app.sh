#!/bin/bash

# ตั้งค่า Path และชื่อไฟล์
JAR_NAME="myapp.jar"  # <-- เปลี่ยนเป็นชื่อไฟล์จริงของคุณ
LOG_FILE="/app/scripts/logs/java_batch.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[$TIMESTAMP] Starting Java Batch..." >> $LOG_FILE

# สั่งรัน Java
# -Duser.timezone=Asia/Bangkok ช่วยให้ Log ใน Java ตรงกับเวลาไทยเป๊ะๆ
java -Duser.timezone=Asia/Bangkok -jar /app/scripts/$JAR_NAME TEST00Z TEST002 >> $LOG_FILE 2>&1

echo "[$TIMESTAMP] Batch Finished." >> $LOG_FILE