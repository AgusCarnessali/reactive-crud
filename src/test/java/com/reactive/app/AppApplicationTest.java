package com.reactive.app;

import org.junit.jupiter.api.Test;

class AppApplicationTest {
    @Test
    void mainMethodExecutesWithoutExceptions() {
        AppApplication.main(new String[]{});
    }

    @Test
    void mainMethodExecutesWithArguments() {
        String[] args = {"--spring.profiles.active=test", "--server.port=0"};
        AppApplication.main(args);
    }
}