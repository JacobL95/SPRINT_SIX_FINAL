package Information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Structure_Model.Location_Model;
import Structure_Model.Weather_Hub_Model;
import Utility.Utilities;

public class JSON_Parser {

    public static Weather_Hub_Model getWeather(String data){
        Weather_Hub_Model weather = new Weather_Hub_Model();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Location_Model location = new Location_Model();
//          Coordinate Getter
            JSONObject coordinateObject = Utilities.getObj("coord", jsonObject);
            location.setLat(Utilities.getFloat("lat", coordinateObject));
            location.setLon(Utilities.getFloat("lon", coordinateObject));
//          System Object Getter
            JSONObject systemObject = Utilities.getObj("sys", jsonObject);
            location.setCity(Utilities.getString("name", jsonObject));
            weather.location = location;
//          Weather Information Getter
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setDescription(Utilities.getString("description", jsonObjectWeather));
            weather.currentCondition.setCondtion(Utilities.getString("main", jsonObjectWeather));
//          Main Temperature Getter
            JSONObject mainObject = Utilities.getObj("main", jsonObject);
            weather.currentCondition.setTemp(Utilities.getDouble("temp", mainObject));
            weather.currentCondition.setHumidty(Utilities.getInt("humidity", mainObject));
            weather.currentCondition.setMaxTemp(Utilities.getFloat("temp_max", mainObject));
            weather.currentCondition.setMinTemp(Utilities.getFloat("temp_min", mainObject));
//          Wind Getter
            JSONObject windObject = Utilities.getObj("wind", jsonObject);
            weather.wind.setSpeed(Utilities.getFloat("speed", windObject));
            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
/*
Got a lot of help for this parser on Ajay Murmu's Youtube Channel. Heres a link to it
https://www.youtube.com/watch?v=JPrFQcAdyyc&t=6054s
This Video was made by Paulo Dichone, it helped me figure out how to build the parser, and connections to api.
It also gave some tips on how to make setters and getters for easy access.
 */
