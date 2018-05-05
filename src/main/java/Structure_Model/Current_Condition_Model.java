package Structure_Model;
public class Current_Condition_Model {

    private String condtion;
    private String description;
    private String icon;
    private float humidty;
    private float MaxTemp;
    private float MinTemp;
    private double temp;

    public String getCondtion() {
        return condtion;
    }

    public void setCondtion(String condtion) {
        this.condtion = condtion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getHumidty() {
        return humidty;
    }

    public void setHumidty(float humidty) {
        this.humidty = humidty;
    }

    public float getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        MaxTemp = maxTemp;
    }

    public float getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(float minTemp) {
        MinTemp = minTemp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
