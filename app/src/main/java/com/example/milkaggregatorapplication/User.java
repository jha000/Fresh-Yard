package com.example.milkaggregatorapplication;

public class User {

    String Product,  Amount,Name,Mobile,Address;

    User ()
    {

    }
    public User(String product, String amount ,String name, String mobile, String address) {
        this.Product = product;
        this.Amount = amount;
        this.Name = name;
        this.Mobile = mobile;
        this.Address = address;

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }
}
