package com.minas.leetcode_mentor.application.services;

import com.minas.leetcode_mentor.application.mappers.LeetCodeProblemMapper;
import com.minas.leetcode_mentor.domain.common.Page;
import com.minas.leetcode_mentor.domain.problem.DifficultyLevel;
import com.minas.leetcode_mentor.domain.problem.Problem;
import com.minas.leetcode_mentor.domain.problem.SingleProblem;
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
    private final InMemoryCache<List<Problem>> problemsCache;
    private final InMemoryCache<SingleProblem> singleProblemCache;

    public Mono<List<Problem>> getProblems(){
        if(problemsCache.get("problems") != null) {
            return Mono.just(problemsCache.get("problems"));
        } else {
            return leetCodeClient.fetchProblems()
                    .map(response -> {
                        List<Problem> problems = LeetCodeProblemMapper.toDomainList(
                                response.getData().getAllQuestions()
                        );
                        problemsCache.put("problems",problems);
                        return problems;
                    });
        }
    }

    public Mono<List<Problem>> getProblemsByDifficulty(DifficultyLevel difficulty) {
        return getProblems()
                .map(problems ->
                        problems.stream()
                                .filter(p -> p.getDifficulty() == difficulty)
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

    public Mono<SingleProblem> getSingleProblemById(String id) {
        if(singleProblemCache.get("problem-" + id) != null) {
            return Mono.just(singleProblemCache.get("problem-" + id));
        } else {
            return getProblems()
                    .flatMap(problems -> problems.stream()
                            .filter(p -> p.getId().equals(id))
                            .findFirst()
                            .map(Mono::just)
                            .orElse(Mono.error(new IllegalArgumentException("Problem not found: " + id))))
                    .flatMap(problem -> leetCodeClient.fetchProblemBySlug(problem.getTitleSlug())
                            .map(response -> {
                                SingleProblem singleProblem = LeetCodeProblemMapper.toDomainSingle(
                                        response.getData().getQuestion()
                                );
                                singleProblemCache.put("problem-" + id, singleProblem);
                                return singleProblem;
                            }));
        }
    }

}
