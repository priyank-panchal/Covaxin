package com.example.covaxin;

public class IDProofDetailsdb {
    private int p_id;
    private String ID_name;
    private String ID_num;
    private String ID_type;
    private int year;
    private String gender;

    public IDProofDetailsdb() {
    }

    public IDProofDetailsdb(int p_id, String ID_name, String ID_num, String ID_type, int year, String gender) {
        this.p_id = p_id;
        this.ID_name = ID_name;
        this.ID_num = ID_num;
        this.ID_type = ID_type;
        this.year = year;
        this.gender = gender;
    }

    public int getP_id() {
        return p_id;
    }
    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getID_name() {
        return ID_name;
    }

    public void setID_name(String ID_name) {
        this.ID_name = ID_name;
    }

    public String getID_num() {
        return ID_num;
    }

    public void setID_num(String ID_num) {
        this.ID_num = ID_num;
    }

    public String getID_type() {
        return ID_type;
    }

    public void setID_type(String ID_type) {
        this.ID_type = ID_type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "IDProofDetailsdb{" +
                "p_id='" + p_id + '\'' +
                ", ID_name='" + ID_name + '\'' +
                ", ID_num='" + ID_num + '\'' +
                ", ID_type='" + ID_type + '\'' +
                ", year=" + year +
                ", gender='" + gender + '\'' +
                '}';
    }
}
