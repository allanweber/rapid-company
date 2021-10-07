package com.job.rapid.company.api;

import com.allanweber.java.core.web.configuration.swagger.SwaggerConstants;
import com.job.rapid.company.service.CompanyRequest;
import com.job.rapid.company.service.CompanyResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(tags = "Company Api")
@RequestMapping("/companies")
public interface CompanyApi {

    String COMPANY_ID = "companyId";
    String COMPANY_NAME = "companyName";

    @ApiOperation(notes = "Retrieve companies", value = "Companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Companies retrieved"),
            @ApiResponse(code = 403, message = SwaggerConstants.HTTP_403_MESSAGE),
            @ApiResponse(code = 401, message = SwaggerConstants.HTTP_401_MESSAGE)})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Flux<CompanyResponse> getAllCompanies();

    @ApiOperation(notes = "Retrieve company", value = "Company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Company retrieved"),
            @ApiResponse(code = 404, message = SwaggerConstants.HTTP_404_MESSAGE),
            @ApiResponse(code = 403, message = SwaggerConstants.HTTP_403_MESSAGE),
            @ApiResponse(code = 401, message = SwaggerConstants.HTTP_401_MESSAGE)})
    @GetMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    Mono<CompanyResponse> getCompany(@ApiParam(name = COMPANY_ID, value = COMPANY_ID, required = true) @PathVariable(name = COMPANY_ID) String companyId);

    @ApiOperation(notes = "Retrieve company by name", value = "Company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Company retrieved"),
            @ApiResponse(code = 403, message = SwaggerConstants.HTTP_403_MESSAGE),
            @ApiResponse(code = 401, message = SwaggerConstants.HTTP_401_MESSAGE)})
    @GetMapping("/by-name/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    Mono<CompanyResponse> getCompanyByName(@ApiParam(name = COMPANY_NAME, value = COMPANY_NAME, required = true) @PathVariable(name = COMPANY_NAME) String companyName);

    @ApiOperation(notes = "Create a new company", value = "Create company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company created"),
            @ApiResponse(code = 400, message = SwaggerConstants.HTTP_400_MESSAGE),
            @ApiResponse(code = 403, message = SwaggerConstants.HTTP_403_MESSAGE),
            @ApiResponse(code = 401, message = SwaggerConstants.HTTP_401_MESSAGE)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<CompanyResponse> create(@RequestBody CompanyRequest companyRequest);

    @ApiOperation(notes = "Update a new company", value = "Update company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company updated"),
            @ApiResponse(code = 400, message = SwaggerConstants.HTTP_400_MESSAGE),
            @ApiResponse(code = 404, message = SwaggerConstants.HTTP_404_MESSAGE),
            @ApiResponse(code = 403, message = SwaggerConstants.HTTP_403_MESSAGE),
            @ApiResponse(code = 401, message = SwaggerConstants.HTTP_401_MESSAGE)})
    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    Mono<CompanyResponse> update(@ApiParam(name = COMPANY_ID, value = COMPANY_ID, required = true) @PathVariable(name = COMPANY_ID) String companyId,
                                 @RequestBody CompanyRequest companyRequest);
}
