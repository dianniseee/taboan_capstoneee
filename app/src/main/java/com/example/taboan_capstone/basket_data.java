package com.example.taboan_capstone;

public class basket_data {
    private String name;
    private double price;
    private int quan;
    private Double total;

    public basket_data(String name, double price, int quan, Double total){
        this.setName(name);
        this.setPrice(price);
        this.setQuan(quan);
        this.setTotal(total);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
