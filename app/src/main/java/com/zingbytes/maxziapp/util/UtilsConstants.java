package com.mobantica.rideIt.comman;

import com.android.volley.Request;

/**
 * Created by admin on 7/18/2016.
 */
public class UtilsConstants {

    // Contacts table name
    public static String TABLE_CAB_REQUEST = "CabRequest";
    public static String TABLE_EMERGENCY_CONTACT = "EmergencyContact";
    public static String TABLE_FAVOURITE = "Favourite";
    public static String TABLE_CITIES = "Cities";
    public static String TABLE_VEHICLE_TYPE = "VehicleType";
    public static String TABLE_NEARBYVEHICLE = "NearByVehicle";
    public static String TABLE_MARKER_ROTATION = "MarkerRotation";


    //common columns
    public static String KEY_ID = "id";
    public static String KEY_CRATED_AT = "createdAt";
    public static String RADIO_SELECT_STRING = "radioString";
    public static String KEY_LOCATION_ID = "locationId";


    public static String IMEI_MOBILE_NO = "imeiMobileNo";
    public static String FCM_TOKEN = "fcmToken";
    public static String IP_ADDRRESS = "ipAddress";


    public static String TOKEN_KEY = "TOKEN-KEY";
    public static String X_API_KEY = "X-API-KEY";
    public static String X_API_KEY_VALUE = "Key@Customer123";


    //passenger details
    public static String FIRST_NAME = "firstName";
    public static String LAST_NAME = "lastName";
    public static String DATE_OF_BIRTH = "dob";
    public static String GENDER = "gender";
    public static String EMAIL = "email";
    public static String PROFILE_IMG_NAME = "profileImgName";
    public static String PASSENGER_PROFILE_IMAGE = "passengerProfileImage";
    public static String IMEI_NUMBER = "imeiNumber";
    public static String PASSENGER_FCM = "passengerFcm";


    // Notifications Table Columns names
    public static String KEY_NOTE_MESSAGE = "message";
    public static String KEY_NOTE_TYPE = "notificationType";
    public static String KEY_NOFY_DATE = "notificationDate";


    //upload photo
    public static String IMAGE_DATA = "imageData";
    public static String OLD_IMAGE_NAME = "oldImageName";

    //wallet id
    public static String PASSENGER_WALLET_ID = "passengerWalletId";
    public static String WALLET_AMOUNT = "walletAmount";
    public static String WALLET_CREATED_ON = "walletCreatedOn";
    public static String WALLET_CREATED_BY = "walletCreatedBy";
    public static String LAST_TRANSACTION_ID = "lastTransactionId";
    public static String WALLET_ID = "walletId";
    public static String CONTACT_ID = "contactId";
    public static String ICE_FULL_NAME = "iceFullName";
    public static String ICE_CONTACT_NUMBER = "iceContactNumber";

    // complete Ride History Values

    public static String RIDE_START_LATITUDE = "rideStartLatitude";
    public static String RIDE_START_LONGITUDE = "rideStartLongitude";
    public static String RIDE_END_LATITUDE = "rideEndLatitude";
    public static String RIDE_END_LONGITUDE = "rideEndLongitude";
    public static String PASSENGER_ID = "passengerId";
    public static String STATUS = "status";
    public static String RIDE_ID = "rideId";
    public static String CANCEL_REASON = "cancelReason";
    public static String RIDE_STATUS = "rideStatus";
    public static String COUPON_DISCOUNT_AMOUNT = "couponDiscountAmount";
    public static String RIDE_START_ADDRESS = "rideStartAddress";
    public static String RIDE_END_ADDRESS = "rideEndAddress";
    public static String AVERAGE_DRIVER_RATINGS = "averageDriverRatings";


    public static String RIDE_STAUTS_CHANGE_TIME = "rideStatusChangeTime";
    public static String RIDE_START_TIME = "rideStartTime";
    public static String RIDE_END_TIME = "rideEndTime";
    public static String TOTAL_FARE = "totalFare";
    public static String FARE_AMOUNT = "fareAmount";
    public static String DUE_FARE = "dueFare";
    public static String AMOUNT_RECEIVED = "amountRecieved";
    public static String PAYMENT_METHOD_ID = "paymentMethodId";
    public static String APPROXIMATE_COST = "approximateCost";
    public static String COUPON_ID = "couponId";
    public static String COUPON_CODE = "couponCode";
    public static String IS_COUPON_APPLIED = "isCouponApplied";
    public static String PAYMENT_METHOD_NAME = "paymentMethodName";
    public static String PAYMENT_METHOD = "paymentMethod";
    public static final String RIDE_STATUS_CHANGE_TIME = "rideStatusChangeTime";
    public static final String BY_CASH = "Cash";
    public static final String PAYMENT_TYPE = "paymentType";
    public static final String BY_WALLET = "Wallet";
    public static final String DRIVER_RATING_STATUS = "driverRatingStatus";

    public static String RATINGS = "ratings";
    public static String REVIEW_CONTENT = "reviewContent";

    public static String VEHICLE_TYPE_ID = "vehicleTypeId";
    public static String VEHICLE_TYPE = "vehicleType";
    public static String VEHICLE_COLOR = "vehicleColor";
    public static String PLATE_NUMBER = "plateNumber";
    public static String AVAILABLE_IMAGE_URL = "availableImageUrl";
    public static String UNAVAILABLE_IMAGE_URL = "unavailableImageUrl";
    public static String VEHICLE_MODEL = "vehicleModel";

