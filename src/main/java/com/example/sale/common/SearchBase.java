package com.example.sale.common;

import java.util.Map;

public class SearchBase {
    private int limit;
    private int page;
    private String sortType;
    private String sortField;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    protected void setParamCommon(Map<String, String> params){
        this.limit = Integer.parseInt(params.getOrDefault("limit", "20"));
        this.page = Integer.parseInt(params.getOrDefault("page", "0"));
        this.sortType = params.getOrDefault("sortType", "ASC");
        this.sortField = params.getOrDefault("sortField", "name");
    }

}
