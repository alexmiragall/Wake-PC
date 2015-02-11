package com.alexmiragall.wakepc.model;

import java.io.Serializable;

/**
 * Created by Alejandro Miragall Arnal on 19/01/2015.
 */
public class PC implements Serializable {
    private String name;
    private String mac;
    private String ssid;
    private String ip;
    private int port;
    private boolean wakeUpAuto = false;

    public PC(String name, String mac, String ssid, String ip, int port, boolean wakeUpAuto) {
        this.name = name;
        this.mac = mac;
        this.ssid = ssid;
        this.ip = ip;
        this.port = port;
        this.wakeUpAuto = wakeUpAuto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isWakeUpAuto() {
        return wakeUpAuto;
    }

    public void setWakeUpAuto(boolean wakeUpAuto) {
        this.wakeUpAuto = wakeUpAuto;
    }
}
