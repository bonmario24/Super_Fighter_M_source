package com.xhuyu.component.mvp.model;

public class CountryModel {
    private String abbreviation = "";
    private String areaCode;
    private String countryName;
    private boolean hasStroke;
    private String sortLetters;

    public boolean hasStroke() {
        return this.hasStroke;
    }

    public void setHasStroke(boolean hasStroke2) {
        this.hasStroke = hasStroke2;
    }

    public String getSortLetters() {
        return this.sortLetters;
    }

    public void setSortLetters(String sortLetters2) {
        this.sortLetters = sortLetters2;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName2) {
        this.countryName = countryName2;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode2) {
        this.areaCode = areaCode2;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(String abbreviation2) {
        this.abbreviation = abbreviation2;
    }

    public String toString() {
        return "CountryModel{sortLetters='" + this.sortLetters + '\'' + ", countryName='" + this.countryName + '\'' + ", areaCode='" + this.areaCode + '\'' + ", abbreviation='" + this.abbreviation + '\'' + ", hasStroke=" + this.hasStroke + '}';
    }
}
