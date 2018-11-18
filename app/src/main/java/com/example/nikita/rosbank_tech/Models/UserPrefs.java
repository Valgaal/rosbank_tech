package com.example.nikita.rosbank_tech.Models;

import java.util.List;

public class UserPrefs {
    private String mainCategory;
    private List<String> okvds;

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public List<String> getOkvds() {
        return okvds;
    }

    public void setOkvds(List<String> okvds) {
        this.okvds = okvds;
    }
}
