package com.hackthon.dbs.medicalProvider.demo.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Varadharajan
 *
 */
public enum StatusTypes {
	UPLOADED("Open", 1), IN_PROGRESS("work in progress", 2), COMPLETED("completed", 3), ONHOLD("OnHold", 4);

	private final String type;
	private final int value;

	private StatusTypes(String type, int value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	private static final Map<String, StatusTypes> typeMap = new HashMap<>();

	static {
		for (final StatusTypes statusType : StatusTypes.values()) {
			typeMap.put(statusType.getType(), statusType);
		}
	}

	public static boolean isExists(final String statusType) {
		return typeMap.containsKey(statusType);
	}

}
