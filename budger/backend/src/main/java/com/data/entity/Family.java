package com.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "family", schema = "public")
public class Family {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "family_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="family_account",
            joinColumns = {
                    @JoinColumn(name = "family_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "account_id")
            })
    private List<Account> accounts;

    public Family() {}

    public Family(String name, List<Account> accounts) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
