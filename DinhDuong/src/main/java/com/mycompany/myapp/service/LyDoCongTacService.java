package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LyDoCongTac}.
 */
public interface LyDoCongTacService {
    /**
     * Save a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to save.
     * @return the persisted entity.
     */
    LyDoCongTacDTO save(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Updates a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to update.
     * @return the persisted entity.
     */
    LyDoCongTacDTO update(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Partially updates a lyDoCongTac.
     *
     * @param lyDoCongTacDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LyDoCongTacDTO> partialUpdate(LyDoCongTacDTO lyDoCongTacDTO);

    /**
     * Get all the lyDoCongTacs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LyDoCongTacDTO> findAll(Pageable pageable);

    /**
     * Get the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LyDoCongTacDTO> findOne(Long id);

    /**
     * Delete the "id" lyDoCongTac.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
