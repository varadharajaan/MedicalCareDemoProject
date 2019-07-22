package com.hackthon.dbs.medicalProvider.demo.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Varadharajan
 *
 */
public enum UserTypes {
	USER("User", 1), RETAILER("Retailer", 2) ;

	private final String type;
	private final int value;

	private UserTypes(String type, int value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	private static final Map<String, UserTypes> typeMap = new HashMap<>();

	static {
		for (final UserTypes statusType : UserTypes.values()) {
			typeMap.put(statusType.getType(), statusType);
		}
	}

	public static boolean isExists(final String statusType) {
		return typeMap.containsKey(statusType);
	}

}
