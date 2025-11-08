package rvb.weather;

public final class WeatherRecord {
    public final long timestamp;
    public final Attribute attribute;
    public final int x, y;
    public final double value;

    public WeatherRecord(long t, Attribute a, int x, int y, double v) {
        this.timestamp = t; this.attribute = a; this.x = x; this.y = y; this.value = v;
    }

    public static WeatherRecord parse(String line) {
        String[] p = line.trim().split("\\s+");
        if (p.length != 5) throw new IllegalArgumentException("Bad line: " + line);
        long ts = Long.parseLong(p[0]);
        Attribute a = Attribute.fromToken(p[1]);
        int x = Integer.parseInt(p[2]);
        int y = Integer.parseInt(p[3]);
        double v = Double.parseDouble(p[4]);
        return new WeatherRecord(ts, a, x, y, v);
    }

    @Override
    public String toString() { return attribute + "@" + x + "," + y + "=" + value; }
}

