package com.demo.demo1racecondition.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CounterControllerTest {

    private static final String[] TARGET_URLS = {
            "http://localhost:8081/counter/increase",
            "http://localhost:8080/counter/increase"
    };

    // WebClient는 논블로킹 방식
    private static final WebClient webClient = WebClient.builder().build();

    @Test
    void counter() {
        int totalThreads = 1000;

        // 스레드 풀: 호출별로 스레드 하나씩 사용
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        List<CompletableFuture<Void>> futures = new ArrayList<>(totalThreads);

        for (int i = 0; i < totalThreads; i++) {
            final int index = i;
            // 어떤 포트를 호출할지 결정
            final String url = (index % 2 == 0
                    ? TARGET_URLS[0]
                    : TARGET_URLS[1]);

            // 각 스레드에서 비동기 요청 실행
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                webClient.post()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(String.class)
                        .doOnNext(resp -> System.out.printf("Thread-%03d → %s : %s%n", index, url, resp))
                        .doOnError(err -> System.err.printf("Thread-%03d ERROR: %s%n", index, err.getMessage()))
                        .block();  // 블로킹하지 않으려면 .subscribe() 후 별도 동기화 필요하나,
                // 각 스레드가 호출만 책임지고 끝내기 위해 block() 사용
            }, executor);

            futures.add(future);
        }

        // 모든 스레드의 작업이 끝날 때까지 대기
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();
    }
}
