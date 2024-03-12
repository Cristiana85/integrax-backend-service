package com.integrax.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assert {
	
	public static void notNull(Object obj, String objName) throws Exception {
		Optional.ofNullable(obj).orElseThrow(() -> new Exception(objName + " is null"));		
	}
	
	public static <T> void notNullNotEmpty(String obj, String objName) throws Exception {
		if (obj == null || obj.isEmpty()) {
			String msg = objName + " is null or empty";
			throw new Exception(msg);
		}
	}
	
	public static void notNullNotEmpty(Object[] obj, String objName) throws Exception {
		if (obj == null || obj.length == 0) {
			String msg = objName + " is null or empty";
			throw new Exception(msg);
		}
	}
	
	public static <T> void notNullNotEmpty(Collection<T> obj, String objName) throws Exception {
		if (obj == null || obj.isEmpty()) {
			String msg = objName + " is null or empty";
			throw new Exception(msg);
		}
	}
	
	public static <E, T> void notNullNotEmpty(Map<E, T> obj, String objName) throws Exception {
		if (obj == null || obj.isEmpty()) {
			String msg = objName + " is null or empty";
			throw new Exception(msg);
		}
	}
	
	public static void notNullNotEmpty(Properties obj, String objName) throws Exception {
		if (obj == null || obj.isEmpty()) {
			String msg = objName + " is null or empty";
			throw new Exception(msg);
		}
	}
	
	public static boolean notNullError(Object obj, @NonNull String objName) {
		if(obj == null) {
			log.error(String.format("%s is null", objName));
		}
		return obj != null;
	}
	
	public static boolean notNullWarn(Object obj, @NonNull String objName) {
		if(obj == null) {
			log.warn(String.format("%s is null", objName));
		}
		return obj != null;
	}
	
	public static boolean notNullInfo(Object obj, @NonNull String objName) {
		if(obj == null) {
			log.info(String.format("%s is null", objName));
		}
		return obj != null;
	}
	
	public static boolean notNullDebug(Object obj, @NonNull String objName) {
		if(obj == null) {
			log.debug(String.format("%s is null", objName));
		}
		return obj != null;
	}
}
