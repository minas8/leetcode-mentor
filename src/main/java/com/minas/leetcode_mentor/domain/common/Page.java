package com.minas.leetcode_mentor.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/// Generic DTO Page of objects <T>
@Data
@AllArgsConstructor
public class Page<T> {
    private List<T> content;      // The list of the results
    private int page;             // Page number
    private int size;             // Count of items in one page
    private int totalElements;    // Total count
}
