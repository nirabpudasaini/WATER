package org.kathmandulivinglabs.water;

import com.mapbox.mapboxsdk.geometry.LatLng;


public class TestPoint {
    private String description, school, color, city, country, surveyor_name, photo_url;
    private LatLng location;
    private Double pH, dissolved_oxygen, temperature, turbidity, biochemical_oxygen_demand, nitrate, phosphate;
    private long id;
    private boolean macroinvertebrates, coliform;

    public TestPoint(long id, LatLng location) {
        this.id = id;
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSurveyor_name() {
        return surveyor_name;
    }

    public void setSurveyor_name(String surveyor_name) {
        this.surveyor_name = surveyor_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public LatLng getLocation() {
        return location;
    }

    public Double getpH() {
        return pH;
    }

    public void setpH(Double pH) {
        this.pH = pH;
    }

    public Double getDissolved_oxygen() {
        return dissolved_oxygen;
    }

    public void setDissolved_oxygen(Double dissolved_oxygen) {
        this.dissolved_oxygen = dissolved_oxygen;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(Double turbidity) {
        this.turbidity = turbidity;
    }

    public Double getBiochemical_oxygen_demand() {
        return biochemical_oxygen_demand;
    }

    public void setBiochemical_oxygen_demand(Double biochemical_oxygen_demand) {
        this.biochemical_oxygen_demand = biochemical_oxygen_demand;
    }

    public Double getNitrate() {
        return nitrate;
    }

    public void setNitrate(Double nitrate) {
        this.nitrate = nitrate;
    }

    public Double getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(Double phosphate) {
        this.phosphate = phosphate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMacroinvertebrates() {
        return macroinvertebrates;
    }

    public void setMacroinvertebrates(boolean macroinvertebrates) {
        this.macroinvertebrates = macroinvertebrates;
    }

    public boolean isColiform() {
        return coliform;
    }

    public void setColiform(boolean coliform) {
        this.coliform = coliform;
    }
}
