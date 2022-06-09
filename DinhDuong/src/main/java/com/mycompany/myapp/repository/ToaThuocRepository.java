package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ToaThuoc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ToaThuoc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToaThuocRepository extends JpaRepository<ToaThuoc, Long> {}
