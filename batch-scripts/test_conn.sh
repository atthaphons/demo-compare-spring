#!/bin/bash

# Path ของไฟล์เก็บจำนวนครั้ง และไฟล์ Log
COUNTER_FILE="/app/scripts/counter.txt"
LOG_FILE="/app/scripts/logs/test_run.log"

# ถ้ายังไม่มีไฟล์ counter ให้เริ่มที่ 0
if [ ! -f $COUNTER_FILE ]; then
    echo 0 > $COUNTER_FILE
fi

# อ่านค่าเดิมมาบวก 1
COUNT=$(cat $COUNTER_FILE)
COUNT=$((COUNT + 1))
echo $COUNT > $COUNTER_FILE

# บันทึกลง Log
echo "[$(date '+%Y-%m-%d %H:%M:%S')] Run Count: $COUNT - Executing test_conn.sh" >> $LOG_FILE

# ส่วนของ Logic เดิมของคุณ
echo "Checking connection to Oracle... (Run #$COUNT)"
# ทดสอบรัน SQL สั้นๆ หรือคำสั่งอื่นๆ
python3 -c "print('Connection Test OK')" >> $LOG_FILE 2>&1

echo "Done."