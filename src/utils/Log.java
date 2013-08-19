package utils;

/**
 * Copyright (c) 2013, Inmite s.r.o. (www.inmite.eu). All rights reserved.
 * <p/>
 * This source code can be used only for purposes specified by the given license contract
 * signed by the rightful deputy of Inmite s.r.o. This source code can be used only
 * by the owner of the license.
 * <p/>
 * Any disputes arising in respect of this agreement (license) shall be brought
 * before the Municipal Court of Prague.
 */
public class Log {
	public static final boolean DEBUG = false;

	public static void d(String output) {
		if (DEBUG) {
			System.out.println(output);
		}
	}

	public static void e(Exception e) {
		e.printStackTrace();
	}
}
