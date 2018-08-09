package fr.carbuddy.enumeration.string;

public enum Message {
	NEGATIVE_ID_EXCEPTION("The id can not be negative"),
	;
	
	private String text;

	private Message(String val) {
		this.text = val;
	}
	
	public String toString() {
		return text;
	}
}
