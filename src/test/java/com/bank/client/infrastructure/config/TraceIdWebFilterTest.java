package com.bank.client.infrastructure.config;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class TraceIdWebFilterTest {
    @Test
    void testFilterGeneratesTraceIdIfNotExists() {
        TraceIdWebFilter filter = new TraceIdWebFilter();

        MockServerWebExchange exchange =
                MockServerWebExchange.from(MockServerHttpRequest.get("/test").build());

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(filter.filter(exchange, chain))
                .verifyComplete();

        String traceId = exchange.getResponse().getHeaders().getFirst("X-Trace-Id");
        assert traceId != null && !traceId.isBlank();

        MDC.clear();
    }

    @Test
    void testFilterKeepsExistingTraceId() {
        TraceIdWebFilter filter = new TraceIdWebFilter();

        String existingTrace = UUID.randomUUID().toString();

        MockServerWebExchange exchange =
                MockServerWebExchange.from(
                        MockServerHttpRequest.get("/test")
                                .header("X-Trace-Id", existingTrace)
                                .build()
                );

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(filter.filter(exchange, chain))
                .verifyComplete();

        String traceId = exchange.getResponse().getHeaders().getFirst("X-Trace-Id");
        assert existingTrace.equals(traceId);

        MDC.clear();
    }
}
