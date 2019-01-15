package com.qa.enums;

public enum  Amazon {
    BOOK_TITLE("A Brief History of Everyone Who Ever Lived"),
    PAGE_TITLE("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");

    private String text;

    Amazon(String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}
