package model;

public class Test {
    String duration;
    String method;
    String name;
    String startTime;
    String endTime;
    String status;

    String sid;
    String env;

    public Test() { }

    public Test(String duration, String method, String name, String startTime, String endTime, String status) {
        this.duration = duration;
        this.method = method;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Test(String sid, String name, String method, String env) {
        this.sid = sid;
        this.name = name;
        this.method = method;
        this.env = env;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSid() {
        return sid;
    }

    public String getEnv() {
        return env;
    }

    @Override
    public String toString() {
        return "Test{" +
                "duration='" + duration + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}