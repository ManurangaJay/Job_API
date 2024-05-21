package com.manujay.JobApp.company;

import com.manujay.JobApp.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Boolean updateCompany(Long id,Company company);

    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
