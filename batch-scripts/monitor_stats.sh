#!/bin/bash
LOG_FILE="/mnt/d/work/batch-scripts/logs/docker_stats_history.log"

echo "Monitoring started at $(date)" >> $LOG_FILE
while true; do 
  echo -n "$(date '+%Y-%m-%d %H:%M:%S') " >> $LOG_FILE
  docker stats batch-runner --no-stream --format "{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}" >> $LOG_FILE
  sleep 2  # ปรับเป็น 5 วินาทีเพื่อไม่ให้ไฟล์ใหญ่เกินไป
done