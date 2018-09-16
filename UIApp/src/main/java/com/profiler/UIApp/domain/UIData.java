package com.profiler.UIApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UIData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="uidata_id")
    private Integer id;

    @Column(nullable=false)
    private String uiData;

    public UIData() {}

    public UIData(String uiData) {
        this.uiData = uiData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUiData() {
        return uiData;
    }

    public void setUidata(String uidata) {
        this.uiData = uiData;
    }
}
