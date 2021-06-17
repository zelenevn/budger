package com.budger.data.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(
        name = "Goal"
)
@Table(
        name = "table",
        schema = "public"
)
public class Goal {

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

    @Column(
            name = "title"
    )
    private String title;

    @Column(
            name = "value"
    )
    private Float value;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    public Goal() {}

    public Goal(String title, Float value, String description, Date expirationDate, Budget budget) {
        this.title = title;
        this.value = value;
        this.description = description;
        this.expirationDate = expirationDate;
        this.budget = budget;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
