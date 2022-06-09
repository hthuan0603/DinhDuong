package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BaoCaoTaiChinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BaoCaoTaiChinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaoCaoTaiChinhRepository extends JpaRepository<BaoCaoTaiChinh, Long> {}
