package Structure_Model;


import android.util.Log;

public class Optimal_Condtions_Model {

    String PreMadeString;
    String OptimalTempRating;
    int OptimalWindRating = 0;

     public String Optimal_Activity(String Activity, int temp, int condition, int wind){
         if(Activity.equals("Basketball")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 1){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Biking")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Camping")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Driving")){
             if (temp < 1){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "perfect";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "perfect";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Fishing")){
             if (temp < 1){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "perfect";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "perfect";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Football")){
             if (temp < 1){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "a little cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "to hot";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Golfing")){
             if (temp == 0){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "a little cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "to hot";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 2){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Hiking")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 3){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Kite Flying")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind < 2){
                 OptimalTempRating = "not windy enough" ;
             }
         }

         if (Activity.equals("Rock Climbing")){
             if (temp < 2){
                 OptimalTempRating = "to cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 3){
                 OptimalTempRating = "to hot";
             }
             if (condition > 1){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 1){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Running")){
             if (temp < 1){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "a little cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp > 4){
                 OptimalTempRating = "to hot";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 3){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Sailing")){
             if (temp < 3){
                 OptimalTempRating = "to cold";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "perfect";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind < 1){
                 OptimalTempRating = "not windy enough" ;
             }
         }

         if (Activity.equals("Skiing")){
             if (temp == 1){
                 OptimalTempRating = "perfect";
             }
             if (temp > 1){
                 OptimalTempRating = "to hot";
             }
             if (condition == 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 3){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Soccer")){
             if (temp < 1){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "a little cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "to hot";
             }
             if (condition > 3){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 3){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Swimming")){
             if (temp < 3){
                 OptimalTempRating = "to cold";
             }
             if (temp >= 3){
                 OptimalTempRating = "perfect";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
         }

         if (Activity.equals("Tennis")){
             if (temp == 0){
                 OptimalTempRating = "to cold";
             }
             if (temp == 1){
                 OptimalTempRating = "a little cold";
             }
             if (temp == 2){
                 OptimalTempRating = "perfect";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "to hot";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind > 3){
                 OptimalWindRating = 1;
             }
         }

         if (Activity.equals("Water Sports")){
             if (temp < 3){
                 OptimalTempRating = "to cold";
             }
             if (temp >= 3){
                 OptimalTempRating = "perfect";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
         }

         if (Activity.equals("Wind Surfing")){
             if (temp < 3){
                 OptimalTempRating = "to cold";
             }
             if (temp == 3){
                 OptimalTempRating = "perfect";
             }
             if (temp == 4){
                 OptimalTempRating = "perfect";
             }
             if (condition > 2){
                 OptimalTempRating = "a bad day" ;
             }
             if (wind < 1){
                 OptimalTempRating = "not windy enough" ;
             }
         }

         String ComposedString = StringComposer(OptimalTempRating, OptimalWindRating, Activity);
         return ComposedString;
     }


     public String StringComposer(String temp, int integer, String activity){

         if (integer == 1){
             PreMadeString = (new String("It's to windy for " + activity + "." ));
         }else {
             PreMadeString = (new String("It's " + temp + " outside for " + activity));
         }
         return PreMadeString;
     }
}
