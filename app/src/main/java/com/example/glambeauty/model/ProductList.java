package com.example.glambeauty.model;

import java.util.ArrayList;

public class ProductList {
    private String nId;
    private String nazivProizvoda;
    private ArrayList<String> listaSastojci;
    private ArrayList<String> listaKoraci;
    private String id;


    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public ArrayList<String> getListaSastojci() {
        return listaSastojci;
    }

    public void setListaSastojci(ArrayList<String> listaSastojci) {
        this.listaSastojci = listaSastojci;
    }

    public ArrayList<String> getListaKoraci() {
        return listaKoraci;
    }

    public void setListaKoraci(ArrayList<String> listaKoraci) {
        this.listaKoraci = listaKoraci;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
