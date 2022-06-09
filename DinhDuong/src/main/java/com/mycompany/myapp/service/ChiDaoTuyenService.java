package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ChiDaoTuyen}.
 */
public interface ChiDaoTuyenService {
    /**
     * Save a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to save.
     * @return the persisted entity.
     */
    ChiDaoTuyenDTO save(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Updates a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to update.
     * @return the persisted entity.
     */
    ChiDaoTuyenDTO update(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Partially updates a chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChiDaoTuyenDTO> partialUpdate(ChiDaoTuyenDTO chiDaoTuyenDTO);

    /**
     * Get all the chiDaoTuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChiDaoTuyenDTO> findAll(Pageable pageable);

    /**
     * Get the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiDaoTuyenDTO> findOne(Long id);

    /**
     * Delete the "id" chiDaoTuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
