package net.skhu.bum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule implements Serializable {
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
    int memId;
    int id;
    Date start;
    Date end;
    int number;
    String destination;
    boolean checked;

    public Schedule(Schedule schedule){
        this.memId=schedule.memId;
        this.id = schedule.id;
        this.start = schedule.start;
        this.end = schedule.end;
        this.number = schedule.number;
        this.destination = schedule.destination;
    }
    public Schedule(int memId, int id , Date start, Date end, int number, String destination){
        this.memId = memId;
        this.id = id;
        this.start = start;
        this.end = end;
        this.number = number;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public String getDateFormattedStart() {
        return format.format(start);
    }
    public String getDateFormattedEnd() {
        return format.format(end);
    }
}
