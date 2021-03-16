package net.skhu.traveler;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Text {
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String title;
    Date date;
    String number;

    public Text(Date date, String title, String number){
        this.title = title;
        this.date =date;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
    public  void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber() {
        this.number = number;
    }

    public String getDateFormatted() {
        return format.format(date);
    }
}
