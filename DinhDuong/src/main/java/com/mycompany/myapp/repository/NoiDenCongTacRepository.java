package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NoiDenCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NoiDenCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoiDenCongTacRepository extends JpaRepository<NoiDenCongTac, Long> {}
