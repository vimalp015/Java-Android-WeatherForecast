package in.lamiv.weatherforecast.dataobjects;
/**
 * Created by vimal on 12/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class ForecastData implements Parcelable {

    double latitude;
    double longitude;
    String timezone;
    Currently currently;

    public static class Currently implements Parcelable{

        String summary;
        double temperature;
        double humidity;
        double windSpeed;
        double visibility;

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

        //Parcelable implementation
        //{
        @Override
        public int describeContents(){
            return hashCode();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags){
            dest.writeString(summary);
            dest.writeDouble(temperature);
            dest.writeDouble(humidity);
            dest.writeDouble(windSpeed);
            dest.writeDouble(visibility);
        }

        public Currently(){}

        public Currently(Parcel p){
            summary = p.readString();
            temperature = p.readDouble();
            humidity = p.readDouble();
            windSpeed = p.readDouble();
            visibility = p.readDouble();
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public Currently createFromParcel(Parcel in) {
                return new Currently(in);
            }

            public Currently[] newArray(int size) {
                return new Currently[size];
            }
        };
        //}
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

    //Parcelable implementation
    //{
    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(timezone);
        dest.writeParcelable(currently, flags);
    }

    public ForecastData(Parcel p) {
        latitude = p.readDouble();
        longitude = p.readDouble();
        timezone = p.readString();
        currently = p.readParcelable(Currently.class.getClassLoader());
    }

    public ForecastData(){}

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ForecastData createFromParcel(Parcel in) {
            return new ForecastData(in);
        }

        public ForecastData[] newArray(int size) {
            return new ForecastData[size];
        }
    };
    //}

}