package com.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "goal", schema = "public")
public class Goal {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column
    private Float value;

    @Column
    private String currency;

    @Column
    private String description;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    public Goal() {}

    public Goal(String title, Float value, String currency, String description, Timestamp expirationDate) {
        this.title = title;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.expirationDate = expirationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
