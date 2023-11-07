package hr.java.production.Enum;


/**
 * The enum City.
 */
public enum City {
    /**
     * Zagreb city.
     */
    ZAGREB("Zagreb", "10000"),
    /**
     * Split city.
     */
    SPLIT("Split", "21000"),
    /**
     * Rijeka city.
     */
    RIJEKA("Rijeka", "51000"),
    /**
     * Osijek city.
     */
    OSIJEK("Osijek", "31000"),
    /**
     * Varazdin city.
     */
    VARAZDIN("Varaždin", "42000");

    private final String cityName;
    private final String postalCode;

    City(String cityName, String postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
}