package org.example.core;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            System.out.println("hello world");
        });

        Thread.sleep(Duration.ofSeconds(1).toMillis());
        System.out.println("goodbye world");
    }
}