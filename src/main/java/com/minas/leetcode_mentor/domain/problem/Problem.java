package com.minas.leetcode_mentor.domain.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Problem {
    private int id;
    private String name;
    private DifficultyLevel level;
}
