package com.minas.leetcode_mentor.infrastructure.leetcode;

import com.minas.leetcode_mentor.infrastructure.leetcode.dto.LeetCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LeetCodeClient {
    private final WebClient webClient;
    private static final String LEETCODE_GRAPHQL_URL = "https://leetcode.com/graphql";

    public Mono<LeetCodeResponse> fetchProblems() {
        String query = """
                {
                  allQuestions {
                    title
                    questionId
                    difficulty
                  }
                }
                """;

        return webClient.post()
                .uri(LEETCODE_GRAPHQL_URL)
                .bodyValue(new GraphQLRequest(query))
                .retrieve()
                .bodyToMono(LeetCodeResponse.class);
    }
}
