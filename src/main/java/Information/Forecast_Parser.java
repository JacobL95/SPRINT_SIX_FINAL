package Information;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Structure_Model.Forecast_Model;
import Utility.Utilities;

public class Forecast_Parser {

    public static Forecast_Model GatherForecast (String str){
        Forecast_Model FullForecast = new Forecast_Model();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //Main Array
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            JSONObject jsonObjectList = jsonArray.getJSONObject(0);
            //Date One
            JSONObject mainObject = Utilities.getObj("main", jsonObjectList);
            FullForecast.setTemp(Utilities.getFloat("temp", mainObject));
            JSONArray jsonArray1 = jsonObjectList.getJSONArray("weather");
            JSONObject jsonObjectList1 = jsonArray1.getJSONObject(0);
            FullForecast.setDescription(Utilities.getString("description", jsonObjectList1));
            FullForecast.setDate(Utilities.getString("dt_txt", jsonObjectList));
            //One Of Location Grab
            JSONObject jsonCity = Utilities.getObj("city", jsonObject);
            FullForecast.setCity(Utilities.getString("name", jsonCity));
            //Date Two
            jsonObjectList = jsonArray.getJSONObject(8);
            JSONObject mainObject2 = Utilities.getObj("main", jsonObjectList);
            FullForecast.setTemp2(Utilities.getFloat("temp", mainObject2));
            JSONArray jsonArray2 = jsonObjectList.getJSONArray("weather");
            JSONObject jsonObjectList2 = jsonArray2.getJSONObject(0);
            FullForecast.setDescription2(Utilities.getString("description", jsonObjectList2));
            FullForecast.setDate2(Utilities.getString("dt_txt", jsonObjectList));
            //Date Three
            jsonObjectList = jsonArray.getJSONObject(16);
            JSONObject mainObject3 = Utilities.getObj("main", jsonObjectList);
            FullForecast.setTemp3(Utilities.getFloat("temp", mainObject3));
            JSONArray jsonArray3 = jsonObjectList.getJSONArray("weather");
            JSONObject jsonObjectList3 = jsonArray3.getJSONObject(0);
            FullForecast.setDescription3(Utilities.getString("description", jsonObjectList3));
            FullForecast.setDate3(Utilities.getString("dt_txt", jsonObjectList));
            //Date Four
            jsonObjectList = jsonArray.getJSONObject(24);
            JSONObject mainObject4 = Utilities.getObj("main", jsonObjectList);
            FullForecast.setTemp4(Utilities.getFloat("temp", mainObject4));
            JSONArray jsonArray4 = jsonObjectList.getJSONArray("weather");
            JSONObject jsonObjectList4 = jsonArray4.getJSONObject(0);
            FullForecast.setDescription4(Utilities.getString("description", jsonObjectList4));
            FullForecast.setDate4(Utilities.getString("dt_txt", jsonObjectList));
            //Date Five
            jsonObjectList = jsonArray.getJSONObject(32);
            JSONObject mainObject5 = Utilities.getObj("main", jsonObjectList);
            FullForecast.setTemp5(Utilities.getFloat("temp", mainObject5));
            JSONArray jsonArray5 = jsonObjectList.getJSONArray("weather");
            JSONObject jsonObjectList5 = jsonArray5.getJSONObject(0);
            FullForecast.setDescription5(Utilities.getString("description", jsonObjectList5));
            FullForecast.setDate5(Utilities.getString("dt_txt", jsonObjectList));
            return FullForecast;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
