package com.globits.security.repository;

import com.globits.security.domain.RefreshToken;
import com.globits.security.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);

   @Modifying
   int deleteByUser(User user);
}
