package fr.carbuddy.enumeration;

public enum TravelStatus {
	/** Calculated on GPS if driver is at 0km from meeting point */
	DRIVER_WAITING,
	/** Calculated on GPS if buddy is at 0km from meeting point */
	BUDDY_WAINTING,
	/** Calculated on GPS if buddy AND driver are at 0km from meeting point */
	NOT_STARTED,
	/** If status was NOT_STARTED then they start moving together */
	ONGOING,
	/** Buddy and driver stopped around 0 - limit km set by buddy */
	SUCCESS,
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
	FOOLED,
	/** If accident of any type that stops the travel */
	FAILED,
	/** If buddy and driver accept to cancel travel, otherwise -> fooled */
	CANCELLED,
}
