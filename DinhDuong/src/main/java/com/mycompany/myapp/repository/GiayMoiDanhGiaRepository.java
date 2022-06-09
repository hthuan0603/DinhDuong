package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GiayMoiDanhGia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GiayMoiDanhGia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiayMoiDanhGiaRepository extends JpaRepository<GiayMoiDanhGia, Long> {}
