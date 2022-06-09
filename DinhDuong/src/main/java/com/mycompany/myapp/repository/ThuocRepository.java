package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Thuoc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Thuoc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThuocRepository extends JpaRepository<Thuoc, Long> {}
