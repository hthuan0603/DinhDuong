package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.VatTuHoTro}.
 */
public interface VatTuHoTroService {
    /**
     * Save a vatTuHoTro.
     *
     * @param vatTuHoTroDTO the entity to save.
     * @return the persisted entity.
     */
    VatTuHoTroDTO save(VatTuHoTroDTO vatTuHoTroDTO);

    /**
     * Updates a vatTuHoTro.
     *
     * @param vatTuHoTroDTO the entity to update.
     * @return the persisted entity.
     */
    VatTuHoTroDTO update(VatTuHoTroDTO vatTuHoTroDTO);

    /**
     * Partially updates a vatTuHoTro.
     *
     * @param vatTuHoTroDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VatTuHoTroDTO> partialUpdate(VatTuHoTroDTO vatTuHoTroDTO);

    /**
     * Get all the vatTuHoTros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VatTuHoTroDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vatTuHoTro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VatTuHoTroDTO> findOne(Long id);

    /**
     * Delete the "id" vatTuHoTro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
