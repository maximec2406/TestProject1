package ru.abr.dit.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "TP1_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column (nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column (nullable = false, length = 500)
    private String name;

    @ManyToMany
    @JoinTable (name="TP1_USER_ROLE",
            joinColumns=@JoinColumn (name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    public Role() {
        this.create_time = new Date();
    }

    public Role(String name) {
        this.name = name;
        this.create_time = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
