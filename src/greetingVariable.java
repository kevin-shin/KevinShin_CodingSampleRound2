import java.util.*;
import java.text.*;


public class greetingVariable {
    private Company company;
    private Guest guest;

    public greetingVariable(Company company, Guest guest){
        this.company = company;
        this.guest = guest;
    }
    public String returnGreeting(long UnixTime){
        Date relevantTime = new Date(UnixTime*1000L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(relevantTime);
        calendar.setTimeZone(TimeZone.getTimeZone(this.company.getTimezone()));
        int hours = calendar.get(Calendar.HOUR);

        return "Hours: " + Integer.toString(hours);
    }
    public static void main(String[] args) {
        Guest guest = new Guest("Jane","Doe");
        Company company = new Company("MYCOMPANY", 21, "Minneapolis", "GMT-5");

        greetingVariable greetingVariable = new greetingVariable(company, guest);
        System.out.println(greetingVariable.returnGreeting(1486654792));


    }
}