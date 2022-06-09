package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ChiDaoTuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiDaoTuyenRepository extends JpaRepository<ChiDaoTuyen, Long> {}
