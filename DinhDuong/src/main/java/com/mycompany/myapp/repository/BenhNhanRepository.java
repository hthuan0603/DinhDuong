package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BenhNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BenhNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Long> {}
