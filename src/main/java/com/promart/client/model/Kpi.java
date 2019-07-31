package com.promart.client.model;

/**
 * Kpi Model Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
public class Kpi {

    private static final long serialVersionUID = 1L;

    private String name;
    private Double value;

    public Kpi(String name, Double value) {
        this.name = name;
        this.value = value;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}