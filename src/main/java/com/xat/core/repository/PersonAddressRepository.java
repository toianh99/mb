package com.xat.core.repository;

import com.xat.core.domain.PersonAddress;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, UUID> {
}
