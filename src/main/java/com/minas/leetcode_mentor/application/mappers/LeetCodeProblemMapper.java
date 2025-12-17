package com.minas.leetcode_mentor.application.mappers;

import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import com.minas.leetcode_mentor.domain.problem.Problem;
import com.minas.leetcode_mentor.domain.problem.SingleProblem;
import com.minas.leetcode_mentor.infrastructure.leetcode.dto.AllProblemsResponse.LeetCodeProblemDto;
import com.minas.leetcode_mentor.infrastructure.leetcode.dto.SingleProblemResponse.SingleProblemData.QuestionDto;

import java.util.List;

public class LeetCodeProblemMapper {
    public static Problem toDomain(LeetCodeProblemDto dto) {
        // Convert from DTO to Domain
        return new Problem(
                dto.getQuestionId(),
                dto.getTitle(),
                dto.getTitleSlug(),
                DifficultyLevel.valueOf(dto.getDifficulty().toUpperCase())
        );
    }

    public static List<Problem> toDomainList(List<LeetCodeProblemDto> dtos) {
        // Loop on a list of DTO → List of Domain
        return dtos.stream()
                .map(LeetCodeProblemMapper::toDomain)
                .toList();
    }

    public static SingleProblem toDomainSingle (QuestionDto que) {
        return new SingleProblem(
                que.getQuestionId(),
                que.getTitle(),
                que.getTitleSlug(),
                DifficultyLevel.valueOf(que.getDifficulty().toUpperCase()),
                que.getContent(),
                que.getTopicTags().stream()
                        .map(tag -> new SingleProblem.Tag(
                                tag.getName(),
                                tag.getSlug())
                        )
                        .toList()
        );
    }

}

//    public static List<Problem> toDomainList(List<LeetCodeProblemDto> dtos) {
// Loop on a list of DTO → List of Domain
//        List<Problem> problems = new ArrayList<>();
//        for (LeetCodeProblemDto dto : dtos) {
//            problems.add(toDomain(dto));
//        }
//        return problems;
//    }
