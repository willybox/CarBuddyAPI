package fr.carbuddy.enumeration.string;

public enum StatusUser {
	BUDDY(0), DRIVER(1)
	;
	
	private int value;
	
	private StatusUser(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}
}
