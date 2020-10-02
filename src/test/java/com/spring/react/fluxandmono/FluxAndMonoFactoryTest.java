package com.spring.react.fluxandmono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoFactoryTest {
	
	List<String> names = Arrays.asList("adam","eshana","rian");
	
	@Test
	public void fluxUsingIterable()
	{
		Flux<String> flux= Flux.fromIterable(names).log();
		StepVerifier.create(flux)
		.expectNext("adam","eshana","rian")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingArray()
	{
		String[] names = new String[] {"adam","eshana","rian"};
		Flux<String> fromArray = Flux.fromArray(names);
		StepVerifier.create(fromArray)
		.expectNext("adam","eshana","rian")
		.verifyComplete();
		
	}
	
	@Test
	public void fluxUsingStream()
	{
		Flux<String> fromStream = Flux.fromStream(names.stream());
		StepVerifier.create(fromStream)
		.expectNext("adam","eshana","rian")
		.verifyComplete();
	}
	
	@Test
	public void monoUsingJustorEmpty()
	{
		Mono<String> justOrEmpty = Mono.justOrEmpty(null);
		StepVerifier.create(justOrEmpty.log()).verifyComplete();
	}
	
	@Test
	public void monoUsingSupplier()
	{
		Supplier<String> stringSupplier = ()-> "Adam";
		Mono<String> fromSupplier = Mono.fromSupplier(stringSupplier);
		StepVerifier.create(fromSupplier)
		.expectNext("Adam")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingRange()
	{
		Flux<Integer> integerFlux = Flux.range(1, 5);
		StepVerifier.create(integerFlux.log())
		.expectNext(1,2,3,4,5)
		.verifyComplete();
	}

}
