package com.slokam.healthcare.utils;

import java.util.Objects;

import com.slokam.healthcare.entity.Patient;

public class StringUtils {
	
	public static boolean blankcheck(String str) {
		boolean flag =false;
		if(str != null && str.trim().length() > 0) {
			flag= true;
		}
		return flag;
	}
	

}
