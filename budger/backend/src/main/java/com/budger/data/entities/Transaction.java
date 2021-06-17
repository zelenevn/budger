package com.budger.data.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(
        name = "Transaction"
)
@Table(
        name = "transaction",
        schema = "public"
)
public class Transaction {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Integer id;

    @Column(nullable = false)
    private Float value;

    @Column(name = "transaction_time")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Transaction() {}

    public Transaction(Float value, Date transactionTime, Category category) {
        this.value = value;
        this.date = transactionTime;
        this.category = category;
    }

    public Transaction(Float value, Date date, Budget budget, Category category) {
        this.value = value;
        this.date = date;
        this.budget = budget;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
