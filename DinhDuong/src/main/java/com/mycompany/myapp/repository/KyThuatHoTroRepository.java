package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.KyThuatHoTro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the KyThuatHoTro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KyThuatHoTroRepository extends JpaRepository<KyThuatHoTro, Long> {}
