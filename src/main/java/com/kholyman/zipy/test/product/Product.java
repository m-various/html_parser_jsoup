package com.kholyman.zipy.test.product;

public class Product {
    //fields
    private String category;
    private String name;
    private String price;
    private String oldPrice;
    private String priceWithDelivery;
    private String discount;
    private String bonusCoins;
    private String seller;
    private String options;
    private String numberOfSales;
    private String smartDelivery;
    private String smartDeliveryMethod;
    private String url;

    //constructors
    public Product() {}

    public Product(String name, String url, String category, String discount, String price, String oldPrice,
                   String numberOfSales, String seller, String priceWithDelivery, String bonusCoins, String options,
                   String smartDelivery, String smartDeliveryMethod) {
        this.name = name;
        this.url = url;
        this.category = category;
        this.discount = discount;
        this.price = price;
        this.oldPrice = oldPrice;
        this.numberOfSales = numberOfSales;
        this.seller = seller;
        this.priceWithDelivery = priceWithDelivery;
        this.bonusCoins = bonusCoins;
        this.options = options;
        this.smartDelivery = smartDelivery;
        this.smartDeliveryMethod = smartDeliveryMethod;
    }

    //getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getPriceWithDelivery() {
        return priceWithDelivery;
    }

    public void setPriceWithDelivery(String priceWithDelivery) {
        this.priceWithDelivery = priceWithDelivery;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBonusCoins() {
        return bonusCoins;
    }

    public void setBonusCoins(String bonusCoins) {
        this.bonusCoins = bonusCoins;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(String numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public String getSmartDelivery() {
        return smartDelivery;
    }

    public void setSmartDelivery(String smartDelivery) {
        this.smartDelivery = smartDelivery;
    }

    public String getSmartDeliveryMethod() {
        return smartDeliveryMethod;
    }

    public void setSmartDeliveryMethod(String smartDeliveryMethod) {
        this.smartDeliveryMethod = smartDeliveryMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                ", priceWithDelivery='" + priceWithDelivery + '\'' +
                ", discount='" + discount + '\'' +
                ", bonusCoins='" + bonusCoins + '\'' +
                ", seller='" + seller + '\'' +
                ", options='" + options + '\'' +
                ", numberOfSales='" + numberOfSales + '\'' +
                ", smartDelivery='" + smartDelivery + '\'' +
                ", smartDeliveryMethod='" + smartDeliveryMethod + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
