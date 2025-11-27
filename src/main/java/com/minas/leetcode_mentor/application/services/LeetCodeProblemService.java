package com.minas.leetcode_mentor.application.services;

import com.minas.leetcode_mentor.application.mappers.LeetCodeProblemMapper;
import com.minas.leetcode_mentor.domain.common.Page;
import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import com.minas.leetcode_mentor.domain.problem.Problem;
import com.minas.leetcode_mentor.infrastructure.cache.InMemoryCache;
import com.minas.leetcode_mentor.infrastructure.leetcode.LeetCodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LeetCodeProblemService {
    private final LeetCodeClient leetCodeClient;
    private final InMemoryCache<List<Problem>> cache;

    public Mono<List<Problem>> getProblems(){
        if(cache.get("problems") != null) {
            return Mono.just(cache.get("problems"));
        } else {
            return leetCodeClient.fetchProblems()
                    .map(response -> {
                        List<Problem> problems = LeetCodeProblemMapper.toDomainList(
                                response.getData().getAllQuestions()
                        );
                        cache.put("problems",problems);
                        return problems;
                    });
        }
    }

    public Mono<List<Problem>> getProblemsByDifficulty(DifficultyLevel level) {
        return getProblems()
                .map(problems ->
                        problems.stream()
                                .filter(p -> p.getLevel() == level)
                                .toList()
                );
    }

    public Mono<Page<Problem>> getProblemsPaged(int page, int size) {
        return getProblems()
                .map(allProblems -> {
                    int total = allProblems.size();
                    int from = page * size;
                    int to = Math.min(from + size, total);

                    // If the page the user asked for is out of range > return an empty page
                    if (from >= total) {
                        return new Page<>(List.of(), page, size, total);
                    }

                    List<Problem> slice = allProblems.subList(from, to);
                    return new Page<>(slice, page, size, total);
                });
    }

}
