package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BaoHiem;
import com.mycompany.myapp.repository.BaoHiemRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BaoHiem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BaoHiemResource {

    private final Logger log = LoggerFactory.getLogger(BaoHiemResource.class);

    private static final String ENTITY_NAME = "baoHiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BaoHiemRepository baoHiemRepository;

    public BaoHiemResource(BaoHiemRepository baoHiemRepository) {
        this.baoHiemRepository = baoHiemRepository;
    }

    /**
     * {@code POST  /bao-hiems} : Create a new baoHiem.
     *
     * @param baoHiem the baoHiem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new baoHiem, or with status {@code 400 (Bad Request)} if the baoHiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bao-hiems")
    public ResponseEntity<BaoHiem> createBaoHiem(@RequestBody BaoHiem baoHiem) throws URISyntaxException {
        log.debug("REST request to save BaoHiem : {}", baoHiem);
        if (baoHiem.getId() != null) {
            throw new BadRequestAlertException("A new baoHiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaoHiem result = baoHiemRepository.save(baoHiem);
        return ResponseEntity
            .created(new URI("/api/bao-hiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bao-hiems/:id} : Updates an existing baoHiem.
     *
     * @param id the id of the baoHiem to save.
     * @param baoHiem the baoHiem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baoHiem,
     * or with status {@code 400 (Bad Request)} if the baoHiem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the baoHiem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bao-hiems/{id}")
    public ResponseEntity<BaoHiem> updateBaoHiem(@PathVariable(value = "id", required = false) final Long id, @RequestBody BaoHiem baoHiem)
        throws URISyntaxException {
        log.debug("REST request to update BaoHiem : {}, {}", id, baoHiem);
        if (baoHiem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, baoHiem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!baoHiemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BaoHiem result = baoHiemRepository.save(baoHiem);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baoHiem.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bao-hiems/:id} : Partial updates given fields of an existing baoHiem, field will ignore if it is null
     *
     * @param id the id of the baoHiem to save.
     * @param baoHiem the baoHiem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baoHiem,
     * or with status {@code 400 (Bad Request)} if the baoHiem is not valid,
     * or with status {@code 404 (Not Found)} if the baoHiem is not found,
     * or with status {@code 500 (Internal Server Error)} if the baoHiem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bao-hiems/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BaoHiem> partialUpdateBaoHiem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BaoHiem baoHiem
    ) throws URISyntaxException {
        log.debug("REST request to partial update BaoHiem partially : {}, {}", id, baoHiem);
        if (baoHiem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, baoHiem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!baoHiemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BaoHiem> result = baoHiemRepository
            .findById(baoHiem.getId())
            .map(existingBaoHiem -> {
                if (baoHiem.getMaTheBHYT() != null) {
                    existingBaoHiem.setMaTheBHYT(baoHiem.getMaTheBHYT());
                }
                if (baoHiem.getDoiTuong() != null) {
                    existingBaoHiem.setDoiTuong(baoHiem.getDoiTuong());
                }
                if (baoHiem.getBaoHiemThanhToan() != null) {
                    existingBaoHiem.setBaoHiemThanhToan(baoHiem.getBaoHiemThanhToan());
                }

                return existingBaoHiem;
            })
            .map(baoHiemRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baoHiem.getId().toString())
        );
    }

    /**
     * {@code GET  /bao-hiems} : get all the baoHiems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of baoHiems in body.
     */
    @GetMapping("/bao-hiems")
    public List<BaoHiem> getAllBaoHiems() {
        log.debug("REST request to get all BaoHiems");
        return baoHiemRepository.findAll();
    }

    /**
     * {@code GET  /bao-hiems/:id} : get the "id" baoHiem.
     *
     * @param id the id of the baoHiem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the baoHiem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bao-hiems/{id}")
    public ResponseEntity<BaoHiem> getBaoHiem(@PathVariable Long id) {
        log.debug("REST request to get BaoHiem : {}", id);
        Optional<BaoHiem> baoHiem = baoHiemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(baoHiem);
    }

    /**
     * {@code DELETE  /bao-hiems/:id} : delete the "id" baoHiem.
     *
     * @param id the id of the baoHiem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bao-hiems/{id}")
    public ResponseEntity<Void> deleteBaoHiem(@PathVariable Long id) {
        log.debug("REST request to delete BaoHiem : {}", id);
        baoHiemRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
