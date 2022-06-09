package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.DanhGiaCanThiepDD;
import com.mycompany.myapp.repository.DanhGiaCanThiepDDRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.DanhGiaCanThiepDD}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DanhGiaCanThiepDDResource {

    private final Logger log = LoggerFactory.getLogger(DanhGiaCanThiepDDResource.class);

    private static final String ENTITY_NAME = "danhGiaCanThiepDD";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhGiaCanThiepDDRepository danhGiaCanThiepDDRepository;

    public DanhGiaCanThiepDDResource(DanhGiaCanThiepDDRepository danhGiaCanThiepDDRepository) {
        this.danhGiaCanThiepDDRepository = danhGiaCanThiepDDRepository;
    }

    /**
     * {@code POST  /danh-gia-can-thiep-dds} : Create a new danhGiaCanThiepDD.
     *
     * @param danhGiaCanThiepDD the danhGiaCanThiepDD to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhGiaCanThiepDD, or with status {@code 400 (Bad Request)} if the danhGiaCanThiepDD has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danh-gia-can-thiep-dds")
    public ResponseEntity<DanhGiaCanThiepDD> createDanhGiaCanThiepDD(@RequestBody DanhGiaCanThiepDD danhGiaCanThiepDD)
        throws URISyntaxException {
        log.debug("REST request to save DanhGiaCanThiepDD : {}", danhGiaCanThiepDD);
        if (danhGiaCanThiepDD.getId() != null) {
            throw new BadRequestAlertException("A new danhGiaCanThiepDD cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhGiaCanThiepDD result = danhGiaCanThiepDDRepository.save(danhGiaCanThiepDD);
        return ResponseEntity
            .created(new URI("/api/danh-gia-can-thiep-dds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danh-gia-can-thiep-dds/:id} : Updates an existing danhGiaCanThiepDD.
     *
     * @param id the id of the danhGiaCanThiepDD to save.
     * @param danhGiaCanThiepDD the danhGiaCanThiepDD to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhGiaCanThiepDD,
     * or with status {@code 400 (Bad Request)} if the danhGiaCanThiepDD is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhGiaCanThiepDD couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danh-gia-can-thiep-dds/{id}")
    public ResponseEntity<DanhGiaCanThiepDD> updateDanhGiaCanThiepDD(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhGiaCanThiepDD danhGiaCanThiepDD
    ) throws URISyntaxException {
        log.debug("REST request to update DanhGiaCanThiepDD : {}, {}", id, danhGiaCanThiepDD);
        if (danhGiaCanThiepDD.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhGiaCanThiepDD.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhGiaCanThiepDDRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DanhGiaCanThiepDD result = danhGiaCanThiepDDRepository.save(danhGiaCanThiepDD);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhGiaCanThiepDD.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /danh-gia-can-thiep-dds/:id} : Partial updates given fields of an existing danhGiaCanThiepDD, field will ignore if it is null
     *
     * @param id the id of the danhGiaCanThiepDD to save.
     * @param danhGiaCanThiepDD the danhGiaCanThiepDD to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhGiaCanThiepDD,
     * or with status {@code 400 (Bad Request)} if the danhGiaCanThiepDD is not valid,
     * or with status {@code 404 (Not Found)} if the danhGiaCanThiepDD is not found,
     * or with status {@code 500 (Internal Server Error)} if the danhGiaCanThiepDD couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/danh-gia-can-thiep-dds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DanhGiaCanThiepDD> partialUpdateDanhGiaCanThiepDD(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DanhGiaCanThiepDD danhGiaCanThiepDD
    ) throws URISyntaxException {
        log.debug("REST request to partial update DanhGiaCanThiepDD partially : {}, {}", id, danhGiaCanThiepDD);
        if (danhGiaCanThiepDD.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, danhGiaCanThiepDD.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!danhGiaCanThiepDDRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DanhGiaCanThiepDD> result = danhGiaCanThiepDDRepository
            .findById(danhGiaCanThiepDD.getId())
            .map(existingDanhGiaCanThiepDD -> {
                if (danhGiaCanThiepDD.getMaBN() != null) {
                    existingDanhGiaCanThiepDD.setMaBN(danhGiaCanThiepDD.getMaBN());
                }
                if (danhGiaCanThiepDD.getChuanDoanLS() != null) {
                    existingDanhGiaCanThiepDD.setChuanDoanLS(danhGiaCanThiepDD.getChuanDoanLS());
                }
                if (danhGiaCanThiepDD.getbSDieuTri() != null) {
                    existingDanhGiaCanThiepDD.setbSDieuTri(danhGiaCanThiepDD.getbSDieuTri());
                }
                if (danhGiaCanThiepDD.getThayDoiCanNangTrong1Thang() != null) {
                    existingDanhGiaCanThiepDD.setThayDoiCanNangTrong1Thang(danhGiaCanThiepDD.getThayDoiCanNangTrong1Thang());
                }
                if (danhGiaCanThiepDD.getDanhGiaCN() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaCN(danhGiaCanThiepDD.getDanhGiaCN());
                }
                if (danhGiaCanThiepDD.getKhauPhanAn() != null) {
                    existingDanhGiaCanThiepDD.setKhauPhanAn(danhGiaCanThiepDD.getKhauPhanAn());
                }
                if (danhGiaCanThiepDD.getDanhGiaKPA() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaKPA(danhGiaCanThiepDD.getDanhGiaKPA());
                }
                if (danhGiaCanThiepDD.getTrieuChungTieuHoa() != null) {
                    existingDanhGiaCanThiepDD.setTrieuChungTieuHoa(danhGiaCanThiepDD.getTrieuChungTieuHoa());
                }
                if (danhGiaCanThiepDD.getMucDo() != null) {
                    existingDanhGiaCanThiepDD.setMucDo(danhGiaCanThiepDD.getMucDo());
                }
                if (danhGiaCanThiepDD.getDanhGiaMD() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaMD(danhGiaCanThiepDD.getDanhGiaMD());
                }
                if (danhGiaCanThiepDD.getGiamChucNangHoatDong() != null) {
                    existingDanhGiaCanThiepDD.setGiamChucNangHoatDong(danhGiaCanThiepDD.getGiamChucNangHoatDong());
                }
                if (danhGiaCanThiepDD.getDanhGiaCNHD() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaCNHD(danhGiaCanThiepDD.getDanhGiaCNHD());
                }
                if (danhGiaCanThiepDD.getStress() != null) {
                    existingDanhGiaCanThiepDD.setStress(danhGiaCanThiepDD.getStress());
                }
                if (danhGiaCanThiepDD.getDanhGiaStress() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaStress(danhGiaCanThiepDD.getDanhGiaStress());
                }
                if (danhGiaCanThiepDD.getRefeeding() != null) {
                    existingDanhGiaCanThiepDD.setRefeeding(danhGiaCanThiepDD.getRefeeding());
                }
                if (danhGiaCanThiepDD.getDanhGiaRefeeding() != null) {
                    existingDanhGiaCanThiepDD.setDanhGiaRefeeding(danhGiaCanThiepDD.getDanhGiaRefeeding());
                }
                if (danhGiaCanThiepDD.getTongDiem() != null) {
                    existingDanhGiaCanThiepDD.setTongDiem(danhGiaCanThiepDD.getTongDiem());
                }

                return existingDanhGiaCanThiepDD;
            })
            .map(danhGiaCanThiepDDRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, danhGiaCanThiepDD.getId().toString())
        );
    }

    /**
     * {@code GET  /danh-gia-can-thiep-dds} : get all the danhGiaCanThiepDDS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhGiaCanThiepDDS in body.
     */
    @GetMapping("/danh-gia-can-thiep-dds")
    public List<DanhGiaCanThiepDD> getAllDanhGiaCanThiepDDS() {
        log.debug("REST request to get all DanhGiaCanThiepDDS");
        return danhGiaCanThiepDDRepository.findAll();
    }

    /**
     * {@code GET  /danh-gia-can-thiep-dds/:id} : get the "id" danhGiaCanThiepDD.
     *
     * @param id the id of the danhGiaCanThiepDD to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhGiaCanThiepDD, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danh-gia-can-thiep-dds/{id}")
    public ResponseEntity<DanhGiaCanThiepDD> getDanhGiaCanThiepDD(@PathVariable Long id) {
        log.debug("REST request to get DanhGiaCanThiepDD : {}", id);
        Optional<DanhGiaCanThiepDD> danhGiaCanThiepDD = danhGiaCanThiepDDRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(danhGiaCanThiepDD);
    }

    /**
     * {@code DELETE  /danh-gia-can-thiep-dds/:id} : delete the "id" danhGiaCanThiepDD.
     *
     * @param id the id of the danhGiaCanThiepDD to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danh-gia-can-thiep-dds/{id}")
    public ResponseEntity<Void> deleteDanhGiaCanThiepDD(@PathVariable Long id) {
        log.debug("REST request to delete DanhGiaCanThiepDD : {}", id);
        danhGiaCanThiepDDRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
