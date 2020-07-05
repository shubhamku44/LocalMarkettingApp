package com.example.pc.localmarketting;

public class Merchant {
    String name;
    String pass;
    String add;
    String shopname;
    String contact;
    String categories;
    String mail;
    public Merchant() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Merchant(String name, String pass, String add, String shopname, String contact, String categories, String mail) {
        this.name = name;
        this.pass = pass;
        this.add = add;
        this.shopname = shopname;
        this.contact = contact;
        this.categories = categories;
        this.mail = mail;
    }
}


