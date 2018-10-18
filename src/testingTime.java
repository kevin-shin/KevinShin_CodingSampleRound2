import java.util.*;
import java.text.*;


public class testingTime {

    public static void main(String[] args) {
        final Date currentTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");

// Give it to me in US-Pacific time.
        sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println("US-Pacific time: " + sdf.format(currentTime));

// Give it to me in GMT-0 time.
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT time: " + sdf.format(currentTime));

// Or maybe Zagreb local time.
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Zagreb"));
        System.out.println("Zagreb time: " + sdf.format(currentTime));

// Even 10 hours and 10 minutes ahead of GMT
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0010"));
        System.out.println("10/10 ahead time: " + sdf.format(currentTime));
    }
}