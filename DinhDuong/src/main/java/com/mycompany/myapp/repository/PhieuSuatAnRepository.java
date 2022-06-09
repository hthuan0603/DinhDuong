package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PhieuSuatAn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PhieuSuatAn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhieuSuatAnRepository extends JpaRepository<PhieuSuatAn, Long> {}
