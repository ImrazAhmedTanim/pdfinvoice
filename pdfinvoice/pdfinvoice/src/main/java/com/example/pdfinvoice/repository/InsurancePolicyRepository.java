package com.example.pdfinvoice.repository;

import com.example.pdfinvoice.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    // JpaRepository provides built-in methods like:
    // save(), findById(), findAll(), deleteById(), etc.

    // You can add custom query methods if needed, e.g.:
    // List<InsurancePolicy> findByPolicyName(String policyName);
}
