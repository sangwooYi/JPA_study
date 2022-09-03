package hellojpa;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipCode;

    public Address() {

    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }



    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(city, address.getCity()) && Objects.equals(street, address.getStreet())
                && Objects.equals(zipCode, address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipCode);
    }

}
