package com.budger.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Account")
@Table(
        name = "account",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_login",
                        columnNames = "login"
                ),
                @UniqueConstraint(
                        name = "uk_email",
                        columnNames = "email"
                )
        }
)
public class Account implements Serializable {

    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "login",
            nullable = false
    )
    private String login;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget = new Budget();



    @OneToMany(
            mappedBy = "account",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<AccountRole> accountRoles = new LinkedList<>();



    public Account() {
    }

    public Account(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Account(Integer id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public void addAccountRole(AccountRole accountRole) {
        if (!this.accountRoles.contains(accountRole)) {
            this.accountRoles.add(accountRole);
        }
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void removeAccountRole(AccountRole accountRole) {
        this.accountRoles.remove(accountRole);
    }

}
