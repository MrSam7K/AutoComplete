package me.mrsam7k.autocomplete;

import java.util.HashMap;
import java.util.Map;

public enum Languages {
    English(""),
    Russian("ru"),
    Chinese("zh"),
    Big_English("en"),
    Portuguese("pt");

    private final String code;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        if(this.equals(Big_English)) return "English (Big)";
        else return this.name();
    }

    Languages(String code) {
        this.code = code;
    }
}

