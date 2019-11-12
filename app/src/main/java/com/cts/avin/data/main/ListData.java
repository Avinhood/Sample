package com.cts.avin.data.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListData {
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Rows> getRows() {
        return rows;
    }
    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    @SerializedName("title")
    String title;
    @SerializedName("rows")
    List<Rows> rows;
}
