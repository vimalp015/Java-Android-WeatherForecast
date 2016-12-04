package in.lamiv.weatherforecast.dataobjects;

/**
 * Created by vimal on 12/4/2016.
 */

public class ForecastData {

    private double latitude;
    private double longitude;
    private String timezone;
    private Currently currently;

    public class Currently{

        private double temperature;
        private double humidity;
        private double windSpeed;
        private double visibility;
        private String summary;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public double getVisibility() {
            return visibility;
        }

        public void setVisibility(double visibility) {
            this.visibility = visibility;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

}