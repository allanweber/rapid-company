package com.job.rapid.company.api;

import com.allanweber.java.core.web.exception.ApplicationException;
import com.job.rapid.company.service.CompanyRequest;
import com.job.rapid.company.service.CompanyResponse;
import com.job.rapid.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;

    @Override
    public Flux<CompanyResponse> getAllCompanies() {
        return companyService.getAll();
    }

    @Override
    public Mono<CompanyResponse> getCompany(String companyId) {
        throw new ApplicationException("anmy");
//        return companyService.getCompany(companyId);
    }

    @Override
    public Mono<CompanyResponse> getCompanyByName(String companyName) {
        return companyService.searchCompany(companyName);
    }

    @Override
    public Mono<CompanyResponse> create(@Valid CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest.getName());
    }

    @Override
    public Mono<CompanyResponse> update(String companyId, @Valid CompanyRequest companyRequest) {
        return companyService.updateCompany(companyId, companyRequest);
    }
}
