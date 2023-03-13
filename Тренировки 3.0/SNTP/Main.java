package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] sendingTimeArr = scanner.nextLine().split(":");
        String[] timeOnServerArr = scanner.nextLine().split(":");
        String[] backedTimeArr = scanner.nextLine().split(":");

        int sendingTime = Integer.parseInt(sendingTimeArr[2]) + Integer.parseInt(sendingTimeArr[1]) * 60 + Integer.parseInt(sendingTimeArr[0]) * 3600;

        int timeOnServer = Integer.parseInt(timeOnServerArr[2]) + Integer.parseInt(timeOnServerArr[1]) * 60 + Integer.parseInt(timeOnServerArr[0]) * 3600;

        int backedTime = Integer.parseInt(backedTimeArr[2]) + Integer.parseInt(backedTimeArr[1]) * 60 + Integer.parseInt(backedTimeArr[0]) * 3600;

        if(backedTime < sendingTime) {
            backedTime += 24 * 3600;
        }

        int timeInRoad = (int)((backedTime - sendingTime) / 2.0 + 0.5);

        int correctTime = timeOnServer + timeInRoad;

        int correctSeconds = correctTime % 60;

        int correctMinute = (int)((correctTime - correctSeconds) / 60.0 + 0.5) % 60;

        int correctHours = (int)((correctTime - correctSeconds - correctMinute * 60) / 3600.0 + 0.5) % 24;

        String hour, minute, second;

        if(correctHours < 10){
            hour = "0" + correctHours;
        } else {
            hour = "" + correctHours;
        }

        if(correctMinute < 10){
            minute = "0" + correctMinute;
        } else {
            minute = "" + correctMinute;
        }

        if(correctSeconds < 10){
            second = "0" + correctSeconds;
        } else {
            second = "" + correctSeconds;
        }

        System.out.println(hour + ":" + minute + ":" + second);
    }
}