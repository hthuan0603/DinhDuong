package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.KyThuatHoTro}.
 */
public interface KyThuatHoTroService {
    /**
     * Save a kyThuatHoTro.
     *
     * @param kyThuatHoTroDTO the entity to save.
     * @return the persisted entity.
     */
    KyThuatHoTroDTO save(KyThuatHoTroDTO kyThuatHoTroDTO);

    /**
     * Updates a kyThuatHoTro.
     *
     * @param kyThuatHoTroDTO the entity to update.
     * @return the persisted entity.
     */
    KyThuatHoTroDTO update(KyThuatHoTroDTO kyThuatHoTroDTO);

    /**
     * Partially updates a kyThuatHoTro.
     *
     * @param kyThuatHoTroDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KyThuatHoTroDTO> partialUpdate(KyThuatHoTroDTO kyThuatHoTroDTO);

    /**
     * Get all the kyThuatHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KyThuatHoTroDTO> findAll(Pageable pageable);

    /**
     * Get the "id" kyThuatHoTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KyThuatHoTroDTO> findOne(Long id);

    /**
     * Delete the "id" kyThuatHoTro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
