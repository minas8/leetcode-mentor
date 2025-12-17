package com.minas.leetcode_mentor.infrastructure.leetcode.dto;

import lombok.Data;

import java.util.List;

@Data
public class SingleProblemResponse {
    private SingleProblemData data;

    @Data
    public static class SingleProblemData {
        private QuestionDto question;

        @Data
        public static class QuestionDto {
            private String questionId;
            private String title;
            private String titleSlug;
            private String difficulty;
            private String content;
            private List<Tag> topicTags;
        }

        @Data
        public static class Tag {
            private String name;
            private String slug;
        }
    }
}
