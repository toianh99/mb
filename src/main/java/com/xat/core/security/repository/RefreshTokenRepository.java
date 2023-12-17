package com.xat.core.security.repository;

import com.xat.core.security.domain.RefreshToken;
import com.xat.core.security.domain.User;
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
