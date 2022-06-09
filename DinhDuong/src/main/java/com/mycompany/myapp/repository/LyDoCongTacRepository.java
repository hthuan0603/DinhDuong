package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LyDoCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LyDoCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LyDoCongTacRepository extends JpaRepository<LyDoCongTac, Long> {}
