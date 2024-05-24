package org.example;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

public class CookieLogEvent implements Serializable {
    private String timestamp;
    private LocalDate date;
    private String cookie;

    public CookieLogEvent(String date, String cookie) {
        this.timestamp=date;
        this.date = OffsetDateTime.parse(date).toLocalDate();
        this.cookie = cookie;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieLogEvent that = (CookieLogEvent) o;
        return Objects.equals(date, that.date) && Objects.equals(cookie, that.cookie);
    }

    @Override
    public int hashCode() {
        return Objects.hash( date, cookie);
    }

    @Override
    public String toString() {
        return "CookieLogEvent{" +
                "date=" + date +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
