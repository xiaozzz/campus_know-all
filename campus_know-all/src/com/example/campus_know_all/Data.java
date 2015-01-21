package com.example.campus_know_all;

import java.util.ArrayList;

public final class Data {
	//用户信息
	
    public static String[][] USER = {
    	{"aaa","aaa","zxh"},
    	{"bbb","bbb","wfz"},
    	{"ccc","ccc","lmc"},
    	{"","","test"}  	
    };
    /*
    public static void append(String[] str){
    	ArrayList tmp = new ArrayList();
    	for (int i = 0; i < USER.length; i++)
    		tmp.add(USER[i]);
    	tmp.add(str);
    	
    	String[][] tmp = USER;
    	
    	//USER = (String[][])tmp.ToArray(typeof(String[]));
    	USER = new String[][] {};
    	int i;
    	for (i = 0; i < tmp.length; i++)
    		USER[i] = tmp[i];
    	USER[i] = str;
    }
	*/
    
	/*
    public static ArrayList USER = new ArrayList();
    
    public Data(){
    	String[] str = {"aaa","aaa","zxh"};
    	USER.add(str);
    	String[] str1 = {"bbb","bbb","wfz"};
    	USER.add(str1);  	
    	String[] str2 = {"ccc","ccc","lmc"};
    	USER.add(str2);   	
    }
    */
}