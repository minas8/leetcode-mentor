package com.minas.leetcode_mentor.controller;

import com.minas.leetcode_mentor.application.services.LeetCodeProblemService;
import com.minas.leetcode_mentor.domain.common.Page;
import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import com.minas.leetcode_mentor.domain.problem.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeetCodeController {
    private final LeetCodeProblemService problemService;

    /// Type of Query Param
    /// Ex. http://localhost:8080/leetcode/problems?difficulty=EASY
    @GetMapping("/leetcode/problems")
    public Mono<List<Problem>> getProblems(@RequestParam(required = false) DifficultyLevel difficulty) {
        if(difficulty == null) {
            return problemService.getProblems();
        }
        return problemService.getProblemsByDifficulty(difficulty);
    }

    /// Type of Path Param
    @GetMapping("/leetcode/problems/difficulty/{difficulty}")
    public Mono<List<Problem>> getProblemsByPath(@PathVariable DifficultyLevel difficulty) {
        return problemService.getProblemsByDifficulty(difficulty);
    }

    @GetMapping("/leetcode/problems/paged")
    public Mono<Page<Problem>> getProblemsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return problemService.getProblemsPaged(page,size);
    }

}
