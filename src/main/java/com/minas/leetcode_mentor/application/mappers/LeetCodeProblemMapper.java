package com.minas.leetcode_mentor.application.mappers;

import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import com.minas.leetcode_mentor.domain.problem.Problem;
import com.minas.leetcode_mentor.infrastructure.leetcode.dto.LeetCodeProblemDto;

import java.util.List;

public class LeetCodeProblemMapper {
    public static Problem toDomain(LeetCodeProblemDto dto) {
        // Convert from DTO to Domain
        int qId = Integer.parseInt(dto.getQuestionId());
        DifficultyLevel level = DifficultyLevel.valueOf(dto.getDifficulty().toUpperCase());
        return new Problem(qId,dto.getTitle(),level);
    }

    public static List<Problem> toDomainList(List<LeetCodeProblemDto> dtos) {
        // Loop on a list of DTO → List of Domain
        return dtos.stream()
                .map(LeetCodeProblemMapper::toDomain)
                .toList();

//        List<Problem> problems = new ArrayList<>();
//        for (LeetCodeProblemDto dto : dtos) {
//            problems.add(toDomain(dto));
//        }
//        return problems;
    }
}
