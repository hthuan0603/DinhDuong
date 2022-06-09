package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.HoTroDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.HoTro}.
 */
public interface HoTroService {
    /**
     * Save a hoTro.
     *
     * @param hoTroDTO the entity to save.
     * @return the persisted entity.
     */
    HoTroDTO save(HoTroDTO hoTroDTO);

    /**
     * Updates a hoTro.
     *
     * @param hoTroDTO the entity to update.
     * @return the persisted entity.
     */
    HoTroDTO update(HoTroDTO hoTroDTO);

    /**
     * Partially updates a hoTro.
     *
     * @param hoTroDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HoTroDTO> partialUpdate(HoTroDTO hoTroDTO);

    /**
     * Get all the hoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HoTroDTO> findAll(Pageable pageable);

    /**
     * Get the "id" hoTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HoTroDTO> findOne(Long id);

    /**
     * Delete the "id" hoTro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
