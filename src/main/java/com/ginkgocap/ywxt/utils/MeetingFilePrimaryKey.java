package com.ginkgocap.ywxt.utils;

import java.sql.Timestamp;

public class MeetingFilePrimaryKey {
	public static String getPrimaryKey(){
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double numDouble = Math.random() * 100000;
        int numInt = (int)numDouble;
        String random = "" + numInt;
        if(random.length() < 5) {
        	for(int i=0; i<5-random.length(); i++) {
        		random = "0" + random;
        	}
        }
        String num = String.valueOf(time) + random;
        return num;
    }
}
