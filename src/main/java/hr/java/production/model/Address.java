package hr.java.production.model;

import java.util.Objects;

/**
 * The type Address.
 */
public class Address {

    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;

    /**
     *
     * @param builder
     */
    private Address(Builder builder) {
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
    }

    /**
     * Gets street.
     *
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private String street;
        private String houseNumber;
        private String city;
        private String postalCode;

        /**
         * Sets street.
         *
         * @param street the street
         * @return the street
         */
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        /**
         * Sets house number.
         *
         * @param houseNumber the house number
         * @return the house number
         */
        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        /**
         * Sets city.
         *
         * @param city the city
         * @return the city
         */
        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets postal code.
         *
         * @param postalCode the postal code
         * @return the postal code
         */
        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        /**
         * Build address.
         *
         * @return the address
         */
        public Address build() {
            return new Address(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder builder)) return false;
            return Objects.equals(street, builder.street) && Objects.equals(houseNumber, builder.houseNumber) && Objects.equals(city, builder.city) && Objects.equals(postalCode, builder.postalCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, houseNumber, city, postalCode);
        }
    }
}
