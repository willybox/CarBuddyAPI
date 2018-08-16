package fr.carbuddy.enumeration;

public enum Gender {
	FEMALE(0), MALE(1);
	
	private int value;

	private Gender(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}

	public static Gender getEnum(int gender) {
		if(gender == 0) {
			return FEMALE;
		}
		return MALE;
	}
}
