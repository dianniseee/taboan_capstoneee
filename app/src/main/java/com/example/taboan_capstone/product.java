package com.example.taboan_capstone;

public class product {

    private String prod_name;
    private String prod_desc;
    private String prod_category;
    private double prod_price;
    private double prod_quantity;
    private String image;

    public product(String prod_name, String prod_desc, String prod_category, double prod_price, double prod_quantity, String image) {
        this.prod_name = prod_name;
        this.prod_desc = prod_desc;
        this.prod_price = prod_price;
        this.prod_category = prod_category;
        this.prod_quantity = prod_quantity;
        this.image = image;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getProd_desc() {
        return prod_desc;
    }
    public String getProd_category() {
        return prod_category;
    }

    public double getProd_price() {
        return prod_price;
    }

    public double getProd_quantity() {
        return prod_quantity;
    }

    public String getImage() {
        return image;
    }
}
