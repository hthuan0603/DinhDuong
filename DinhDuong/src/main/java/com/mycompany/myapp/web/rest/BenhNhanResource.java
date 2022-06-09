package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BenhNhan;
import com.mycompany.myapp.repository.BenhNhanRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BenhNhan}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BenhNhanResource {

    private final Logger log = LoggerFactory.getLogger(BenhNhanResource.class);

    private static final String ENTITY_NAME = "benhNhan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BenhNhanRepository benhNhanRepository;

    public BenhNhanResource(BenhNhanRepository benhNhanRepository) {
        this.benhNhanRepository = benhNhanRepository;
    }

    /**
     * {@code POST  /benh-nhans} : Create a new benhNhan.
     *
     * @param benhNhan the benhNhan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new benhNhan, or with status {@code 400 (Bad Request)} if the benhNhan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/benh-nhans")
    public ResponseEntity<BenhNhan> createBenhNhan(@RequestBody BenhNhan benhNhan) throws URISyntaxException {
        log.debug("REST request to save BenhNhan : {}", benhNhan);
        if (benhNhan.getId() != null) {
            throw new BadRequestAlertException("A new benhNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BenhNhan result = benhNhanRepository.save(benhNhan);
        return ResponseEntity
            .created(new URI("/api/benh-nhans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /benh-nhans/:id} : Updates an existing benhNhan.
     *
     * @param id the id of the benhNhan to save.
     * @param benhNhan the benhNhan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benhNhan,
     * or with status {@code 400 (Bad Request)} if the benhNhan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the benhNhan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/benh-nhans/{id}")
    public ResponseEntity<BenhNhan> updateBenhNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BenhNhan benhNhan
    ) throws URISyntaxException {
        log.debug("REST request to update BenhNhan : {}, {}", id, benhNhan);
        if (benhNhan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, benhNhan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benhNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BenhNhan result = benhNhanRepository.save(benhNhan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, benhNhan.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /benh-nhans/:id} : Partial updates given fields of an existing benhNhan, field will ignore if it is null
     *
     * @param id the id of the benhNhan to save.
     * @param benhNhan the benhNhan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benhNhan,
     * or with status {@code 400 (Bad Request)} if the benhNhan is not valid,
     * or with status {@code 404 (Not Found)} if the benhNhan is not found,
     * or with status {@code 500 (Internal Server Error)} if the benhNhan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/benh-nhans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BenhNhan> partialUpdateBenhNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BenhNhan benhNhan
    ) throws URISyntaxException {
        log.debug("REST request to partial update BenhNhan partially : {}, {}", id, benhNhan);
        if (benhNhan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, benhNhan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!benhNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BenhNhan> result = benhNhanRepository
            .findById(benhNhan.getId())
            .map(existingBenhNhan -> {
                if (benhNhan.getHoTen() != null) {
                    existingBenhNhan.setHoTen(benhNhan.getHoTen());
                }
                if (benhNhan.getMaBN() != null) {
                    existingBenhNhan.setMaBN(benhNhan.getMaBN());
                }
                if (benhNhan.getNgaySinh() != null) {
                    existingBenhNhan.setNgaySinh(benhNhan.getNgaySinh());
                }
                if (benhNhan.getGioiTinh() != null) {
                    existingBenhNhan.setGioiTinh(benhNhan.getGioiTinh());
                }
                if (benhNhan.getDiaChi() != null) {
                    existingBenhNhan.setDiaChi(benhNhan.getDiaChi());
                }
                if (benhNhan.getNgheNghiep() != null) {
                    existingBenhNhan.setNgheNghiep(benhNhan.getNgheNghiep());
                }
                if (benhNhan.getChieuCao() != null) {
                    existingBenhNhan.setChieuCao(benhNhan.getChieuCao());
                }
                if (benhNhan.getCanHT() != null) {
                    existingBenhNhan.setCanHT(benhNhan.getCanHT());
                }
                if (benhNhan.getCanTC() != null) {
                    existingBenhNhan.setCanTC(benhNhan.getCanTC());
                }
                if (benhNhan.getVongCT() != null) {
                    existingBenhNhan.setVongCT(benhNhan.getVongCT());
                }
                if (benhNhan.getbMI() != null) {
                    existingBenhNhan.setbMI(benhNhan.getbMI());
                }
                if (benhNhan.getMaTheBHYT() != null) {
                    existingBenhNhan.setMaTheBHYT(benhNhan.getMaTheBHYT());
                }
                if (benhNhan.getsDT() != null) {
                    existingBenhNhan.setsDT(benhNhan.getsDT());
                }

                return existingBenhNhan;
            })
            .map(benhNhanRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, benhNhan.getId().toString())
        );
    }

    /**
     * {@code GET  /benh-nhans} : get all the benhNhans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of benhNhans in body.
     */
    @GetMapping("/benh-nhans")
    public List<BenhNhan> getAllBenhNhans() {
        log.debug("REST request to get all BenhNhans");
        return benhNhanRepository.findAll();
    }

    /**
     * {@code GET  /benh-nhans/:id} : get the "id" benhNhan.
     *
     * @param id the id of the benhNhan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the benhNhan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/benh-nhans/{id}")
    public ResponseEntity<BenhNhan> getBenhNhan(@PathVariable Long id) {
        log.debug("REST request to get BenhNhan : {}", id);
        Optional<BenhNhan> benhNhan = benhNhanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(benhNhan);
    }

    /**
     * {@code DELETE  /benh-nhans/:id} : delete the "id" benhNhan.
     *
     * @param id the id of the benhNhan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/benh-nhans/{id}")
    public ResponseEntity<Void> deleteBenhNhan(@PathVariable Long id) {
        log.debug("REST request to delete BenhNhan : {}", id);
        benhNhanRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
