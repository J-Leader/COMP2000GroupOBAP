package rvb.weather;

public enum Attribute {
    RAIN, WINDX, WINDY, TEMP;

    public static Attribute fromToken(String tok) {
        switch (tok.toLowerCase()) {
            case "rain":  return RAIN;
            case "windx": return WINDX;
            case "windy": return WINDY;
            case "temp":
            case "temperature": return TEMP;
            default: throw new IllegalArgumentException("Unknown attribute: " + tok);
        }
    }
}
