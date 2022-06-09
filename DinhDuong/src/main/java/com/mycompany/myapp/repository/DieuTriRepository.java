package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DieuTri;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DieuTri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DieuTriRepository extends JpaRepository<DieuTri, Long> {}
