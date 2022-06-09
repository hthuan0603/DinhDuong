package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.GiayMoiDanhGia;
import com.mycompany.myapp.repository.GiayMoiDanhGiaRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.GiayMoiDanhGia}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GiayMoiDanhGiaResource {

    private final Logger log = LoggerFactory.getLogger(GiayMoiDanhGiaResource.class);

    private static final String ENTITY_NAME = "giayMoiDanhGia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiayMoiDanhGiaRepository giayMoiDanhGiaRepository;

    public GiayMoiDanhGiaResource(GiayMoiDanhGiaRepository giayMoiDanhGiaRepository) {
        this.giayMoiDanhGiaRepository = giayMoiDanhGiaRepository;
    }

    /**
     * {@code POST  /giay-moi-danh-gias} : Create a new giayMoiDanhGia.
     *
     * @param giayMoiDanhGia the giayMoiDanhGia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giayMoiDanhGia, or with status {@code 400 (Bad Request)} if the giayMoiDanhGia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/giay-moi-danh-gias")
    public ResponseEntity<GiayMoiDanhGia> createGiayMoiDanhGia(@RequestBody GiayMoiDanhGia giayMoiDanhGia) throws URISyntaxException {
        log.debug("REST request to save GiayMoiDanhGia : {}", giayMoiDanhGia);
        if (giayMoiDanhGia.getId() != null) {
            throw new BadRequestAlertException("A new giayMoiDanhGia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiayMoiDanhGia result = giayMoiDanhGiaRepository.save(giayMoiDanhGia);
        return ResponseEntity
            .created(new URI("/api/giay-moi-danh-gias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /giay-moi-danh-gias/:id} : Updates an existing giayMoiDanhGia.
     *
     * @param id the id of the giayMoiDanhGia to save.
     * @param giayMoiDanhGia the giayMoiDanhGia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giayMoiDanhGia,
     * or with status {@code 400 (Bad Request)} if the giayMoiDanhGia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giayMoiDanhGia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/giay-moi-danh-gias/{id}")
    public ResponseEntity<GiayMoiDanhGia> updateGiayMoiDanhGia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GiayMoiDanhGia giayMoiDanhGia
    ) throws URISyntaxException {
        log.debug("REST request to update GiayMoiDanhGia : {}, {}", id, giayMoiDanhGia);
        if (giayMoiDanhGia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giayMoiDanhGia.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giayMoiDanhGiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GiayMoiDanhGia result = giayMoiDanhGiaRepository.save(giayMoiDanhGia);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giayMoiDanhGia.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /giay-moi-danh-gias/:id} : Partial updates given fields of an existing giayMoiDanhGia, field will ignore if it is null
     *
     * @param id the id of the giayMoiDanhGia to save.
     * @param giayMoiDanhGia the giayMoiDanhGia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giayMoiDanhGia,
     * or with status {@code 400 (Bad Request)} if the giayMoiDanhGia is not valid,
     * or with status {@code 404 (Not Found)} if the giayMoiDanhGia is not found,
     * or with status {@code 500 (Internal Server Error)} if the giayMoiDanhGia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/giay-moi-danh-gias/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GiayMoiDanhGia> partialUpdateGiayMoiDanhGia(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GiayMoiDanhGia giayMoiDanhGia
    ) throws URISyntaxException {
        log.debug("REST request to partial update GiayMoiDanhGia partially : {}, {}", id, giayMoiDanhGia);
        if (giayMoiDanhGia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giayMoiDanhGia.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giayMoiDanhGiaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GiayMoiDanhGia> result = giayMoiDanhGiaRepository
            .findById(giayMoiDanhGia.getId())
            .map(existingGiayMoiDanhGia -> {
                if (giayMoiDanhGia.getMaBN() != null) {
                    existingGiayMoiDanhGia.setMaBN(giayMoiDanhGia.getMaBN());
                }
                if (giayMoiDanhGia.getMaNguoiTao() != null) {
                    existingGiayMoiDanhGia.setMaNguoiTao(giayMoiDanhGia.getMaNguoiTao());
                }
                if (giayMoiDanhGia.getThoiGianChiDinh() != null) {
                    existingGiayMoiDanhGia.setThoiGianChiDinh(giayMoiDanhGia.getThoiGianChiDinh());
                }
                if (giayMoiDanhGia.getChuanDoan() != null) {
                    existingGiayMoiDanhGia.setChuanDoan(giayMoiDanhGia.getChuanDoan());
                }
                if (giayMoiDanhGia.getChuanDoanPhu() != null) {
                    existingGiayMoiDanhGia.setChuanDoanPhu(giayMoiDanhGia.getChuanDoanPhu());
                }
                if (giayMoiDanhGia.getGuiTu() != null) {
                    existingGiayMoiDanhGia.setGuiTu(giayMoiDanhGia.getGuiTu());
                }
                if (giayMoiDanhGia.getGuiDen() != null) {
                    existingGiayMoiDanhGia.setGuiDen(giayMoiDanhGia.getGuiDen());
                }
                if (giayMoiDanhGia.getMoi() != null) {
                    existingGiayMoiDanhGia.setMoi(giayMoiDanhGia.getMoi());
                }
                if (giayMoiDanhGia.getNoiDungHoiChuan() != null) {
                    existingGiayMoiDanhGia.setNoiDungHoiChuan(giayMoiDanhGia.getNoiDungHoiChuan());
                }
                if (giayMoiDanhGia.getHoiChuanLan() != null) {
                    existingGiayMoiDanhGia.setHoiChuanLan(giayMoiDanhGia.getHoiChuanLan());
                }
                if (giayMoiDanhGia.getThoiGianHoiChuan() != null) {
                    existingGiayMoiDanhGia.setThoiGianHoiChuan(giayMoiDanhGia.getThoiGianHoiChuan());
                }
                if (giayMoiDanhGia.getTrangThai() != null) {
                    existingGiayMoiDanhGia.setTrangThai(giayMoiDanhGia.getTrangThai());
                }

                return existingGiayMoiDanhGia;
            })
            .map(giayMoiDanhGiaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giayMoiDanhGia.getId().toString())
        );
    }

    /**
     * {@code GET  /giay-moi-danh-gias} : get all the giayMoiDanhGias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giayMoiDanhGias in body.
     */
    @GetMapping("/giay-moi-danh-gias")
    public List<GiayMoiDanhGia> getAllGiayMoiDanhGias() {
        log.debug("REST request to get all GiayMoiDanhGias");
        return giayMoiDanhGiaRepository.findAll();
    }

    /**
     * {@code GET  /giay-moi-danh-gias/:id} : get the "id" giayMoiDanhGia.
     *
     * @param id the id of the giayMoiDanhGia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giayMoiDanhGia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/giay-moi-danh-gias/{id}")
    public ResponseEntity<GiayMoiDanhGia> getGiayMoiDanhGia(@PathVariable Long id) {
        log.debug("REST request to get GiayMoiDanhGia : {}", id);
        Optional<GiayMoiDanhGia> giayMoiDanhGia = giayMoiDanhGiaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(giayMoiDanhGia);
    }

    /**
     * {@code DELETE  /giay-moi-danh-gias/:id} : delete the "id" giayMoiDanhGia.
     *
     * @param id the id of the giayMoiDanhGia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/giay-moi-danh-gias/{id}")
    public ResponseEntity<Void> deleteGiayMoiDanhGia(@PathVariable Long id) {
        log.debug("REST request to delete GiayMoiDanhGia : {}", id);
        giayMoiDanhGiaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
