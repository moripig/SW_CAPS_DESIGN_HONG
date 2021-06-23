package net.skhu.bum;

import java.io.Serializable;

public class SeoulInfo implements Serializable {
    String nameGu;
    String today;
    String total;
    String x;
    String y;

    public void setNameGu(String nameGu){
        this.nameGu = nameGu;
    }
    public String getNameGu() {
        return nameGu;
    }
    public void setToday(String today ) {this.today = today;}
    public String getToday() {return today;}
    public void setTotal(String total) {this.total=total;}
    public String getTotal(){return total;}
    public String getX() {
        return x;
    }
    public void setX(String x){
        this.x = x;
    }
    public String getY() {
        return y;
    }
    public void setY(String y){
        this.y = y;
    }
    public void setXYNameGu(String x, String y, String nameGu) {
        this.x = x;
        this.y = y;
        this.nameGu = nameGu;
    }
    public void setCovidInfo(String total, String today){
        this.total = total;
        this.today = today;
    }


}
