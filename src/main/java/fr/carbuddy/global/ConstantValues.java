package fr.carbuddy.global;

public class ConstantValues {

    /**
     * ==============================
     *          SPECIAL KEYS
     * ==============================
     */
    public static final String ATT_DAO_FACTORY = "daofactory";
    

    /**
     * ==============================
     *          FOREIGN KEYS
     * ==============================
     */

	public static final String ADDRESS_ID = "addressId";
	public static final String PERSON_ID = "personId";
	public static final String USER_ID = "userId";
	public static final String VEHICLE_ID = "vehicleId";
	public static final String BUDDY = "buddy";
	public static final String BUDDY_PROFILE = "buddyProfile";
	public static final String DRIVER = "driver";
	public static final String DRIVER_PROFILE = "driverProfile";
	public static final String RENDEZ_VOUS = "rendezVous";
	public static final String TERMINUS = "terminus";
	
    
    
    /**
     * ==============================
     *           ATTRIBUTES
     * ==============================
     */
    /** -- GENERAL -- */
	public static final String ID = "id";

    /** -- USER -- */
	public static final String USERNAME = "username";
	public static final String E_MAIL = "email";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PW = "confirmPW";
	public static final String AVATAR = "avatar";
	public static final String KARMA = "karma";
	public static final String PAYPAL_ID = "paypalID";
	public static final String STATUS_USER = "statusUser";

    /** -- PERSON -- */
	public static final String NAME = "name";
	public static final String FIRSTNAME = "firstname";
	public static final String GENDER = "gender";
	public static final String PHONE = "phone";
	public static final String BIRTHDAY = "birthday";

    /** -- ADDRESS -- */
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String POSTAL = "postal";
	public static final String STREET = "street";
	
	/** -- BUDDY PROFILE -- */
	public static final String DESTINATION = "destination";
	public static final String START = "start";
	public static final String TRAVEL_DATE = "travelDate";
	
	/** -- DRIVER PROFILE -- */
	public static final String PREFERED_START = "preferedStart";
	public static final String MAX_BUDDIES = "maxBuddies";
	public static final String NUMBER_LUGGAGES = "numberLuggages";
	
	/** -- DRIVER PROFILE -- */
	public static final String TRAVEL_STATUS = "travelStatus";
	public static final String DATE = "date";

	/** -- VEHICLE -- */
	public static final String BRAND = "brand";
	public static final String MODEL = "model";
	public static final String TOTAL_PLACES = "totalPlaces";

}
