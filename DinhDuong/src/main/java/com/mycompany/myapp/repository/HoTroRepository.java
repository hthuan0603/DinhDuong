package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.HoTro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the HoTro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoTroRepository extends JpaRepository<HoTro, Long> {}
