package com.budger.data.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(
        name = "Budget"
)
@Table(
        name = "budget",
        schema = "public"
)
public class Budget {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Goal> goals = new LinkedList<>();

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new LinkedList<>();

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

    public void addTransaction(Transaction transaction) {
        if (!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
        }
    }

    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
    }

    public void addGoal(Goal goal) {
        if (!this.goals.contains(goal)) {
            this.goals.add(goal);
        }
    }

    public void removeGoal(Goal goal) {
        this.goals.remove(goal);
    }

}
