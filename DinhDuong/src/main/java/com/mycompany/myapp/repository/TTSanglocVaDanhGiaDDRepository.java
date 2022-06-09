package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TTSanglocVaDanhGiaDD;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TTSanglocVaDanhGiaDD entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TTSanglocVaDanhGiaDDRepository extends JpaRepository<TTSanglocVaDanhGiaDD, Long> {}
