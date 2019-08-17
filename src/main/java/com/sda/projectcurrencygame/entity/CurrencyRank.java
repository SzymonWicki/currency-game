package com.sda.projectcurrencygame.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CurrencyRank {

    public CurrencyRank(String user, int scoree, LocalDate date) {
        this.user = user;
        this.scoree = scoree;
        this.date = date;
    }

    public CurrencyRank() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    private int scoree;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScoree() {
        return scoree;
    }

    public void setScoree(int scoree) {
        this.scoree = scoree;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
