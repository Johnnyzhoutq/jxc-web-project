package com.gms.util;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUIDKey(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static void main(String[] args) {
		System.out.println(getUUIDKey());
	}
}
