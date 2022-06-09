package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.KetQuaCongTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the KetQuaCongTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KetQuaCongTacRepository extends JpaRepository<KetQuaCongTac, Long> {}
