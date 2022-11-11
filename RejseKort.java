public class RejseKort {

    private int balance;
    private boolean checkedIn;
    private int lastCheckIn;

    public RejseKort() {
        balance = 100;
        checkedIn = false;
        lastCheckIn = 0;
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
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in");
        }
    }

}




/* 
public void checkOut(int x, int y, int timeStamp) */