package com.budger.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import static javax.persistence.GenerationType.IDENTITY;

    @Entity(name = "Role")
    @Table(
            name = "role",
            schema = "public",
            uniqueConstraints = {
                    @UniqueConstraint(
                            name = "uk_role_title",
                            columnNames = "title"
                    )
            }
    )
    public class Role implements Serializable {

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
                name = "title",
                nullable = false,
                length = 25
        )
        private String title;

        @OneToMany(
                mappedBy = "role",
                cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
                fetch = FetchType.LAZY
        )
        private List<AccountRole> accountsRoles = new LinkedList<>();

        public Role() {
        }

        public Role(String title) {
            this.title = title;
        }

        public Role(Integer id, String title) {
            this.id = id;
            this.title = title;
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

        public List<AccountRole> getAccountsRoles() {
            return accountsRoles;
        }

        public void setAccountsRoles(List<AccountRole> accountsRoles) {
            this.accountsRoles = accountsRoles;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Role role = (Role) o;
            return id.equals(role.id) && title.equals(role.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }

        @Override
        public String toString() {
            return "Role{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

