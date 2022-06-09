package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NhanVienTiepNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NhanVienTiepNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanVienTiepNhanRepository extends JpaRepository<NhanVienTiepNhan, Long> {}
