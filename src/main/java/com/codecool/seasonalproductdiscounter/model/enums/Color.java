package com.codecool.seasonalproductdiscounter.model.enums;

public enum Color implements CharSequence {
    RED,
    YELLOW,
    GREEN,
    BLUE,
    VIOLET,
    PINK,
    BROWN;

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}

