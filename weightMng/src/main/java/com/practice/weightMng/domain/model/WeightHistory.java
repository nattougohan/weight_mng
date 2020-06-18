package com.practice.weightMng.domain.model;


import javax.persistence.*;

@Entity
@Table(name = "weighthistory")
public class WeightHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer seq;

    @Column(name="id")
    private int id;

    @Column(name="weight")
    private double weight;

    @Column(name="bmi")
    private double bmi;

    @Column(name="measured_day")
    private String measuredDay;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getMeasuredDay() {
        return measuredDay;
    }

    public void setMeasuredDay(String measuredDay) {
        this.measuredDay = measuredDay;
    }
}
