package com.example.campus_know_all;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.util.Base64;

public final class Data {
	
    public static final String SERVER = "http://192.168.0.4/php/";
    
    
    public static final String base64(String content) throws UnsupportedEncodingException{
    	content = Base64.encodeToString(content.getBytes("utf-8"),Base64.DEFAULT);
    	content = URLEncoder.encode(content);
    	return content;
    }
}