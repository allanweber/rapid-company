package com.job.rapid.company.service;

import com.allanweber.java.core.web.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.http.HttpStatus.CONFLICT;

@RequiredArgsConstructor
@Service
public class CompanyService {

    public static final String COMPANY_NOT_FOUND = "Company not found";

    private final CompanyRepository companyRepository;

    public Flux<CompanyResponse> getAll() {
        return companyRepository.findAll()
                .map(CompanyResponse::fromEntity);
    }

    public Mono<CompanyResponse> getCompany(@NotNull String companyId) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ApplicationException(HttpStatus.NOT_FOUND, COMPANY_NOT_FOUND)))
                .map(CompanyResponse::fromEntity);
    }

    public Mono<CompanyResponse> searchCompany(@NotNull String companyName) {
        return companyRepository.findByName(companyName)
                .map(CompanyResponse::fromEntity);
    }

    public Mono<Boolean> companyExistsByName(@NotNull String companyName) {
        return companyRepository.existsByName(companyName);
    }

    public Mono<CompanyResponse> createCompany(@NotNull String companyName) {
        return companyExistsByName(companyName)
                .doOnNext(validateCompany())
                .onErrorResume(Mono::error)
                .flatMap(unused -> companyRepository.save(Company.builder().name(companyName).build()))
                .map(CompanyResponse::fromEntity);
    }

    public Mono<CompanyResponse> updateCompany(String companyId, CompanyRequest companyRequest) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ApplicationException(HttpStatus.NOT_FOUND, COMPANY_NOT_FOUND)))
                .map(entity -> entity.update(companyRequest))
                .flatMap(companyRepository::save)
                .map(CompanyResponse::fromEntity);
    }

    private Consumer<Boolean> validateCompany() {
        return exist -> {
            if (exist) {
                throw new ApplicationException(CONFLICT, "Company already exist");
            }
        };
    }
}
