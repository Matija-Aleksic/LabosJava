package hr.java.production.model;

import hr.java.production.Enum.City;

import java.util.Objects;


/**
 * The type Address.
 */
public class Address {
    private final String street;
    private final String houseNumber;
    private final City city; // Promijenjena varijabla city da koristi enum City

    /**
     * Konstruktor za Address s Builder obrascem
     *
     * @param builder graditelj
     */
    private Address(Builder builder) {
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city; // Postavljeno korištenje enuma za grad
    }

    /**
     * Gets street.
     *
     * @return ulica street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets house number.
     *
     * @return kućni broj
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Gets city.
     *
     * @return grad (enum City)
     */
    public City getCity() {
        return city;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private String street;
        private String houseNumber;
        private City city; // Promijenjena varijabla city u enum City

        /**
         * Sets street.
         *
         * @param street ulica
         * @return graditelj street
         */
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        /**
         * Sets house number.
         *
         * @param houseNumber kućni broj
         * @return graditelj house number
         */
        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        /**
         * Sets city.
         *
         * @param city grad (enum City)
         * @return graditelj city
         */
        public Builder setCity(City city) {
            this.city = city;
            return this;
        }

        /**
         * Build address.
         *
         * @return adresa address
         */
        public Address build() {
            return new Address(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder builder)) return false;
            return Objects.equals(street, builder.street) && Objects.equals(houseNumber, builder.houseNumber) && Objects.equals(city, builder.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, houseNumber, city);
        }
    }
}

