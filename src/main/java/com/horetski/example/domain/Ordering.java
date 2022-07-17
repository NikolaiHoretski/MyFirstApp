package com.horetski.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "ordering")
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;

    private String time;
    @ManyToOne
    private User user;

    public Ordering(String data, String time, User user) {
        this.data = data;
        this.time = time;
        this.user = user;
    }

    public Ordering() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
