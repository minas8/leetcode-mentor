package com.minas.leetcode_mentor.infrastructure.leetcode.dto;

import lombok.Data;

import java.util.List;

@Data
public class LeetCodeData {
    private List<LeetCodeProblemDto> allQuestions;
}
