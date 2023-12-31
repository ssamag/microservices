package com.spring.microservice.organizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.microservice.organizationservice.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
