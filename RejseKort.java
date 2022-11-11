import java.util.Map;
import java.util.HashMap;

public class RejseKort {

    private int balance;
    private boolean checkedIn;
    private int lastCheckIn;
    private Map<String, Integer> coordinates;

    public RejseKort() {
        balance = 100;
        checkedIn = false;
        lastCheckIn = 0;
        coordinates = new HashMap<>();
        coordinates.put("maxX", Integer.MIN_VALUE);
        coordinates.put("minX", Integer.MAX_VALUE);
        coordinates.put("maxY", Integer.MIN_VALUE);
        coordinates.put("minY", Integer.MAX_VALUE);
    }

    public void depositMoney(int dkk) {
        if (dkk >= 0) {
            balance += dkk;
            System.out.println(dkk + " DKK deposited. New balance: " + balance + " DKK");
        } else {
            System.out.println("Error: Cannot deposit negative amount");
        }
    }

    public boolean isCheckedIn(int timeStamp) {
        if (checkedIn && lastCheckIn + 120 >= timeStamp) {
            return true;
        }
        return false;
    }

    public void checkIn(int x, int y, int timeStamp) {
        
        if (x > coordinates.get("maxX")) {
            coordinates.put("maxX", x);
        } else if (x < coordinates.get("minX")) {
            coordinates.put("minX", x);
        }

        if (y > coordinates.get("maxY")) {
            coordinates.put("maxY", y);
        } else if (y < coordinates.get("minY")) {
            coordinates.put("minY", y);
        }
        
        if (!checkedIn || lastCheckIn + 100 < timeStamp) {
            checkedIn = true;
            lastCheckIn = timeStamp;
            System.out.println("Checked in");
        } else {
            System.out.println("Continued journey (" + (timeStamp - lastCheckIn) + " minutes since the last check in)");
            lastCheckIn = timeStamp;
        }
    }

    public void checkOut(int x, int y, int timeStamp) {
        
        if (!checkedIn) {
            System.out.println("Error: Cannot check out; Not currently checked in"); //might need to checkout automatically, when there is more time than 120 mins from last checkin
            return;
        }

        checkedIn = false;
        int price = 12 + (coordinates.get("maxX") - coordinates.get("minX") + coordinates.get("maxY") - coordinates.get("minY")) * 3;
        
        int finalPrice;

        if (price < 12) {
            finalPrice = 12;
        } else if (price > 50) {
            finalPrice = 50;
        } else {
            finalPrice = price;
        }
        
        balance -= finalPrice;
        System.out.println("Checked out! " + (timeStamp - lastCheckIn) + " minutes since the last check in. Price is " + finalPrice + " DKK, remaining balance is " + balance + " DKK");
    }

}