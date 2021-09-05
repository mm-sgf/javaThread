package com.cfox.thread.Queue;

public class StrUtils {
    public static void main(String[] args) {
        StrUtils strUtils = new StrUtils();
        System.out.println("str--->" + strUtils.StrChange("1234567890"));
    }


    public String StrChange(String str){
        if (str.length() > 3){
            return StrChange(str.substring(0,str.length() - 3)) + "," + str.substring(str.length() - 3,str.length());
        }
        return str;
    }
}