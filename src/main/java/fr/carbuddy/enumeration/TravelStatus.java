package fr.carbuddy.enumeration;

public enum TravelStatus {
	/** Calculated on GPS if driver is at 0km from meeting point */
	DRIVER_WAITING(0),
	/** Calculated on GPS if buddy is at 0km from meeting point */
	BUDDY_WAINTING(1),
	/** Calculated on GPS if buddy AND driver are at 0km from meeting point */
	NOT_STARTED(2),
	/** If status was NOT_STARTED then they start moving together */
	ONGOING(3),
	/** Buddy and driver stopped around 0 - limit km set by buddy */
	SUCCESS(4),
	/** <p>If buddy or driver has been fooled</p>
	 * <p><u>Reasons:</u></p>
	 * <ul>
	 *   <li>Bad payment (Buddy's fault -50 points)</li>
	 *   <li>Bad behaviour (less than 5/10 on travel feedback -1 to -3)</li>
	 *   <li>Destination is not what was requested (Driver's
	 *   fault, because destination can be changed on
	 *   ONGOING travels or status before -10)</li>
	 *   <li>No refund on fail (Driver's fault -50 points)</li>
	 * </ul>
	 */
	FOOLED(5),
	/** If accident of any type that stops the travel */
	FAILED(6),
	/** If buddy and driver accept to cancel travel, otherwise -> fooled */
	CANCELLED(7),
	;
	
	private int value;

	private TravelStatus(int val) {
		this.value = val;
	}
 
	public static TravelStatus getStatus(int val) {
		for(TravelStatus status : values()) {
			if(status.getValue() == val) {
				return status;
			}
		}
		return null;
	}
	
	public int getValue() {
		return value;
	}
 }
