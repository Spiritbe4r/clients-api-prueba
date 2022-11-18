package com.oh.apiclients.domain.repository;

import com.oh.apiclients.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
