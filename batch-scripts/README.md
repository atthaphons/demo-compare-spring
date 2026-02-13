# 🚀 Batch Runner Setup Guide (Ubuntu 22.04 + Java 17)
สรุปขั้นตอนการเซตระบบสำหรับรัน Java Batch และ Cron Job ภายใน Docker

## 1. ไฟล์โครงสร้างระบบ (docker-compose.yaml)
สร้างไฟล์นี้ไว้ที่โฟลเดอร์หลัก เพื่อคุม Environment ให้คงที่

```yaml
version: '3.8'
services:
  batch-runner:
    image: ubuntu:22.04
    container_name: batch-runner
    volumes:
      - /mnt/d/work/batch-scripts:/app/scripts
    environment:
      - TZ=Asia/Bangkok
      - ORACLE_HOST=oracle-19c
    networks:
      - shared-data-net
    tty: true
    command: tail -f /dev/null
networks:
  shared-data-net:
    external: true
	
	
## 2. ขั้นตอนการเตรียมเครื่อง (Post-Installation)
รันคำสั่งเหล่านี้ทีละบรรทัด หลังจากที่ docker compose up -d แล้ว

Bash

# [1] ติดตั้งเครื่องมือพื้นฐานและ Java 17
docker exec -u root batch-runner bash -c "apt-get update && apt-get install -y cron openjdk-17-jdk nano tzdata"

# [2] บังคับเวลา Timezone ให้เป็นประเทศไทย (บ่ายสอง)
docker exec -u root batch-runner bash -c "ln -snf /usr/share/zoneinfo/Asia/Bangkok /etc/localtime && echo 'Asia/Bangkok' > /etc/timezone"

# [3] เริ่มทำงาน Service Cron
docker exec batch-runner service cron start

3. การจัดการไฟล์ Script และ Crontab
ตัวอย่างไฟล์รันงาน (run_app.sh)
วางไว้ที่ D:\work\batch-scripts\run_app.sh

#!/bin/bash
JAR_NAME="myapp.jar"
LOG_FILE="/app/scripts/logs/java_batch.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[$TIMESTAMP] Starting Java Batch..." >> $LOG_FILE
java -Duser.timezone=Asia/Bangkok -jar /app/scripts/$JAR_NAME >> $LOG_FILE 2>&1
echo "[$TIMESTAMP] Batch Finished." >> $LOG_FILE


การตั้งเวลา (Crontab)
เข้าไปแก้ไข: docker exec -it batch-runner crontab -e

ใส่บรรทัดนี้ลงไปท้ายไฟล์: * * * * * /bin/bash /app/scripts/run_app.sh

ออกจากหน้าจอ: กด Ctrl+O -> Enter -> Ctrl+X

รีสตาร์ท Cron: docker exec batch-runner service cron restart

4. คำสั่งที่ใช้บ่อย (Cheat Sheet)
เช็คเวลาปัจจุบัน: docker exec batch-runner date

เช็คเวอร์ชัน Java: docker exec batch-runner java -version

ดู Log การรันงาน: tail -f /mnt/d/work/batch-scripts/logs/java_batch.log

ตรวจสอบงานในคิว: docker exec batch-runner crontab -l

วิธีใช้งาน:

สั่งรัน: bash monitor_stats.sh &

วิธีหยุด: pkill -f monitor_stats.sh

1. รันที่เครื่องคุณ (WSL Terminal)
Monitoring: การรันลูป while เพื่อบันทึกค่า docker stats ลงไฟล์

Control: การสั่ง pkill หรือ kill เพื่อหยุดการบันทึก

Viewing: การใช้ tail -f เพื่อดู Log ที่กำลังบันทึก

2. รันใน Docker (Container)
Java Batch: ตัวโปรแกรม Java ที่ Crontab เป็นคนสั่งรัน

Cron Service: ตัวตั้งเวลาที่คอยปลุก Java ให้ทำงาน

pkill -f "docker stats"



เพื่อให้การ Monitoring ครั้งนี้สมบูรณ์ที่สุด ผมแนะนำให้คุณลองเปิดหน้าจอทิ้งไว้แบบนี้ครับ:

ดูสถานะ Docker: tail -f /mnt/d/work/batch-scripts/logs/docker_stats_history.log

ดูสถานะ Java Batch: tail -f /mnt/d/work/batch-scripts/logs/java_batch.log

คราวนี้พอถึงเวลาที่ Crontab สั่งรัน คุณจะเห็นความสัมพันธ์ของข้อมูลทันที เช่น:

ใน java_batch.log ขึ้นว่า Starting...

ใน docker_stats_history.log ค่า CPU % จะต้องดีดขึ้นตามไปในวินาทีเดียวกัน

ทริคสุดท้าย: หากคุณรัน Monitoring ทิ้งไว้นานๆ ไฟล์จะเริ่มใหญ่ขึ้นเรื่อยๆ ถ้าเช็คผลจนพอใจแล้ว อย่าลืมสั่ง pkill -f monitor_stats.sh เพื่อหยุดการบันทึกด้วยนะครับ