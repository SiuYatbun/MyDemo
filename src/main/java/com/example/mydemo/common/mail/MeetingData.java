package com.example.mydemo.common.mail;

import java.time.ZoneId;
import java.util.UUID;

public class MeetingData {
    private String to;
    private String startTime;
    private String endTime;
    private final String uid;
    private final ZoneId zoneId;
    private String location;

    public MeetingData() {
        this.uid = UUID.randomUUID().toString();
        this.zoneId = ZoneId.systemDefault();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUid() {
        return uid;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
