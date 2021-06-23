package net.skhu.bum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Chaotic {

    List<Schedule> scheduleList;

    public Chaotic(List<Schedule> scheduleList ) {
        this.scheduleList = scheduleList;
    }

    private String calculate(Date date) {

        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
        List<Date> compare = result(date);
        HashMap<Date, Integer> map = new HashMap<Date, Integer>();
        double result = 0;
        map.put(new Date(),0);

        for(int i=0; i < scheduleList.size();i++) {
            Date start= scheduleList.get(i).getStart();
            Date end = scheduleList.get(i).getEnd();
            cal.setTime(start);

            for(int j = 0  ;j<= start.compareTo(end);j++) {
                cal.add(cal.DATE, j);
                Date d = new Date(cal.getTimeInMillis());
                if(map.get(d)!=null)
                    map.put(d, map.get(d)+1);
                else
                    map.put(d,0);

            }
        }

        result = 3*map.get(compare.get(0))/(map.get(compare.get(1))+map.get(compare.get(2))+map.get(compare.get(3)));

        if(result>1.3)
            return "혼잡";
        else if(result<0.7)
            return "한적";
        else
            return "보통";
    }
    private List<Date> result(Date date) {

        List<Calendar> cal = new ArrayList<Calendar>();
        List<Date> convert = new ArrayList<Date>();
        for(int i = 0 ; i < 15 ;i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            cal.get(i).add(c.DATE, -i);

        }
        convert.add(new Date(cal.get(0).getTimeInMillis()));
        convert.add(new Date(cal.get(1).getTimeInMillis()));
        convert.add(new Date(cal.get(7).getTimeInMillis()));
        convert.add(new Date(cal.get(15).getTimeInMillis()));

        return  convert;
    }
}