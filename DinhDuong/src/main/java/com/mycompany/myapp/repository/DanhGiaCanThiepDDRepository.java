package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DanhGiaCanThiepDD;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DanhGiaCanThiepDD entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhGiaCanThiepDDRepository extends JpaRepository<DanhGiaCanThiepDD, Long> {}
