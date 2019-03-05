import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class greetingVariable {
    private Company company;

    public greetingVariable(Company company){
        this.company = company;
    }
    public String returnGreeting() {
        Calendar calendar = Calendar.getInstance();
        Date relevantTime = new Date(System.currentTimeMillis());
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