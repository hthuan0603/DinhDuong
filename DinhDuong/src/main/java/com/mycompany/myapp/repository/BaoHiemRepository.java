package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BaoHiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BaoHiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaoHiemRepository extends JpaRepository<BaoHiem, Long> {}
