package Information;

public class Information_Directory {

    public static final String PassedString = "com.example.jacob.weatherornot.PassedString;";
    public static final String PassedStringExtra = "com.example.jacob.weatherornot.PassedStringExtra;";
    public static final String PassedInt = "com.example.jacob.weatherornot.PassedInt;";
    public static final String API_KEY = "&appid=058f647dae36f7bd10afc148478615d9";
    public static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?";
    public static final String POSTCODE_API_URL = "https://api.bring.com/shippingguide/api/postalCode.json?clientUrl=insertYourClientUrlHere&country=US&pnr=";
    public static final String ICON_API_URL = "http://openweathermap.org/img/w/";
    public static final int ZipcodeLength = 5;

    public String getPassedString(){
        return PassedString;
    }

    public String getPassedInt(){
        return PassedInt;
    }

    public String getApiKey(){
        return API_KEY;
    }

    public String getApiUrl(){
        return API_URL;
    }

    public String getIconApiUrl(){
        return ICON_API_URL;
    }




}
