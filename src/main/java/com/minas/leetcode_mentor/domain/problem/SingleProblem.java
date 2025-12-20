package com.minas.leetcode_mentor.domain.problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor()
public class SingleProblem extends Problem {

    private String content;
    private List<Tag> topicTags;

    public SingleProblem(
            String id,
            String title,
            String titleSlug,
            DifficultyLevel difficulty,
            String content,
            List<Tag> topicTags
    ) {
        super(id, title, titleSlug, difficulty);
        this.content = content;
        this.topicTags = topicTags;
    }

    @Data
    @AllArgsConstructor()
    public static class Tag {
        private String name;
        private String slug;
    }
}
