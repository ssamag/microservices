package com.spring.microservice.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "OrganizationDto Model Information")
public class OrganizationDto {
    private Long id;
    
    @Schema(description = "Employee Organization Name")
    private String organizationName;
    
    @Schema(description = "Employee Organization Description")
    private String organizationDescription;
    
    @Schema(description = "Employee Organization Code")
    private String organizationCode;
    
    @Schema(description = "Employee Organization Creation Date")
    private LocalDateTime createdDate;
}
