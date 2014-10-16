package org.kathmandulivinglabs.water;

public class Country {

    private String mName;
    private Double pH, dissolved_oxygen, temperature, turbidity, biochemical_oxygen_demand, nitrate, phosphate;
    private int number_school, number_testpoint, number_cities, coliform_present, coliform_absent, macroinvertebrates_present, macroinvertebrates_absent;

    public Country(String name) {
        mName = name;
        //mBoundry = boundry;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
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

    public int getNumber_school() {
        return number_school;
    }

    public void setNumber_school(int number_school) {
        this.number_school = number_school;
    }

    public int getNumber_testpoint() {
        return number_testpoint;
    }

    public void setNumber_testpoint(int number_testpoint) {
        this.number_testpoint = number_testpoint;
    }

    public int getNumber_cities() {
        return number_cities;
    }

    public void setNumber_cities(int number_cities) {
        this.number_cities = number_cities;
    }

    public int getColiform_present() {
        return coliform_present;
    }

    public void setColiform_present(int coliform_present) {
        this.coliform_present = coliform_present;
    }

    public int getColiform_absent() {
        return coliform_absent;
    }

    public void setColiform_absent(int coliform_absent) {
        this.coliform_absent = coliform_absent;
    }

    public int getMacroinvertebrates_present() {
        return macroinvertebrates_present;
    }

    public void setMacroinvertebrates_present(int macroinvertebrates_present) {
        this.macroinvertebrates_present = macroinvertebrates_present;
    }

    public int getMacroinvertebrates_absent() {
        return macroinvertebrates_absent;
    }

    public void setMacroinvertebrates_absent(int macroinvertebrates_absent) {
        this.macroinvertebrates_absent = macroinvertebrates_absent;
    }
}
