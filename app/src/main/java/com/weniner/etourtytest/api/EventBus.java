package com.weniner.etourtytest.api;

/**
 * @version 1.0
 * @auther Weniner
 * 2019/4/2
 **/
public class EventBus {
    private String close;

    public EventBus(String close) {
        this.close = close;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }
}

