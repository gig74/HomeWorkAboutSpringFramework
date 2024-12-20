package org.example.basics.homework;

import java.util.Objects;
public final class Result implements Comparable<Result>{
    private final String name;
    private final String gender;
    private final String distance;
    private final String time;

    public Result(String name, String gender, String distance, String time) {
        this.name = name;
        this.gender = gender;
        this.distance = distance;
        this.time = time;
    }

    public String name() {
        return name;
    }

    public String gender() {
        return gender;
    }

    public String distance() {
        return distance;
    }

    public String time() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Result) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.gender, that.gender) &&
                Objects.equals(this.distance, that.distance) &&
                Objects.equals(this.time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, distance, time);
    }

    @Override
    public String toString() {
        return "Result[" +
                "name=" + name + ", " +
                "gender=" + gender + ", " +
                "distance=" + distance + ", " +
                "time=" + time + ']';
    }

    private long timeInSec(String time) {
        String[] splitTime = time.split(":");
        return Integer.valueOf(splitTime[0]) * 60 + Integer.valueOf(splitTime[1]);
    }

    @Override
    public int compareTo(Result o) {
        //  For Ascending order
        return (int) (timeInSec(this.time()) - timeInSec(o.time())) ;
    }
}
