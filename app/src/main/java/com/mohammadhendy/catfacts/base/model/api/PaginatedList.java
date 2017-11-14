package com.mohammadhendy.catfacts.base.model.api;

import java.util.List;

/**
 * Created by mohammadhendy on 11/14/17.
 */

/**
 * Wrapper Interface for any paginated list
 * @param <T>
 */
public interface PaginatedList<T> {
    List<T> getData();
    int getCurrentPage();
    int getTotal();
    int getItemsPerPage();
}
