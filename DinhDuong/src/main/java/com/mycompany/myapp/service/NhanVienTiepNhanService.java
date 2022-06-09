package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.NhanVienTiepNhan}.
 */
public interface NhanVienTiepNhanService {
    /**
     * Save a nhanVienTiepNhan.
     *
     * @param nhanVienTiepNhanDTO the entity to save.
     * @return the persisted entity.
     */
    NhanVienTiepNhanDTO save(NhanVienTiepNhanDTO nhanVienTiepNhanDTO);

    /**
     * Updates a nhanVienTiepNhan.
     *
     * @param nhanVienTiepNhanDTO the entity to update.
     * @return the persisted entity.
     */
    NhanVienTiepNhanDTO update(NhanVienTiepNhanDTO nhanVienTiepNhanDTO);

    /**
     * Partially updates a nhanVienTiepNhan.
     *
     * @param nhanVienTiepNhanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NhanVienTiepNhanDTO> partialUpdate(NhanVienTiepNhanDTO nhanVienTiepNhanDTO);

    /**
     * Get all the nhanVienTiepNhans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NhanVienTiepNhanDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nhanVienTiepNhan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhanVienTiepNhanDTO> findOne(Long id);

    /**
     * Delete the "id" nhanVienTiepNhan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
