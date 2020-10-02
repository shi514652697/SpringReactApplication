package com.spring.react.fluxandmono;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {
	
	@Test
	public void fluxTest()
	{
		Flux<String> fluxString = Flux.just("Spring","Spring Boot","Reative Spring")
				                   //.concatWith(Flux.error(new RuntimeException("Error occured")))
				                   .log();
		
		fluxString.subscribe(System.out::println, e-> System.err.println(e),
				             ()-> System.out.println("completed"));
		
	}
	
	@Test
	public void fluxTestWithCountError()
	{
		Flux<String> fluxString = Flux.just("Spring","Spring Boot","Reative Spring")
				                   .concatWith(Flux.error(new RuntimeException("Error occured")))
				                   .log();
		
		StepVerifier.create(fluxString)
		.expectNextCount(3)
		.expectErrorMessage("Error occured")
		.verify();
		
	}
	
	
	@Test
	public void monoTest()
	{
		Mono<String> monoString = Mono.just("Spring");
		
		StepVerifier.create(monoString.log())
		.expectNext("Spring")
		.verifyComplete();
	}

}
