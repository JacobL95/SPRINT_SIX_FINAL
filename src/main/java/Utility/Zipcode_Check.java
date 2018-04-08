package Utility;


import org.json.JSONException;
import org.json.JSONObject;

import Structure_Model.Zipcode_model;

public class Zipcode_Check {

    public static Zipcode_model CheckZipcode (String str){

        Zipcode_model postcode = new Zipcode_model();
        try {
            JSONObject jsonObject = new JSONObject(str);
            postcode.setResult(Utilities.getString("result", jsonObject));
            postcode.setTOF(Utilities.getString("valid", jsonObject));
            postcode.setPCT(Utilities.getString("postalCodeType", jsonObject));
            return postcode;

        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }
}
