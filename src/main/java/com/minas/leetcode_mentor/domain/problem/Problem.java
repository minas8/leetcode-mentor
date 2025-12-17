package com.minas.leetcode_mentor.domain.problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private String id;
    private String title;
    private String titleSlug;
    private DifficultyLevel difficulty;
}