    public static String SERVICE_TAX = "serviceTax";
    public static String SURCHARGE = "surcharge";
    public static String NIGHT_FROM_TIME = "nightFromTime";
    public static String NIGHT_END_TIME = "nightEndTime";
    public static String FARE_ID = "fareId";
    public static String BASE_FARE = "baseFare";
    public static String BASE_FARE_PER_KM = "baseFarePerKm";
    public static String PER_MINUTE = "perMinute";
    public static String RATE_PER_MINUTE = "ratePerMinute";
    public static String FARE_ABOVE_MINIMUN_FARE = "fareAboveMinimumFare";
    public static String WAITING_CHARGES_PER_HOUR = "waitingChargesPerHour";
    public static String LUGGAGE_CHARGES = "luggageCharges";
    public static String NIGHT_TIMINGS = "nightTimmings";
    public static String MINIMUM_FARE = "minimumFare";
    public static String NIGHT_EXTRA_FARE_PERCENTAGE = "nightExtraFarePercentage";
    public static String NIGHT_EXTRA_FARE = "nightExtraFare";
    public static String CITY_NAME = "cityName";

    //driver id
    public static String DRIVER_ID = "driverId";
    public static String DRIVER_CURRENT_LAT = "driverCurrentLat";
    public static String DRIVER_CURRENT_LONG = "driverCurrentLong";
    public static String LOCATION_UPDATED_ON = "locationUpdatedOn";
    public static String FCM_ID = "fcmId";
    public static String MIN_DURATION = "minDuraion";
    public static String DRIVER_FIRST_NAME = "driverFirstName";
    public static String DRIVER_LAST_NAME = "driverLastName";
    public static String DRIVER_MOBILE_NO = "driverMobileNo";
    public static String DRIVER_PROFILE_IMAGE = "driverProfileImage";
    public static String RIDE_ACCEPTED_TIME = "rideAcceptedTime";
    public static String ETA = "eta";
    public static String TOTAL_TRAVEL_TIME = "totalTravelTime";
    public static String TOTAL_DISTANCE = "totalDistance";
    public static String DISTANCE = "distance";
    public static String PAYMENT_STATUS = "paymentStatus";
    public static String CURRENT_LATITUDE = "currentLatitude";
    public static String CURRENT_LONGITUDE = "currentLongitude";
    public static String DRIVER_NAME = "driverName";
    public static String AMOUNT = "amount";


    //rate driver
    public static String RATING_ID = "ratingId";

    //cities
    public static String CITY_ID = "cityId";
    public static String CITY = "city";


    public static String FAVOURITE = "favourite";


    //--
    //TX = TRANSACTION
    public static String PAYMENT_ID = "paymentId";
    public static String ACTUAL_AMOUNT = "actualAmt";
    public static String TX_MESSAGE = "txMsg";
    public static String TX_TYPE = "transactionType";
    public static String PAYMENT_DATE = "paymentDate";
    public static String PAYMENTSTATUS = "paymentStatus";
    public static final String RATING_STATUS = "ratingStatus";
    public static final String IS_PAYMENT_DONE = "isPaymentDone";


    public static boolean IS_RIDE_NAVIGATION_PAGE_BACKPRESSED = false;
    public static boolean IS_SHOW_PROGRSS_BAR = false;


    public static String PAY_FARE = "payFare";
    public static String GET_COUPONS = "getCoupons?passengerId=";
    public static String APPLY_COUPON = "applyCoupon";


    public static String FULL_NAME = "fullName";
    public static String MOBILE = "mobile";
    public static String MOBILE_NUMBER = "mobileNumber";
    public static String OTP_REASON = "otpReason";


    //-----------------
    public static String colorPrimary = "#0b295b";
    public static String textColor = "#ffffff";


    //--------------------
    public static String SOURCE_CITY = "sourceCity";
    public static String DESTINATION_CITY = "destinationCity";


    //Vehicle Type


    public static final String AUTO = "AUTO";
    public static final String PINK_AUTO = "PINK AUTO";
    public static final String TAXI = "TAXI";
    public static final String SEDAN = "SEDAN";
    public static final String SUV = "SUV";
    public static final String LUXURY = "LUXURY";


    //Intent Keys
    public static final String RIDE_ACCEPT = "RideAccept";
    public static final String CANCEL_BY_DRIVER = "cancelbydriver";
    public static final String RIDE_START = "RideStart";


    //Ride Status Keys
    public static final String A = "A";
    public static final String D = "D";
    public static final String TD = "TD";
    public static final String TP = "TP";
    public static final String C = "C";


    public static final String MESSAGE = "Message";
    public static final String TRANSACTION_ID = "TransactionId";
    public static final String TRANSACTION_STATUS = "TransactionStatus";
    public static final String TRANSACTION_AMOUNT = "TransactionAmount";


    public static String PLATE_NUMBER_VALUE = "";
    public static String VEHICLE_TYPE_VALUE = "";
    public static String VEHICLE_COLOR_VALUE = "";
    public static String RIDE_START_TIME_VALUE = "";
    public static String RIDE_END_TIME_VALUE = "";
    public static String TOTAL_FARE_VALUE = "";
    public static String RIDE_START_ADDRESS_VALUE = "";
    public static String RIDE_END_ADDRESS_VALUE = "";
    public static String PAYMENT_METHOD_NAME_VALUE = "";
    public static String DISTANCE_VALUE = "";


    //Coupon
    public static final String COUPON_NAME = "couponName";
    public static final String COUPON_DESCRIPTION = "couponDescription";
    public static final String MAX_AMOUNT = "maxAmount";
    public static final String RADIO_FLG = "radioflg";


    //notification
    public static final String PASSENGER_ACTIVITY_ID = "passengerActivityId";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "Description";
    public static final String CREATED_ON = "createdOn";


}
