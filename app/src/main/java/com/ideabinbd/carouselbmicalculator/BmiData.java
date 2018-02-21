package com.ideabinbd.carouselbmicalculator;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ramim on 2/21/2018.
 */

public class BmiData extends RealmObject{

    @PrimaryKey
    int id;

    double weight,height,bmi;

    String timeDate;

    public BmiData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }
}
