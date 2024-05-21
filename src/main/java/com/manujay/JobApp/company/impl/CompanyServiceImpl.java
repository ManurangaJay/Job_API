package com.manujay.JobApp.company.impl;

import com.manujay.JobApp.company.Company;
import com.manujay.JobApp.company.CompanyRepository;
import com.manujay.JobApp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<com.manujay.JobApp.company.Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company1 = companyOptional.get();
            company1.setDescription(updatedCompany.getDescription());
            company1.setName(updatedCompany.getName());
            companyRepository.save(company1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}

