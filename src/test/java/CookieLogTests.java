import org.example.CookieLog;
import org.example.CookieLogEvent;
import org.example.DateNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CookieLogTests {
    @Test
    public void shouldReturnOneMostActiveCookieByDate() throws DateNotFoundException{
        CookieLog cookieLog=new CookieLog();
        CookieLogEvent cookieLogEvent1=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent2=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie102");
        CookieLogEvent cookieLogEvent3=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent4=new CookieLogEvent("2019-01-20T14:19:00+00:00","cookie104");
        cookieLog.addLog(cookieLogEvent1);
        cookieLog.addLog(cookieLogEvent2);
        cookieLog.addLog(cookieLogEvent3);
        cookieLog.addLog(cookieLogEvent4);
        assertEquals(cookieLog.getMostActiveCookie(LocalDate.parse("2019-02-01")), Arrays.asList("cookie101"));
    }

    @Test
    public void shouldReturnMultipleMostActiveCookieByDate() throws DateNotFoundException{

        CookieLog cookieLog=new CookieLog();
        CookieLogEvent cookieLogEvent1=new CookieLogEvent("2020-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent2=new CookieLogEvent("2020-02-01T14:19:00+00:00","cookie102");
        CookieLogEvent cookieLogEvent3=new CookieLogEvent("2020-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent4=new CookieLogEvent("2020-02-01T14:19:00+00:00","cookie102");
        CookieLogEvent cookieLogEvent5=new CookieLogEvent("2020-01-20T14:19:00+00:00","cookie104");
        CookieLogEvent cookieLogEvent6=new CookieLogEvent("2020-01-20T14:19:00+00:00","cookie105");
        cookieLog.addLog(cookieLogEvent1);
        cookieLog.addLog(cookieLogEvent2);
        cookieLog.addLog(cookieLogEvent3);
        cookieLog.addLog(cookieLogEvent4);
        cookieLog.addLog(cookieLogEvent5);
        cookieLog.addLog(cookieLogEvent6);
        List<String> actualCookiesReceived=cookieLog.getMostActiveCookie(LocalDate.parse("2020-02-01"));
        assertEquals(actualCookiesReceived.size(),2);
    }

    @Test
    public void shouldNotReturnMostActiveCookiePresentInTwoDates() throws DateNotFoundException {

        CookieLog cookieLog=new CookieLog();
        CookieLogEvent cookieLogEvent1=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent2=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie102"); // cookie102 present in 2019-02-01
        CookieLogEvent cookieLogEvent3=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent4=new CookieLogEvent("2019-01-20T14:19:00+00:00","cookie102"); // cookie102 present in 2019-01-01
        CookieLogEvent cookieLogEvent5=new CookieLogEvent("2019-01-20T14:19:00+00:00","cookie103");
        cookieLog.addLog(cookieLogEvent1);
        cookieLog.addLog(cookieLogEvent2);
        cookieLog.addLog(cookieLogEvent3);
        cookieLog.addLog(cookieLogEvent4);
        cookieLog.addLog(cookieLogEvent5);
        List<String> actualCookiesReceived=cookieLog.getMostActiveCookie(LocalDate.parse("2019-02-01"));
        assertEquals(actualCookiesReceived.size(),1);
        assertEquals(actualCookiesReceived.getFirst(),"cookie101");//should not return cookie102
    }

    @Test
    public void shouldThrowDateNotFoundExceptionForInvalidDate() throws DateNotFoundException {

        CookieLog cookieLog=new CookieLog();
        CookieLogEvent cookieLogEvent1=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent2=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie102"); // cookie102 present in 2019-02-01
        CookieLogEvent cookieLogEvent3=new CookieLogEvent("2019-02-01T14:19:00+00:00","cookie101");
        CookieLogEvent cookieLogEvent4=new CookieLogEvent("2019-01-20T14:19:00+00:00","cookie102"); // cookie102 present in 2019-01-01
        CookieLogEvent cookieLogEvent5=new CookieLogEvent("2019-01-20T14:19:00+00:00","cookie103");
        cookieLog.addLog(cookieLogEvent1);
        cookieLog.addLog(cookieLogEvent2);
        cookieLog.addLog(cookieLogEvent3);
        cookieLog.addLog(cookieLogEvent4);
        cookieLog.addLog(cookieLogEvent5);
        LocalDate invalidDate=LocalDate.now();
        DateNotFoundException thrownException = assertThrows(
                DateNotFoundException.class,
                () -> cookieLog.getMostActiveCookie(invalidDate),
                "Exception : Date not Found - "+invalidDate
        );
        assertEquals(thrownException.getMessage(), "Exception : Date not Found - " + invalidDate);
    }
}
