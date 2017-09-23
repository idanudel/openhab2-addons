
package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumerDetails {

    @SerializedName("City_Name")
    @Expose
    private String cityName;
    @SerializedName("First_Name")
    @Expose
    private String firstName;
    @SerializedName("Last_Name")
    @Expose
    private String lastName;
    @SerializedName("Apartment")
    @Expose
    private String apartment;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Street_Name")
    @Expose
    private String streetName;
    @SerializedName("Consumer_Number")
    @Expose
    private String consumerNumber;
    @SerializedName("Phone_Prefix")
    @Expose
    private String phonePrefix;
    @SerializedName("Phone_Number")
    @Expose
    private String phoneNumber;
    @SerializedName("Additional_Phone_Prefix")
    @Expose
    private String additionalPhonePrefix;
    @SerializedName("Additional_Phone_Number")
    @Expose
    private String additionalPhoneNumber;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalPhonePrefix() {
        return additionalPhonePrefix;
    }

    public void setAdditionalPhonePrefix(String additionalPhonePrefix) {
        this.additionalPhonePrefix = additionalPhonePrefix;
    }

    public String getAdditionalPhoneNumber() {
        return additionalPhoneNumber;
    }

    public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
        this.additionalPhoneNumber = additionalPhoneNumber;
    }

}
