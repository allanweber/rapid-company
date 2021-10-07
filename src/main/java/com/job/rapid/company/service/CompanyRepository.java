package com.job.rapid.company.service;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {

    Mono<Company> findByName(String companyName);

    Mono<Boolean> existsByName(String companyName);
}
