package com.example.covaxin;

public class appoinmentclass {
    public  String mobile;
    public  String token_no;
    public appoinmentclass() {

    }
    public appoinmentclass(String mobile, String token_no) {
        this.mobile = mobile;
        this.token_no = token_no;
    }
    public String getMobile() {
        return mobile;
    }
    public String getToken_no() {
        return token_no;
    }
    @Override
    public String toString() {
        return "appoinmentclass{" +
                "mobile='" + mobile + '\'' +
                ", token_no='" + token_no + '\'' +
                '}';
    }
}
