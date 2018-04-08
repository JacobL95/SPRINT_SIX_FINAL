package Utility;

public class CONDITION_Utility {
    int PreMadeReturnableNumber;

    public int TEMP_CHECK (int integer){
        if(integer < 0){
            PreMadeReturnableNumber = 0;
        }
        if(integer <= 40 && integer >= 10){
            PreMadeReturnableNumber = 1;
        }
        if(integer <= 60 && integer >= 40){
            PreMadeReturnableNumber = 2;
        }
        if(integer <= 90 && integer >= 60){
            PreMadeReturnableNumber = 3;
        }
        if(integer >= 90){
            PreMadeReturnableNumber = 4;
        }
        return PreMadeReturnableNumber;
    }

    public int CONDITION_CHECK(String string) {
        if (string.equals("Clear")) {
            PreMadeReturnableNumber = 0;
        }
        if (string.equals("Clouds")) {
            PreMadeReturnableNumber = 1;
        }
        if (string.equals("Mist")) {
            PreMadeReturnableNumber = 2;
        }
        if (string.equals("Rain")) {
            PreMadeReturnableNumber = 3;
        }
        if (string.equals("Snow")) {
            PreMadeReturnableNumber = 4;
        }
        return PreMadeReturnableNumber;
    }

    public int WIND_CHECK(int integer){
        if(integer < 10){
            PreMadeReturnableNumber = 0;
        }
        if(integer <= 20 && integer >= 10){
            PreMadeReturnableNumber = 1;
        }
        if(integer <= 30 && integer >= 20){
            PreMadeReturnableNumber = 2;
        }
        if(integer <= 40 && integer >= 30){
            PreMadeReturnableNumber = 3;
        }
        if(integer >= 40){
            PreMadeReturnableNumber = 4;
        }
        return PreMadeReturnableNumber;
    }
}
