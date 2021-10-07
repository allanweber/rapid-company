package com.job.rapid.company.service;

import com.allanweber.java.core.utilities.DateHelper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyResponse {

    private String id;

    private String name;

    @JsonFormat(pattern = DateHelper.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = DateHelper.DATE_TIME_FORMAT)
    private LocalDateTime lastModifiedDate;

    public static CompanyResponse fromEntity(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .createdDate(company.getCreatedDate())
                .lastModifiedDate(company.getLastModifiedDate())
                .build();
    }
}
