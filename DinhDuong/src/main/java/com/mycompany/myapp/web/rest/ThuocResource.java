package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Thuoc;
import com.mycompany.myapp.repository.ThuocRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Thuoc}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ThuocResource {

    private final Logger log = LoggerFactory.getLogger(ThuocResource.class);

    private static final String ENTITY_NAME = "thuoc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThuocRepository thuocRepository;

    public ThuocResource(ThuocRepository thuocRepository) {
        this.thuocRepository = thuocRepository;
    }

    /**
     * {@code POST  /thuocs} : Create a new thuoc.
     *
     * @param thuoc the thuoc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thuoc, or with status {@code 400 (Bad Request)} if the thuoc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thuocs")
    public ResponseEntity<Thuoc> createThuoc(@RequestBody Thuoc thuoc) throws URISyntaxException {
        log.debug("REST request to save Thuoc : {}", thuoc);
        if (thuoc.getId() != null) {
            throw new BadRequestAlertException("A new thuoc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Thuoc result = thuocRepository.save(thuoc);
        return ResponseEntity
            .created(new URI("/api/thuocs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thuocs/:id} : Updates an existing thuoc.
     *
     * @param id the id of the thuoc to save.
     * @param thuoc the thuoc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuoc,
     * or with status {@code 400 (Bad Request)} if the thuoc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thuoc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thuocs/{id}")
    public ResponseEntity<Thuoc> updateThuoc(@PathVariable(value = "id", required = false) final Long id, @RequestBody Thuoc thuoc)
        throws URISyntaxException {
        log.debug("REST request to update Thuoc : {}, {}", id, thuoc);
        if (thuoc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thuoc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuocRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Thuoc result = thuocRepository.save(thuoc);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuoc.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /thuocs/:id} : Partial updates given fields of an existing thuoc, field will ignore if it is null
     *
     * @param id the id of the thuoc to save.
     * @param thuoc the thuoc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuoc,
     * or with status {@code 400 (Bad Request)} if the thuoc is not valid,
     * or with status {@code 404 (Not Found)} if the thuoc is not found,
     * or with status {@code 500 (Internal Server Error)} if the thuoc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/thuocs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Thuoc> partialUpdateThuoc(@PathVariable(value = "id", required = false) final Long id, @RequestBody Thuoc thuoc)
        throws URISyntaxException {
        log.debug("REST request to partial update Thuoc partially : {}, {}", id, thuoc);
        if (thuoc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, thuoc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!thuocRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Thuoc> result = thuocRepository
            .findById(thuoc.getId())
            .map(existingThuoc -> {
                if (thuoc.getTenThuoc() != null) {
                    existingThuoc.setTenThuoc(thuoc.getTenThuoc());
                }
                if (thuoc.getDuongDung() != null) {
                    existingThuoc.setDuongDung(thuoc.getDuongDung());
                }
                if (thuoc.getSoLuong() != null) {
                    existingThuoc.setSoLuong(thuoc.getSoLuong());
                }
                if (thuoc.getCachDung() != null) {
                    existingThuoc.setCachDung(thuoc.getCachDung());
                }
                if (thuoc.getHoatChat() != null) {
                    existingThuoc.setHoatChat(thuoc.getHoatChat());
                }
                if (thuoc.getdVT() != null) {
                    existingThuoc.setdVT(thuoc.getdVT());
                }
                if (thuoc.getDonGia() != null) {
                    existingThuoc.setDonGia(thuoc.getDonGia());
                }
                if (thuoc.getThanhTien() != null) {
                    existingThuoc.setThanhTien(thuoc.getThanhTien());
                }
                if (thuoc.getLoaiTTCu() != null) {
                    existingThuoc.setLoaiTTCu(thuoc.getLoaiTTCu());
                }
                if (thuoc.getLoaiTTMoi() != null) {
                    existingThuoc.setLoaiTTMoi(thuoc.getLoaiTTMoi());
                }
                if (thuoc.getLieuDung() != null) {
                    existingThuoc.setLieuDung(thuoc.getLieuDung());
                }
                if (thuoc.getkS() != null) {
                    existingThuoc.setkS(thuoc.getkS());
                }

                return existingThuoc;
            })
            .map(thuocRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuoc.getId().toString())
        );
    }

    /**
     * {@code GET  /thuocs} : get all the thuocs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thuocs in body.
     */
    @GetMapping("/thuocs")
    public List<Thuoc> getAllThuocs() {
        log.debug("REST request to get all Thuocs");
        return thuocRepository.findAll();
    }

    /**
     * {@code GET  /thuocs/:id} : get the "id" thuoc.
     *
     * @param id the id of the thuoc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thuoc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thuocs/{id}")
    public ResponseEntity<Thuoc> getThuoc(@PathVariable Long id) {
        log.debug("REST request to get Thuoc : {}", id);
        Optional<Thuoc> thuoc = thuocRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thuoc);
    }

    /**
     * {@code DELETE  /thuocs/:id} : delete the "id" thuoc.
     *
     * @param id the id of the thuoc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thuocs/{id}")
    public ResponseEntity<Void> deleteThuoc(@PathVariable Long id) {
        log.debug("REST request to delete Thuoc : {}", id);
        thuocRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
