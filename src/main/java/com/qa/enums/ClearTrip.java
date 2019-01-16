package com.qa.enums;

public enum ClearTrip {
    PICKUP("Bangalore"),
    DESTINATION("New Delhi");

    private String text;

    ClearTrip(String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }


}
