package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.NhanVienTiepNhanRepository;
import com.mycompany.myapp.service.NhanVienTiepNhanService;
import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.NhanVienTiepNhan}.
 */
@RestController
@RequestMapping("/api")
public class NhanVienTiepNhanResource {

    private final Logger log = LoggerFactory.getLogger(NhanVienTiepNhanResource.class);

    private static final String ENTITY_NAME = "nhanVienTiepNhan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhanVienTiepNhanService nhanVienTiepNhanService;

    private final NhanVienTiepNhanRepository nhanVienTiepNhanRepository;

    public NhanVienTiepNhanResource(
        NhanVienTiepNhanService nhanVienTiepNhanService,
        NhanVienTiepNhanRepository nhanVienTiepNhanRepository
    ) {
        this.nhanVienTiepNhanService = nhanVienTiepNhanService;
        this.nhanVienTiepNhanRepository = nhanVienTiepNhanRepository;
    }

    /**
     * {@code POST  /nhan-vien-tiep-nhans} : Create a new nhanVienTiepNhan.
     *
     * @param nhanVienTiepNhanDTO the nhanVienTiepNhanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhanVienTiepNhanDTO, or with status {@code 400 (Bad Request)} if the nhanVienTiepNhan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhan-vien-tiep-nhans")
    public ResponseEntity<NhanVienTiepNhanDTO> createNhanVienTiepNhan(@RequestBody NhanVienTiepNhanDTO nhanVienTiepNhanDTO)
        throws URISyntaxException {
        log.debug("REST request to save NhanVienTiepNhan : {}", nhanVienTiepNhanDTO);
        if (nhanVienTiepNhanDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhanVienTiepNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanVienTiepNhanDTO result = nhanVienTiepNhanService.save(nhanVienTiepNhanDTO);
        return ResponseEntity
            .created(new URI("/api/nhan-vien-tiep-nhans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhan-vien-tiep-nhans/:id} : Updates an existing nhanVienTiepNhan.
     *
     * @param id the id of the nhanVienTiepNhanDTO to save.
     * @param nhanVienTiepNhanDTO the nhanVienTiepNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanVienTiepNhanDTO,
     * or with status {@code 400 (Bad Request)} if the nhanVienTiepNhanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhanVienTiepNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhan-vien-tiep-nhans/{id}")
    public ResponseEntity<NhanVienTiepNhanDTO> updateNhanVienTiepNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NhanVienTiepNhanDTO nhanVienTiepNhanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NhanVienTiepNhan : {}, {}", id, nhanVienTiepNhanDTO);
        if (nhanVienTiepNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhanVienTiepNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhanVienTiepNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NhanVienTiepNhanDTO result = nhanVienTiepNhanService.update(nhanVienTiepNhanDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhanVienTiepNhanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /nhan-vien-tiep-nhans/:id} : Partial updates given fields of an existing nhanVienTiepNhan, field will ignore if it is null
     *
     * @param id the id of the nhanVienTiepNhanDTO to save.
     * @param nhanVienTiepNhanDTO the nhanVienTiepNhanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanVienTiepNhanDTO,
     * or with status {@code 400 (Bad Request)} if the nhanVienTiepNhanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nhanVienTiepNhanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nhanVienTiepNhanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/nhan-vien-tiep-nhans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NhanVienTiepNhanDTO> partialUpdateNhanVienTiepNhan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NhanVienTiepNhanDTO nhanVienTiepNhanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NhanVienTiepNhan partially : {}, {}", id, nhanVienTiepNhanDTO);
        if (nhanVienTiepNhanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nhanVienTiepNhanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nhanVienTiepNhanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NhanVienTiepNhanDTO> result = nhanVienTiepNhanService.partialUpdate(nhanVienTiepNhanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nhanVienTiepNhanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nhan-vien-tiep-nhans} : get all the nhanVienTiepNhans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhanVienTiepNhans in body.
     */
    @GetMapping("/nhan-vien-tiep-nhans")
    public ResponseEntity<List<NhanVienTiepNhanDTO>> getAllNhanVienTiepNhans(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of NhanVienTiepNhans");
        Page<NhanVienTiepNhanDTO> page = nhanVienTiepNhanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nhan-vien-tiep-nhans/:id} : get the "id" nhanVienTiepNhan.
     *
     * @param id the id of the nhanVienTiepNhanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhanVienTiepNhanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhan-vien-tiep-nhans/{id}")
    public ResponseEntity<NhanVienTiepNhanDTO> getNhanVienTiepNhan(@PathVariable Long id) {
        log.debug("REST request to get NhanVienTiepNhan : {}", id);
        Optional<NhanVienTiepNhanDTO> nhanVienTiepNhanDTO = nhanVienTiepNhanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhanVienTiepNhanDTO);
    }

    /**
     * {@code DELETE  /nhan-vien-tiep-nhans/:id} : delete the "id" nhanVienTiepNhan.
     *
     * @param id the id of the nhanVienTiepNhanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhan-vien-tiep-nhans/{id}")
    public ResponseEntity<Void> deleteNhanVienTiepNhan(@PathVariable Long id) {
        log.debug("REST request to delete NhanVienTiepNhan : {}", id);
        nhanVienTiepNhanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
