package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.VatTuHoTro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VatTuHoTro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VatTuHoTroRepository extends JpaRepository<VatTuHoTro, Long> {}
