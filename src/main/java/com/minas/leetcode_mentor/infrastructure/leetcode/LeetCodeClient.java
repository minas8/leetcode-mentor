package com.minas.leetcode_mentor.infrastructure.leetcode;

import com.minas.leetcode_mentor.infrastructure.leetcode.dto.AllProblemsResponse;
import com.minas.leetcode_mentor.infrastructure.leetcode.dto.SingleProblemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LeetCodeClient {
    private final WebClient webClient;
    private static final String LEETCODE_GRAPHQL_URL = "https://leetcode.com/graphql";

    public Mono<AllProblemsResponse> fetchProblems() {
        String query = """
                {
                  allQuestions {
                    questionId
                    title
                    titleSlug
                    difficulty
                  }
                }
                """;

        return webClient.post()
                .uri(LEETCODE_GRAPHQL_URL)
                .bodyValue(new GraphQLRequest(query))
                .retrieve()
                .bodyToMono(AllProblemsResponse.class);
    }

    public Mono<SingleProblemResponse> fetchProblemBySlug (String tSlug) {
        String query = """ 
                 query getQuestion($titleSlug: String!) {
                  question(titleSlug: $titleSlug) {
                    questionId
                    title
                    titleSlug
                    difficulty
                    content
                    topicTags {
                      name
                      slug
                    }
                  }
                }
                """;
        return webClient.post()
                .uri(LEETCODE_GRAPHQL_URL)
                .bodyValue(new GraphQLRequest(query, Map.of("titleSlug", tSlug)))
                .retrieve()
                .bodyToMono(SingleProblemResponse.class);
    }
}
