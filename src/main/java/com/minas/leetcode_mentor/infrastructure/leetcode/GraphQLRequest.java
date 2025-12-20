package com.minas.leetcode_mentor.infrastructure.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphQLRequest {
    private String query;
    private Map<String, Object> variables;

    public GraphQLRequest(String query) {
        this.query = query;
    }
}
