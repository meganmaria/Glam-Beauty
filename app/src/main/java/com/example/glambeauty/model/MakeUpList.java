package com.example.glambeauty.model;

public class MakeUpList {
    private String brand;
    private String name;
    private Double price;
    private String price_sign;
    private String description;
    private String image_link;
    private String product_type;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrice_sign() {
        return price_sign;
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_image_link() {
        return image_link;
    }

    public void setProduct_image_link(String product_image_link) {
        this.image_link = product_image_link;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

}
