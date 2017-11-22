package People;

import java.util.Locale;

public enum Nationality {
    Polish(new Locale("pl", "PL")),
    Ukrainian(new Locale("uk","UA")),
    Belarussian(new Locale("be", "BY")),
    Slovak(new Locale("sk","SK")),
    Lithuanian(new Locale("lt","LT")),
    Latvian(new Locale("lv","LV")),
    British(new Locale("en","GB")),
    Indian(new Locale("en","IN")),
    Chinese(new Locale("zh","CN")),
    Vietnamese(new Locale("vi","VN"));

    private Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }
}
