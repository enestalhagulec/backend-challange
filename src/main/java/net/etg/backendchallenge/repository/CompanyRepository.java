package net.etg.backendchallenge.repository;

import net.etg.backendchallenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE companyName=:companyName")
    Optional<Company> findCompanyByName(String companyName);

    @Query("SELECT c FROM Company c WHERE id=:id")
    Optional<Company> findById(Long id);
}
