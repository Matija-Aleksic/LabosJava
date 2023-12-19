package hr.java.production.model;

import hr.java.production.Enum.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


/**
 * The type Address.
 */
public class Address implements Serializable {
    public static final ArrayList<Address> addresses = new ArrayList<>();
    private final String street;
    private final String houseNumber;
    private final City city; // Promijenjena varijabla city da koristi enum City
    private final Long id;

    /**
     * Konstruktor za Address s Builder obrascem
     *
     * @param builder graditelj
     */
    public Address(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city; // Postavljeno korištenje enuma za grad
    }

    public Address(String street, String houseNumber, City city, Long id) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    /**
     * The type Builder.
     */
    public static class Builder {

        private Long id;
        private String street;
        private String houseNumber;
        private City city;

        public static ArrayList<Address> loadAddressesFromFile(String fileName) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Long id = Long.parseLong(line.trim());
                    String street = reader.readLine().trim();
                    String houseNumber = reader.readLine().trim();
                    City city = City.valueOf(reader.readLine().trim());

                    addresses.add(new Address.Builder().setId(id).setStreet(street).setHouseNumber(houseNumber).setCity(city).build());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return addresses;
        }

        public static Address findAddressById(Long id) {
            for (Address address : addresses) {
                if (address.getId().equals(id)) {
                    return address;
                }
            }
            return null;
        }

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

        public Builder setId(Long id1) {
            this.id = id1;
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

