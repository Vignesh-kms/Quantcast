package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CookieLog {
    public Map<LocalDate, Set<CookieLogEvent>> dateCookiesMap=new HashMap<>();
    public Map<CookieLogEvent,Integer> cookieCounter=new HashMap<>();

    private void addCookie(CookieLogEvent cookieLogEvent){
        if(cookieCounter.containsKey(cookieLogEvent)){
            cookieCounter.put(cookieLogEvent,cookieCounter.get(cookieLogEvent)+1);
        }else{
            cookieCounter.put(cookieLogEvent,1);
        }
    }

    public void addLog(CookieLogEvent cookieLogEvent){
        Set<CookieLogEvent> cookies=null;
        LocalDate logDate=cookieLogEvent.getDate();
        if(!(dateCookiesMap.containsKey(logDate))){
            cookies=new HashSet<>();
        }else{
            cookies=dateCookiesMap.get(logDate);
        }
        cookies.add(cookieLogEvent);
        dateCookiesMap.put(logDate,cookies);
        addCookie(cookieLogEvent);
    }

    public List<String> getMostActiveCookie(LocalDate dateKey) throws DateNotFoundException{
        if(!(dateCookiesMap.containsKey(dateKey))){
            throw new DateNotFoundException("Exception : Date not Found - "+dateKey);
        }
        Set<CookieLogEvent> cookies=dateCookiesMap.get(dateKey);
        Integer maxFrequency=cookies.stream().map(cookie->cookieCounter.get(cookie)).max(Integer::compareTo).get();
        List<String> mostActiveCookies=cookies.stream().filter(cookie->maxFrequency.equals(cookieCounter.get(cookie))).map(CookieLogEvent::getCookie).collect(Collectors.toList());
        return mostActiveCookies;
    }
}
