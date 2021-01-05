/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

/**
 *
 * @author İREM SAMUR
 */
public class Item {
    //doktor ad ve id değerlerini tutan Item classı oluşturduk
    private String ad;//encapsulation kullandık
    private int id;

    public Item(String ad, int id) {//constructor
        this.ad = ad;
        this.id = id;
    }
//get-set methotları
    public String getAd() {
        return ad;
    }

    public void setSoyad(String ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){//toString 'i override eden methot
        return this.ad;
    }
    
}
