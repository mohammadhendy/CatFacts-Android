package com.mohammadhendy.catfacts.model.api;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.mohammadhendy.catfacts.base.model.api.PaginatedList;

import java.util.List;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@JsonObject(fieldNamingPolicy = JsonObject.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
public class ListResponse<T> implements PaginatedList<T> {

    @JsonField
    private int total;
    @JsonField
    private int currentPage;
    @JsonField
    private int perPage;
    @JsonField
    private int totalPages;
    @JsonField
    private List<T> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int getItemsPerPage() {
        return getPerPage();
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
