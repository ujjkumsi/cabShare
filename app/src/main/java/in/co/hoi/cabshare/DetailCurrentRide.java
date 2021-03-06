package in.co.hoi.cabshare;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

/**
 * Created by Ujjwal on 24-06-2015.
 */
public class DetailCurrentRide {

    String pickupAddress;
    String dropAddress;
    String bookingTime;
    String startTime;
    String lastResponceTime;
    LatLng sourceCoordinates;
    LatLng destinationCoordinates;
    Double distanceIncurred;
    Double timeIncurred;
    int waitingMins;
    Double refundAmount;
    Double tollTax;
    Double costIncurred;
    LatLng cabLastCoordinates;

    public DetailCurrentRide(JSONObject rideInfo){

        try {
            System.out.println("check1");
            pickupAddress = rideInfo.getString("origaddress1") + rideInfo.getString("origaddress2");
            dropAddress = rideInfo.getString("destaddress1") + rideInfo.getString("destaddress2");
            bookingTime = rideInfo.getString("bookingtime");
            bookingTime = bookingTime.replace(bookingTime.substring(bookingTime.length() - 5), "");
            sourceCoordinates = new LatLng(rideInfo.getDouble("originx"),rideInfo.getDouble("originy"));
            destinationCoordinates = new LatLng(rideInfo.getDouble("corx"),rideInfo.getDouble("cory"));
            System.out.println("check2");

            if(rideInfo.has("lastlat") && rideInfo.has("lastlong"))
                cabLastCoordinates = new LatLng(rideInfo.getDouble("lastlat"),rideInfo.getDouble("lastlong"));
            System.out.println("check3");

            if(rideInfo.has("starttime")) startTime = rideInfo.getString("starttime");
            distanceIncurred = rideInfo.getDouble("distanceincurred");
            costIncurred = rideInfo.getDouble("costincurred");
            timeIncurred = rideInfo.getDouble("timeincurred");
            System.out.println("check4");

            if(rideInfo.has("lastresponcetime"))
                lastResponceTime = rideInfo.getString("lastresponcetime");
            waitingMins = rideInfo.getInt("waitingmins");
            refundAmount = rideInfo.getDouble("refundamount");
            tollTax = rideInfo.getDouble("tolltax");
        }
        catch(Exception e){
            Log.d("Exception", "Car Details");
        }
    }

    public DetailCurrentRide(JSONObject cabBookingDetails, JSONObject cabBookingResponce){
        try {
            bookingTime = cabBookingDetails.getString("requestdatetime");
            sourceCoordinates = new LatLng(cabBookingDetails.getDouble("origin_latitude"),cabBookingDetails.getDouble("origin_longitude"));
            destinationCoordinates = new LatLng(cabBookingDetails.getDouble("destination_latitude"),cabBookingDetails.getDouble("destination_longitude"));

            cabLastCoordinates = new LatLng(cabBookingResponce.getDouble("ridelastlat"),cabBookingResponce.getDouble("ridelastlong"));
            startTime = "";
            distanceIncurred = 0.0;
            costIncurred = 0.0;
            timeIncurred = 0.0;

            lastResponceTime = "";
            waitingMins = 0;
            refundAmount = 0.0;
            tollTax = 0.0;
        }
        catch(Exception e){
            Log.d("Exception", "Car Details");
        }
    }


}

