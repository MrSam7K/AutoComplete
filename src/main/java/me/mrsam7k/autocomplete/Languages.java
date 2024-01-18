package me.mrsam7k.autocomplete;

public enum Languages {
    english(""),
    big_english("en"),
    russian("ru"),
    chinese("zh"),
    portuguese("pt");

    private final String code;

    public String getCode() {
        if(this.name().equals("english")) return "";
        else return "-" + code;
    }

    @Override
    public String toString() {
        return "language.autocomplete." + this.name().toLowerCase();
    }

    Languages(String code) {
        this.code = code;
    }
}

