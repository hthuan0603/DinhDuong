package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BaoCaoTaiChinhDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.BaoCaoTaiChinh}.
 */
public interface BaoCaoTaiChinhService {
    /**
     * Save a baoCaoTaiChinh.
     *
     * @param baoCaoTaiChinhDTO the entity to save.
     * @return the persisted entity.
     */
    BaoCaoTaiChinhDTO save(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO);

    /**
     * Updates a baoCaoTaiChinh.
     *
     * @param baoCaoTaiChinhDTO the entity to update.
     * @return the persisted entity.
     */
    BaoCaoTaiChinhDTO update(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO);

    /**
     * Partially updates a baoCaoTaiChinh.
     *
     * @param baoCaoTaiChinhDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BaoCaoTaiChinhDTO> partialUpdate(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO);

    /**
     * Get all the baoCaoTaiChinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BaoCaoTaiChinhDTO> findAll(Pageable pageable);

    /**
     * Get the "id" baoCaoTaiChinh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BaoCaoTaiChinhDTO> findOne(Long id);

    /**
     * Delete the "id" baoCaoTaiChinh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
