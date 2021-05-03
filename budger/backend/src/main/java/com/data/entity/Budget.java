package com.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "budget", schema = "public")
public class Budget {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "budget")
    private List<Goal> goals;

    @OneToMany(mappedBy = "budget")
    private List<Transaction> transactions;

    public Budget() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
