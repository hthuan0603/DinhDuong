package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TTSanglocVaDanhGiaDD;
import com.mycompany.myapp.repository.TTSanglocVaDanhGiaDDRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TTSanglocVaDanhGiaDD}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TTSanglocVaDanhGiaDDResource {

    private final Logger log = LoggerFactory.getLogger(TTSanglocVaDanhGiaDDResource.class);

    private static final String ENTITY_NAME = "tTSanglocVaDanhGiaDD";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TTSanglocVaDanhGiaDDRepository tTSanglocVaDanhGiaDDRepository;

    public TTSanglocVaDanhGiaDDResource(TTSanglocVaDanhGiaDDRepository tTSanglocVaDanhGiaDDRepository) {
        this.tTSanglocVaDanhGiaDDRepository = tTSanglocVaDanhGiaDDRepository;
    }

    /**
     * {@code POST  /tt-sangloc-va-danh-gia-dds} : Create a new tTSanglocVaDanhGiaDD.
     *
     * @param tTSanglocVaDanhGiaDD the tTSanglocVaDanhGiaDD to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tTSanglocVaDanhGiaDD, or with status {@code 400 (Bad Request)} if the tTSanglocVaDanhGiaDD has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tt-sangloc-va-danh-gia-dds")
    public ResponseEntity<TTSanglocVaDanhGiaDD> createTTSanglocVaDanhGiaDD(@RequestBody TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD)
        throws URISyntaxException {
        log.debug("REST request to save TTSanglocVaDanhGiaDD : {}", tTSanglocVaDanhGiaDD);
        if (tTSanglocVaDanhGiaDD.getId() != null) {
            throw new BadRequestAlertException("A new tTSanglocVaDanhGiaDD cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TTSanglocVaDanhGiaDD result = tTSanglocVaDanhGiaDDRepository.save(tTSanglocVaDanhGiaDD);
        return ResponseEntity
            .created(new URI("/api/tt-sangloc-va-danh-gia-dds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tt-sangloc-va-danh-gia-dds/:id} : Updates an existing tTSanglocVaDanhGiaDD.
     *
     * @param id the id of the tTSanglocVaDanhGiaDD to save.
     * @param tTSanglocVaDanhGiaDD the tTSanglocVaDanhGiaDD to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tTSanglocVaDanhGiaDD,
     * or with status {@code 400 (Bad Request)} if the tTSanglocVaDanhGiaDD is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tTSanglocVaDanhGiaDD couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tt-sangloc-va-danh-gia-dds/{id}")
    public ResponseEntity<TTSanglocVaDanhGiaDD> updateTTSanglocVaDanhGiaDD(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD
    ) throws URISyntaxException {
        log.debug("REST request to update TTSanglocVaDanhGiaDD : {}, {}", id, tTSanglocVaDanhGiaDD);
        if (tTSanglocVaDanhGiaDD.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tTSanglocVaDanhGiaDD.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tTSanglocVaDanhGiaDDRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TTSanglocVaDanhGiaDD result = tTSanglocVaDanhGiaDDRepository.save(tTSanglocVaDanhGiaDD);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tTSanglocVaDanhGiaDD.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tt-sangloc-va-danh-gia-dds/:id} : Partial updates given fields of an existing tTSanglocVaDanhGiaDD, field will ignore if it is null
     *
     * @param id the id of the tTSanglocVaDanhGiaDD to save.
     * @param tTSanglocVaDanhGiaDD the tTSanglocVaDanhGiaDD to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tTSanglocVaDanhGiaDD,
     * or with status {@code 400 (Bad Request)} if the tTSanglocVaDanhGiaDD is not valid,
     * or with status {@code 404 (Not Found)} if the tTSanglocVaDanhGiaDD is not found,
     * or with status {@code 500 (Internal Server Error)} if the tTSanglocVaDanhGiaDD couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tt-sangloc-va-danh-gia-dds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TTSanglocVaDanhGiaDD> partialUpdateTTSanglocVaDanhGiaDD(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD
    ) throws URISyntaxException {
        log.debug("REST request to partial update TTSanglocVaDanhGiaDD partially : {}, {}", id, tTSanglocVaDanhGiaDD);
        if (tTSanglocVaDanhGiaDD.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tTSanglocVaDanhGiaDD.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tTSanglocVaDanhGiaDDRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TTSanglocVaDanhGiaDD> result = tTSanglocVaDanhGiaDDRepository
            .findById(tTSanglocVaDanhGiaDD.getId())
            .map(existingTTSanglocVaDanhGiaDD -> {
                if (tTSanglocVaDanhGiaDD.getMaBN() != null) {
                    existingTTSanglocVaDanhGiaDD.setMaBN(tTSanglocVaDanhGiaDD.getMaBN());
                }
                if (tTSanglocVaDanhGiaDD.getMangThai() != null) {
                    existingTTSanglocVaDanhGiaDD.setMangThai(tTSanglocVaDanhGiaDD.getMangThai());
                }
                if (tTSanglocVaDanhGiaDD.getbMI() != null) {
                    existingTTSanglocVaDanhGiaDD.setbMI(tTSanglocVaDanhGiaDD.getbMI());
                }
                if (tTSanglocVaDanhGiaDD.getDanhGiaBMI() != null) {
                    existingTTSanglocVaDanhGiaDD.setDanhGiaBMI(tTSanglocVaDanhGiaDD.getDanhGiaBMI());
                }
                if (tTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang() != null) {
                    existingTTSanglocVaDanhGiaDD.setPhanTramCanNangTrong3Thang(tTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang());
                }
                if (tTSanglocVaDanhGiaDD.getDanhGiaCanNang() != null) {
                    existingTTSanglocVaDanhGiaDD.setDanhGiaCanNang(tTSanglocVaDanhGiaDD.getDanhGiaCanNang());
                }
                if (tTSanglocVaDanhGiaDD.getAnUongTrongTuan() != null) {
                    existingTTSanglocVaDanhGiaDD.setAnUongTrongTuan(tTSanglocVaDanhGiaDD.getAnUongTrongTuan());
                }
                if (tTSanglocVaDanhGiaDD.getDanhGiaAnUong() != null) {
                    existingTTSanglocVaDanhGiaDD.setDanhGiaAnUong(tTSanglocVaDanhGiaDD.getDanhGiaAnUong());
                }
                if (tTSanglocVaDanhGiaDD.getThoiGianChiDinh() != null) {
                    existingTTSanglocVaDanhGiaDD.setThoiGianChiDinh(tTSanglocVaDanhGiaDD.getThoiGianChiDinh());
                }
                if (tTSanglocVaDanhGiaDD.getNguyCoSDD() != null) {
                    existingTTSanglocVaDanhGiaDD.setNguyCoSDD(tTSanglocVaDanhGiaDD.getNguyCoSDD());
                }
                if (tTSanglocVaDanhGiaDD.getCheDoAn() != null) {
                    existingTTSanglocVaDanhGiaDD.setCheDoAn(tTSanglocVaDanhGiaDD.getCheDoAn());
                }

                return existingTTSanglocVaDanhGiaDD;
            })
            .map(tTSanglocVaDanhGiaDDRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tTSanglocVaDanhGiaDD.getId().toString())
        );
    }

    /**
     * {@code GET  /tt-sangloc-va-danh-gia-dds} : get all the tTSanglocVaDanhGiaDDS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tTSanglocVaDanhGiaDDS in body.
     */
    @GetMapping("/tt-sangloc-va-danh-gia-dds")
    public List<TTSanglocVaDanhGiaDD> getAllTTSanglocVaDanhGiaDDS() {
        log.debug("REST request to get all TTSanglocVaDanhGiaDDS");
        return tTSanglocVaDanhGiaDDRepository.findAll();
    }

    /**
     * {@code GET  /tt-sangloc-va-danh-gia-dds/:id} : get the "id" tTSanglocVaDanhGiaDD.
     *
     * @param id the id of the tTSanglocVaDanhGiaDD to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tTSanglocVaDanhGiaDD, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tt-sangloc-va-danh-gia-dds/{id}")
    public ResponseEntity<TTSanglocVaDanhGiaDD> getTTSanglocVaDanhGiaDD(@PathVariable Long id) {
        log.debug("REST request to get TTSanglocVaDanhGiaDD : {}", id);
        Optional<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tTSanglocVaDanhGiaDD);
    }

    /**
     * {@code DELETE  /tt-sangloc-va-danh-gia-dds/:id} : delete the "id" tTSanglocVaDanhGiaDD.
     *
     * @param id the id of the tTSanglocVaDanhGiaDD to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tt-sangloc-va-danh-gia-dds/{id}")
    public ResponseEntity<Void> deleteTTSanglocVaDanhGiaDD(@PathVariable Long id) {
        log.debug("REST request to delete TTSanglocVaDanhGiaDD : {}", id);
        tTSanglocVaDanhGiaDDRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
