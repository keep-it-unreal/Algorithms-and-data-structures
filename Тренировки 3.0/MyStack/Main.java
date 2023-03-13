package org.example;

import java.util.*;
public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str;

        MyStack myStack = new MyStack();

        String[] arrayFromPush = new String[2];

        while (true){
            str = scanner.nextLine();
            if(str.equals("pop")){
                myStack.pop();
            } else if(str.equals("back")){
                myStack.back();
            } else if (str.equals("size")) {
                myStack.size();
            } else if (str.equals("clear")) {
                myStack.clear();
            } else if (str.equals("exit")) {
                break;
            } else {
                arrayFromPush = str.split(" ");
                myStack.push(Integer.parseInt(arrayFromPush[1]));
            }
        }
        System.out.println("bye");
    }
}

class MyStack{

    int[] array;

    int flag;

    public MyStack(){
        array = new int[15];
        flag = 0;
    }

    public void push(int n){
        if(flag == array.length){
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; ++i) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[flag] = n;

        ++flag;

        System.out.println("ok");
    }

    public void pop(){
        if(flag == 0){
            System.out.println("error");
            return;
        }
        int lastElement = array[--flag];
        array[flag] = 0;

        System.out.println(lastElement);
    }

    public void back(){
        if(flag == 0){
            System.out.println("error");
            return;
        }
        System.out.println(array[flag - 1]);
    }

    public void size(){
        System.out.println(flag);
    }

    public void clear(){
        array = new int[15];
        flag = 0;
        System.out.println("ok");
    }
}