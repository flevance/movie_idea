package com.dayuanit.movie.movie.enums;

public enum OrderStatusEnum {

    待付款(0,"待付款"),已支付(1,"支付成功"),支付取消(2,"支付取消");

    private int k;
    private String v;

    OrderStatusEnum(int k, String v) {
        this.k = k;
        this.v = v;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
