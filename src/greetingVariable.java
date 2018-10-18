import java.util.*;
import java.text.*;


public class greetingVariable {
    private Company company;
    private Guest guest;

    public greetingVariable(Company company, Guest guest){
        this.company = company;
        this.guest = guest;
    }
    public String returnGreeting(long UnixTime) {
        Date relevantTime = new Date(UnixTime * 1000L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(relevantTime);
        calendar.setTimeZone(TimeZone.getTimeZone(this.company.getTimezone()));
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (0 <= hours && hours < 12) {
            return "Good morning";
        }
        if (12 < hours && hours <= 16 ){
            return "Good afternoon";
        }
        if (16 < hours && hours <= 24){
            return "Good evening";
        }
    return "";
    }
}