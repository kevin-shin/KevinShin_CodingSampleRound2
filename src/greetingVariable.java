import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Class used to generate greetingVariable, corresponding to the time of day. greetingVariable needs the capacity
 * to account for differences in time based on the company's timezone, and is thus constructed with a Company object.
 */

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
        //Afternoon is declared as noon to 5pm. This can easily be adjusted based on preference/status quo.
        if (12 < hours && hours <= 17 ){
            return "Good afternoon";
        }
        if (17 < hours && hours <= 24){
            return "Good evening";
        }
        return "";
    }
}