package hr.java.production.Enum;


public enum City {
    ZAGREB("Zagreb", "10000"),
    SPLIT("Split", "21000"),
    RIJEKA("Rijeka", "51000"),
    OSIJEK("Osijek", "31000"),
    VARAZDIN("Varaždin", "42000");

    private final String cityName;
    private final String postalCode;

    City(String cityName, String postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}