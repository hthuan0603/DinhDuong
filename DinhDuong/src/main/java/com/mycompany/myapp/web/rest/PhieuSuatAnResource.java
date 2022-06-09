package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PhieuSuatAn;
import com.mycompany.myapp.repository.PhieuSuatAnRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PhieuSuatAn}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PhieuSuatAnResource {

    private final Logger log = LoggerFactory.getLogger(PhieuSuatAnResource.class);

    private static final String ENTITY_NAME = "phieuSuatAn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhieuSuatAnRepository phieuSuatAnRepository;

    public PhieuSuatAnResource(PhieuSuatAnRepository phieuSuatAnRepository) {
        this.phieuSuatAnRepository = phieuSuatAnRepository;
    }

    /**
     * {@code POST  /phieu-suat-ans} : Create a new phieuSuatAn.
     *
     * @param phieuSuatAn the phieuSuatAn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phieuSuatAn, or with status {@code 400 (Bad Request)} if the phieuSuatAn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/phieu-suat-ans")
    public ResponseEntity<PhieuSuatAn> createPhieuSuatAn(@RequestBody PhieuSuatAn phieuSuatAn) throws URISyntaxException {
        log.debug("REST request to save PhieuSuatAn : {}", phieuSuatAn);
        if (phieuSuatAn.getId() != null) {
            throw new BadRequestAlertException("A new phieuSuatAn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhieuSuatAn result = phieuSuatAnRepository.save(phieuSuatAn);
        return ResponseEntity
            .created(new URI("/api/phieu-suat-ans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /phieu-suat-ans/:id} : Updates an existing phieuSuatAn.
     *
     * @param id the id of the phieuSuatAn to save.
     * @param phieuSuatAn the phieuSuatAn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phieuSuatAn,
     * or with status {@code 400 (Bad Request)} if the phieuSuatAn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phieuSuatAn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/phieu-suat-ans/{id}")
    public ResponseEntity<PhieuSuatAn> updatePhieuSuatAn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PhieuSuatAn phieuSuatAn
    ) throws URISyntaxException {
        log.debug("REST request to update PhieuSuatAn : {}, {}", id, phieuSuatAn);
        if (phieuSuatAn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phieuSuatAn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phieuSuatAnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PhieuSuatAn result = phieuSuatAnRepository.save(phieuSuatAn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phieuSuatAn.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /phieu-suat-ans/:id} : Partial updates given fields of an existing phieuSuatAn, field will ignore if it is null
     *
     * @param id the id of the phieuSuatAn to save.
     * @param phieuSuatAn the phieuSuatAn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phieuSuatAn,
     * or with status {@code 400 (Bad Request)} if the phieuSuatAn is not valid,
     * or with status {@code 404 (Not Found)} if the phieuSuatAn is not found,
     * or with status {@code 500 (Internal Server Error)} if the phieuSuatAn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/phieu-suat-ans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PhieuSuatAn> partialUpdatePhieuSuatAn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PhieuSuatAn phieuSuatAn
    ) throws URISyntaxException {
        log.debug("REST request to partial update PhieuSuatAn partially : {}, {}", id, phieuSuatAn);
        if (phieuSuatAn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phieuSuatAn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phieuSuatAnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PhieuSuatAn> result = phieuSuatAnRepository
            .findById(phieuSuatAn.getId())
            .map(existingPhieuSuatAn -> {
                if (phieuSuatAn.getTenDv() != null) {
                    existingPhieuSuatAn.setTenDv(phieuSuatAn.getTenDv());
                }
                if (phieuSuatAn.getMaDV() != null) {
                    existingPhieuSuatAn.setMaDV(phieuSuatAn.getMaDV());
                }
                if (phieuSuatAn.getMaBN() != null) {
                    existingPhieuSuatAn.setMaBN(phieuSuatAn.getMaBN());
                }
                if (phieuSuatAn.getMaTheBHYT() != null) {
                    existingPhieuSuatAn.setMaTheBHYT(phieuSuatAn.getMaTheBHYT());
                }
                if (phieuSuatAn.gettGSuDung() != null) {
                    existingPhieuSuatAn.settGSuDung(phieuSuatAn.gettGSuDung());
                }
                if (phieuSuatAn.gettGChiDinh() != null) {
                    existingPhieuSuatAn.settGChiDinh(phieuSuatAn.gettGChiDinh());
                }
                if (phieuSuatAn.getChuanDoan() != null) {
                    existingPhieuSuatAn.setChuanDoan(phieuSuatAn.getChuanDoan());
                }
                if (phieuSuatAn.getChuanDoanKT() != null) {
                    existingPhieuSuatAn.setChuanDoanKT(phieuSuatAn.getChuanDoanKT());
                }
                if (phieuSuatAn.getGhiChu() != null) {
                    existingPhieuSuatAn.setGhiChu(phieuSuatAn.getGhiChu());
                }

                return existingPhieuSuatAn;
            })
            .map(phieuSuatAnRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phieuSuatAn.getId().toString())
        );
    }

    /**
     * {@code GET  /phieu-suat-ans} : get all the phieuSuatAns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phieuSuatAns in body.
     */
    @GetMapping("/phieu-suat-ans")
    public List<PhieuSuatAn> getAllPhieuSuatAns() {
        log.debug("REST request to get all PhieuSuatAns");
        return phieuSuatAnRepository.findAll();
    }

    /**
     * {@code GET  /phieu-suat-ans/:id} : get the "id" phieuSuatAn.
     *
     * @param id the id of the phieuSuatAn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phieuSuatAn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/phieu-suat-ans/{id}")
    public ResponseEntity<PhieuSuatAn> getPhieuSuatAn(@PathVariable Long id) {
        log.debug("REST request to get PhieuSuatAn : {}", id);
        Optional<PhieuSuatAn> phieuSuatAn = phieuSuatAnRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(phieuSuatAn);
    }

    /**
     * {@code DELETE  /phieu-suat-ans/:id} : delete the "id" phieuSuatAn.
     *
     * @param id the id of the phieuSuatAn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/phieu-suat-ans/{id}")
    public ResponseEntity<Void> deletePhieuSuatAn(@PathVariable Long id) {
        log.debug("REST request to delete PhieuSuatAn : {}", id);
        phieuSuatAnRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
