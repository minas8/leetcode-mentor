package com.minas.leetcode_mentor.infrastructure.leetcode.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllProblemsResponse {
    private AllProblemsData data;

    @Data
    public static class AllProblemsData {
        private List<LeetCodeProblemDto> allQuestions;
    }

    @Data
    public static class LeetCodeProblemDto {
        private String questionId;
        private String title;
        private String titleSlug;
        private String difficulty;
    }
}
