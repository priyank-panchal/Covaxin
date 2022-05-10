package com.example.covaxin;
public class vaccineSlotsDistricte {

    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private int pincode;
    private String from;
    private String to;
    private String fee_type;
    private String date;
    private int available_capacity;
    private int available_capacity_dose1;
    private int available_capacity_dose2;
    private int min_age_limit;
    private String Vaccine;

     public vaccineSlotsDistricte(String name, String address, String state_name, String district_name, String block_name, int pincode, String from, String to, String fee_type, String date, int available_capacity, int available_capacity_dose1, int available_capacity_dose2, int min_age_limit, String vaccine) {
        this.name = name;
        this.address = address;
        this.state_name = state_name;
        this.district_name = district_name;
        this.block_name = block_name;
        this.pincode = pincode;
        this.from = from;
        this.to = to;
        this.fee_type = fee_type;
        this.date = date;
        this.available_capacity = available_capacity;
        this.available_capacity_dose1 = available_capacity_dose1;
        this.available_capacity_dose2 = available_capacity_dose2;
        this.min_age_limit = min_age_limit;
        Vaccine = vaccine;
    }

    public vaccineSlotsDistricte() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailable_capacity() {
        return available_capacity;
    }

    public void setAvailable_capacity(int available_capacity) {
        this.available_capacity = available_capacity;
    }

    public int getAvailable_capacity_dose1() {
        return available_capacity_dose1;
    }

    public void setAvailable_capacity_dose1(int available_capacity_dose1) {
        this.available_capacity_dose1 = available_capacity_dose1;
    }

    public int getAvailable_capacity_dose2() {
        return available_capacity_dose2;
    }

    public void setAvailable_capacity_dose2(int available_capacity_dose2) {
        this.available_capacity_dose2 = available_capacity_dose2;
    }

    public int getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(int min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public void setVaccine(String vaccine) {
        Vaccine = vaccine;
    }

    @Override
    public String toString() {
        return "name:" + name + '\n' +
                " address:" + address + '\n' +
                " state_name:" + state_name + '\n' +
                " district_name:" + district_name + '\n' +
                " block_name:" + block_name + '\n' +
                " pincode:" + pincode +'\n' +
                " from:" + from + '\n' +
                " to:" + to + '\n' +
                " fee_type:" + fee_type + '\n' +
                " date:" + date + '\n' +
                " available_capacity:" + available_capacity + '\n' +
                " available_capacity_dose1:" + available_capacity_dose1 + '\n' +
                " available_capacity_dose2:" + available_capacity_dose2 + '\n' +
                " min_age_limit:" + min_age_limit + '\n' +
                " Vaccine:" + Vaccine + '\n' ;

    }
}

