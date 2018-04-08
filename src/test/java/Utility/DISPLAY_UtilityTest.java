package Utility;

import com.example.jacob.weatherornot.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class DISPLAY_UtilityTest {
    @Test
    public void applyIcon() throws Exception {
        String input = "Rain";
        int output;
        int expected = 2131165289;

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.applyIcon(input);

        assertEquals(expected, output);
    }

    @Test
    public void formatDescription() throws Exception {
        String input = "clear sky";
        String output;
        String expected = "Clear Skys";

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.formatDescription(input);

        assertEquals(expected, output);
    }

    @Test
    public void ICON_CORRECTION_CHECK() throws Exception {
        String input = "scattered clouds";
        int secondinput = 0;
        int output;
        int test = R.drawable.partly_cloudy;
        int expected = test;

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.ICON_CORRECTION_CHECK(input, secondinput);

        assertEquals(expected, output);
    }

    @Test
    public void KELVIN_TO_FAHRENHEIT1() throws Exception {
        double input = 400;
        String output;
        String expected = "260";

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.KELVIN_TO_FAHRENHEIT(input);

        assertEquals(expected, output);
    }

    @Test
    public void KELVIN_TO_FAHRENHEIT_INTEGER1() throws Exception {
        double input = 400;
        int output;
        int expected = 260;

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.KELVIN_TO_FAHRENHEIT_INTEGER(input);

        assertEquals(expected, output);
    }

    @Test
    public void floatToString() throws Exception {
        float input = 400;
        String output;
        String expected = "400";

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.FloatToString(input);

        assertEquals(expected, output);
    }

    @Test
    public void floatToInteger() throws Exception {
        float input = 400;
        String output;
        String expected = "400";

        //note since we take the float input and cast it to a integer it's a product of java.lang.string and not java.lang.integer
        //It still functions as an integer but will not test like an integer

        DISPLAY_Utility display_utility = new DISPLAY_Utility();
        output = display_utility.FloatToString(input);
        assertEquals(expected, output);
    }
}