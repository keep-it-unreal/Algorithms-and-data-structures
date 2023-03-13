package org.example;


import java.util.*;

public class Main {
    public static void main(String[] args){
        int[] array = new int[26];
        int curCount = 1;
        int maxCount = 1;
        int firstFlag = 1;
        int secondFlag = 0;
        int hub;

        char curCh;
        char maxCh;

        Scanner scanner = new Scanner(System.in);
        int countRepl = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();

        maxCh = curCh = str.charAt(0);

        array[(int)curCh - 97] = 1;

        for(int i = 1; i < str.length(); ++i){
            curCh = str.charAt(i);
            if((hub = array[(int)curCh - 97]) > 0){
                if(++hub > array[(int)maxCh - 97]){
                    maxCh = curCh;
                    curCount = hub;
                }
                array[(int)curCh - 97] = hub;
            } else {
                array[(int)curCh - 97] = 1;
            }
            ++firstFlag;
            if(firstFlag - secondFlag - curCount > countRepl){
                curCh = str.charAt(secondFlag);
                hub = array[(int)curCh - 97];
                if(hub == 1){
                    array[(int)curCh - 97] = 0;
                } else {
                    array[(int)curCh - 97] = --hub;
                }
                if(curCh == maxCh) {
                    for (int j = 0; j < array.length; ++j) {
                        if(array[j] > hub){
                            maxCh = (char)(j + 97);
                            curCount = array[j];
                            break;
                        }
                    }
                }
                ++secondFlag;
            }
            maxCount = Math.max(firstFlag - secondFlag, maxCount);
        }
        if(firstFlag - secondFlag - curCount > countRepl) {
            maxCount = Math.max(firstFlag - secondFlag - 1, maxCount);
        } else {
            maxCount = Math.max(firstFlag - secondFlag, maxCount);
        }
        System.out.println(maxCount);
        }
}
