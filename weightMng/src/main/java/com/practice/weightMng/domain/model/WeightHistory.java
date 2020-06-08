package com.practice.weightMng.domain.model;


import javax.persistence.*;

@Entity
@Table(name = "WEIGHTHISTORY")
public class WeightHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer seq;

    @Column(name="id")
    private Integer id;

    @Column(name="weight")
    private Double weight;

    @Column(name="bmi")
    private Double bmi;

    @Column(name="measuredDay")
    private String measuredDay;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public String getMeasuredDay() {
        return measuredDay;
    }

    public void setMeasuredDay(String measuredDay) {
        this.measuredDay = measuredDay;
    }
}
