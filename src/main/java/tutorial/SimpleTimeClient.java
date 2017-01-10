package tutorial;

import java.time.*;
/**
 * Created by geekslife on 2017. 1. 10..
 */
public class SimpleTimeClient implements TimeClient {
    private LocalDateTime dateAndTime;

    public SimpleTimeClient() {
        dateAndTime = LocalDateTime.now();
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        LocalDate currentDate = LocalDate.from( dateAndTime );
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }

    @Override
    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(year,month,day);
        LocalTime currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet,currentTime);
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        LocalDate dateToSet = LocalDate.of(year,month,day);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return dateAndTime.toString();
    }

    public static void main(String[] args) {
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.printf(myTimeClient.toString());
        System.out.println("Time in California: " +
                myTimeClient.getZonedDateTime("Blah blah").toString());
    }
}

interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                        int hour, int minute, int second);
    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                    "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }
    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of( getLocalDateTime(), getZoneId(zoneString));
    }
}

interface AnotherTimeClient extends TimeClient {
    // case 1. extend default method
    ZonedDateTime getZonedDateTime(String zoneString);
}

interface HandleInvalidTimeZoneClient extends TimeClient {
    // case 2. override default method
    default ZonedDateTime getZonedDateTime(String zoneString) {
        try {
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.of(zoneString));
        } catch (DateTimeException e) {
            System.err.println("Invalid zone ID: " + zoneString +
                    "; using the default time zone instead.");
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.systemDefault());
        }
    }
}


