package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder {
    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder) {
        //this.iEncoder = new Base64Encoder();
        //this.iEncoder = new UrlEncoder();
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
    public String encode(String msg) {
        return iEncoder.encode(msg);
    }
}
