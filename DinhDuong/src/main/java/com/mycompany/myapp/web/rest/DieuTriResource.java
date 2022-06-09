package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.DieuTri;
import com.mycompany.myapp.repository.DieuTriRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.DieuTri}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DieuTriResource {

    private final Logger log = LoggerFactory.getLogger(DieuTriResource.class);

    private static final String ENTITY_NAME = "dieuTri";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DieuTriRepository dieuTriRepository;

    public DieuTriResource(DieuTriRepository dieuTriRepository) {
        this.dieuTriRepository = dieuTriRepository;
    }

    /**
     * {@code POST  /dieu-tris} : Create a new dieuTri.
     *
     * @param dieuTri the dieuTri to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dieuTri, or with status {@code 400 (Bad Request)} if the dieuTri has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dieu-tris")
    public ResponseEntity<DieuTri> createDieuTri(@RequestBody DieuTri dieuTri) throws URISyntaxException {
        log.debug("REST request to save DieuTri : {}", dieuTri);
        if (dieuTri.getId() != null) {
            throw new BadRequestAlertException("A new dieuTri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DieuTri result = dieuTriRepository.save(dieuTri);
        return ResponseEntity
            .created(new URI("/api/dieu-tris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dieu-tris/:id} : Updates an existing dieuTri.
     *
     * @param id the id of the dieuTri to save.
     * @param dieuTri the dieuTri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dieuTri,
     * or with status {@code 400 (Bad Request)} if the dieuTri is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dieuTri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dieu-tris/{id}")
    public ResponseEntity<DieuTri> updateDieuTri(@PathVariable(value = "id", required = false) final Long id, @RequestBody DieuTri dieuTri)
        throws URISyntaxException {
        log.debug("REST request to update DieuTri : {}, {}", id, dieuTri);
        if (dieuTri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dieuTri.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dieuTriRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DieuTri result = dieuTriRepository.save(dieuTri);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dieuTri.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /dieu-tris/:id} : Partial updates given fields of an existing dieuTri, field will ignore if it is null
     *
     * @param id the id of the dieuTri to save.
     * @param dieuTri the dieuTri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dieuTri,
     * or with status {@code 400 (Bad Request)} if the dieuTri is not valid,
     * or with status {@code 404 (Not Found)} if the dieuTri is not found,
     * or with status {@code 500 (Internal Server Error)} if the dieuTri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/dieu-tris/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DieuTri> partialUpdateDieuTri(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DieuTri dieuTri
    ) throws URISyntaxException {
        log.debug("REST request to partial update DieuTri partially : {}, {}", id, dieuTri);
        if (dieuTri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dieuTri.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dieuTriRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DieuTri> result = dieuTriRepository
            .findById(dieuTri.getId())
            .map(existingDieuTri -> {
                if (dieuTri.getMaDieuTri() != null) {
                    existingDieuTri.setMaDieuTri(dieuTri.getMaDieuTri());
                }
                if (dieuTri.getHoTen() != null) {
                    existingDieuTri.setHoTen(dieuTri.getHoTen());
                }
                if (dieuTri.getMaBenhAn() != null) {
                    existingDieuTri.setMaBenhAn(dieuTri.getMaBenhAn());
                }
                if (dieuTri.getTenBSDieuTri() != null) {
                    existingDieuTri.setTenBSDieuTri(dieuTri.getTenBSDieuTri());
                }
                if (dieuTri.getNguoiNha() != null) {
                    existingDieuTri.setNguoiNha(dieuTri.getNguoiNha());
                }
                if (dieuTri.getGiuong() != null) {
                    existingDieuTri.setGiuong(dieuTri.getGiuong());
                }
                if (dieuTri.getMaTheBHYT() != null) {
                    existingDieuTri.setMaTheBHYT(dieuTri.getMaTheBHYT());
                }
                if (dieuTri.getNgayVaoKhoa() != null) {
                    existingDieuTri.setNgayVaoKhoa(dieuTri.getNgayVaoKhoa());
                }
                if (dieuTri.getNgayRaVien() != null) {
                    existingDieuTri.setNgayRaVien(dieuTri.getNgayRaVien());
                }
                if (dieuTri.getbVChuyen() != null) {
                    existingDieuTri.setbVChuyen(dieuTri.getbVChuyen());
                }
                if (dieuTri.getKetQuaDieuTri() != null) {
                    existingDieuTri.setKetQuaDieuTri(dieuTri.getKetQuaDieuTri());
                }
                if (dieuTri.getLichSuChuyenKhoa() != null) {
                    existingDieuTri.setLichSuChuyenKhoa(dieuTri.getLichSuChuyenKhoa());
                }

                return existingDieuTri;
            })
            .map(dieuTriRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dieuTri.getId().toString())
        );
    }

    /**
     * {@code GET  /dieu-tris} : get all the dieuTris.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dieuTris in body.
     */
    @GetMapping("/dieu-tris")
    public List<DieuTri> getAllDieuTris() {
        log.debug("REST request to get all DieuTris");
        return dieuTriRepository.findAll();
    }

    /**
     * {@code GET  /dieu-tris/:id} : get the "id" dieuTri.
     *
     * @param id the id of the dieuTri to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dieuTri, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dieu-tris/{id}")
    public ResponseEntity<DieuTri> getDieuTri(@PathVariable Long id) {
        log.debug("REST request to get DieuTri : {}", id);
        Optional<DieuTri> dieuTri = dieuTriRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dieuTri);
    }

    /**
     * {@code DELETE  /dieu-tris/:id} : delete the "id" dieuTri.
     *
     * @param id the id of the dieuTri to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dieu-tris/{id}")
    public ResponseEntity<Void> deleteDieuTri(@PathVariable Long id) {
        log.debug("REST request to delete DieuTri : {}", id);
        dieuTriRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
