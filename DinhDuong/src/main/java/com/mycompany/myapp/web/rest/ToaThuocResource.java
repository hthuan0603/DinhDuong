package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ToaThuoc;
import com.mycompany.myapp.repository.ToaThuocRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ToaThuoc}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ToaThuocResource {

    private final Logger log = LoggerFactory.getLogger(ToaThuocResource.class);

    private static final String ENTITY_NAME = "toaThuoc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToaThuocRepository toaThuocRepository;

    public ToaThuocResource(ToaThuocRepository toaThuocRepository) {
        this.toaThuocRepository = toaThuocRepository;
    }

    /**
     * {@code POST  /toa-thuocs} : Create a new toaThuoc.
     *
     * @param toaThuoc the toaThuoc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toaThuoc, or with status {@code 400 (Bad Request)} if the toaThuoc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/toa-thuocs")
    public ResponseEntity<ToaThuoc> createToaThuoc(@RequestBody ToaThuoc toaThuoc) throws URISyntaxException {
        log.debug("REST request to save ToaThuoc : {}", toaThuoc);
        if (toaThuoc.getId() != null) {
            throw new BadRequestAlertException("A new toaThuoc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToaThuoc result = toaThuocRepository.save(toaThuoc);
        return ResponseEntity
            .created(new URI("/api/toa-thuocs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /toa-thuocs/:id} : Updates an existing toaThuoc.
     *
     * @param id the id of the toaThuoc to save.
     * @param toaThuoc the toaThuoc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toaThuoc,
     * or with status {@code 400 (Bad Request)} if the toaThuoc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toaThuoc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/toa-thuocs/{id}")
    public ResponseEntity<ToaThuoc> updateToaThuoc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ToaThuoc toaThuoc
    ) throws URISyntaxException {
        log.debug("REST request to update ToaThuoc : {}, {}", id, toaThuoc);
        if (toaThuoc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toaThuoc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toaThuocRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ToaThuoc result = toaThuocRepository.save(toaThuoc);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toaThuoc.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /toa-thuocs/:id} : Partial updates given fields of an existing toaThuoc, field will ignore if it is null
     *
     * @param id the id of the toaThuoc to save.
     * @param toaThuoc the toaThuoc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toaThuoc,
     * or with status {@code 400 (Bad Request)} if the toaThuoc is not valid,
     * or with status {@code 404 (Not Found)} if the toaThuoc is not found,
     * or with status {@code 500 (Internal Server Error)} if the toaThuoc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/toa-thuocs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ToaThuoc> partialUpdateToaThuoc(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ToaThuoc toaThuoc
    ) throws URISyntaxException {
        log.debug("REST request to partial update ToaThuoc partially : {}, {}", id, toaThuoc);
        if (toaThuoc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toaThuoc.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toaThuocRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ToaThuoc> result = toaThuocRepository
            .findById(toaThuoc.getId())
            .map(existingToaThuoc -> {
                if (toaThuoc.getiCD10() != null) {
                    existingToaThuoc.setiCD10(toaThuoc.getiCD10());
                }
                if (toaThuoc.getMaBA() != null) {
                    existingToaThuoc.setMaBA(toaThuoc.getMaBA());
                }
                if (toaThuoc.getMaBN() != null) {
                    existingToaThuoc.setMaBN(toaThuoc.getMaBN());
                }
                if (toaThuoc.getLoaiThuoc() != null) {
                    existingToaThuoc.setLoaiThuoc(toaThuoc.getLoaiThuoc());
                }
                if (toaThuoc.getDoiTuong() != null) {
                    existingToaThuoc.setDoiTuong(toaThuoc.getDoiTuong());
                }
                if (toaThuoc.getTiLe() != null) {
                    existingToaThuoc.setTiLe(toaThuoc.getTiLe());
                }
                if (toaThuoc.getSoNgayHenTaiKham() != null) {
                    existingToaThuoc.setSoNgayHenTaiKham(toaThuoc.getSoNgayHenTaiKham());
                }
                if (toaThuoc.getCapPhieuHenKham() != null) {
                    existingToaThuoc.setCapPhieuHenKham(toaThuoc.getCapPhieuHenKham());
                }
                if (toaThuoc.getLoiDanBacSi() != null) {
                    existingToaThuoc.setLoiDanBacSi(toaThuoc.getLoiDanBacSi());
                }
                if (toaThuoc.getNgayChiDinh() != null) {
                    existingToaThuoc.setNgayChiDinh(toaThuoc.getNgayChiDinh());
                }
                if (toaThuoc.getNgayDung() != null) {
                    existingToaThuoc.setNgayDung(toaThuoc.getNgayDung());
                }
                if (toaThuoc.getSoNgaykeDon() != null) {
                    existingToaThuoc.setSoNgaykeDon(toaThuoc.getSoNgaykeDon());
                }
                if (toaThuoc.getKhoThuoc() != null) {
                    existingToaThuoc.setKhoThuoc(toaThuoc.getKhoThuoc());
                }

                return existingToaThuoc;
            })
            .map(toaThuocRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toaThuoc.getId().toString())
        );
    }

    /**
     * {@code GET  /toa-thuocs} : get all the toaThuocs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toaThuocs in body.
     */
    @GetMapping("/toa-thuocs")
    public List<ToaThuoc> getAllToaThuocs() {
        log.debug("REST request to get all ToaThuocs");
        return toaThuocRepository.findAll();
    }

    /**
     * {@code GET  /toa-thuocs/:id} : get the "id" toaThuoc.
     *
     * @param id the id of the toaThuoc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toaThuoc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/toa-thuocs/{id}")
    public ResponseEntity<ToaThuoc> getToaThuoc(@PathVariable Long id) {
        log.debug("REST request to get ToaThuoc : {}", id);
        Optional<ToaThuoc> toaThuoc = toaThuocRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(toaThuoc);
    }

    /**
     * {@code DELETE  /toa-thuocs/:id} : delete the "id" toaThuoc.
     *
     * @param id the id of the toaThuoc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/toa-thuocs/{id}")
    public ResponseEntity<Void> deleteToaThuoc(@PathVariable Long id) {
        log.debug("REST request to delete ToaThuoc : {}", id);
        toaThuocRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
