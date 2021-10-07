package com.job.rapid.company.service;

import com.allanweber.java.core.database.DateAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company implements DateAuditableEntity {

    @Id
    private String id;

    private String name;

    @Builder.Default
    private Boolean enabled = true;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public Company update(CompanyRequest companyRequest) {
        this.name = companyRequest.getName();
        return this;
    }
}
