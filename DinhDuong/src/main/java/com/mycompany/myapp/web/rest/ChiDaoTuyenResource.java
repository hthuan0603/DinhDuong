package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.ChiDaoTuyenService;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ChiDaoTuyen}.
 */
@RestController
@RequestMapping("/api")
public class ChiDaoTuyenResource {

    private final Logger log = LoggerFactory.getLogger(ChiDaoTuyenResource.class);

    private static final String ENTITY_NAME = "chiDaoTuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiDaoTuyenService chiDaoTuyenService;

    private final ChiDaoTuyenRepository chiDaoTuyenRepository;

    public ChiDaoTuyenResource(ChiDaoTuyenService chiDaoTuyenService, ChiDaoTuyenRepository chiDaoTuyenRepository) {
        this.chiDaoTuyenService = chiDaoTuyenService;
        this.chiDaoTuyenRepository = chiDaoTuyenRepository;
    }

    /**
     * {@code POST  /chi-dao-tuyens} : Create a new chiDaoTuyen.
     *
     * @param chiDaoTuyenDTO the chiDaoTuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiDaoTuyenDTO, or with status {@code 400 (Bad Request)} if the chiDaoTuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chi-dao-tuyens")
    public ResponseEntity<ChiDaoTuyenDTO> createChiDaoTuyen(@RequestBody ChiDaoTuyenDTO chiDaoTuyenDTO) throws URISyntaxException {
        log.debug("REST request to save ChiDaoTuyen : {}", chiDaoTuyenDTO);
        if (chiDaoTuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiDaoTuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChiDaoTuyenDTO result = chiDaoTuyenService.save(chiDaoTuyenDTO);
        return ResponseEntity
            .created(new URI("/api/chi-dao-tuyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chi-dao-tuyens/:id} : Updates an existing chiDaoTuyen.
     *
     * @param id the id of the chiDaoTuyenDTO to save.
     * @param chiDaoTuyenDTO the chiDaoTuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiDaoTuyenDTO,
     * or with status {@code 400 (Bad Request)} if the chiDaoTuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiDaoTuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chi-dao-tuyens/{id}")
    public ResponseEntity<ChiDaoTuyenDTO> updateChiDaoTuyen(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiDaoTuyenDTO chiDaoTuyenDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ChiDaoTuyen : {}, {}", id, chiDaoTuyenDTO);
        if (chiDaoTuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiDaoTuyenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiDaoTuyenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ChiDaoTuyenDTO result = chiDaoTuyenService.update(chiDaoTuyenDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiDaoTuyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /chi-dao-tuyens/:id} : Partial updates given fields of an existing chiDaoTuyen, field will ignore if it is null
     *
     * @param id the id of the chiDaoTuyenDTO to save.
     * @param chiDaoTuyenDTO the chiDaoTuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiDaoTuyenDTO,
     * or with status {@code 400 (Bad Request)} if the chiDaoTuyenDTO is not valid,
     * or with status {@code 404 (Not Found)} if the chiDaoTuyenDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the chiDaoTuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/chi-dao-tuyens/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ChiDaoTuyenDTO> partialUpdateChiDaoTuyen(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChiDaoTuyenDTO chiDaoTuyenDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChiDaoTuyen partially : {}, {}", id, chiDaoTuyenDTO);
        if (chiDaoTuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chiDaoTuyenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chiDaoTuyenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChiDaoTuyenDTO> result = chiDaoTuyenService.partialUpdate(chiDaoTuyenDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chiDaoTuyenDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /chi-dao-tuyens} : get all the chiDaoTuyens.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiDaoTuyens in body.
     */
    @GetMapping("/chi-dao-tuyens")
    public ResponseEntity<List<ChiDaoTuyenDTO>> getAllChiDaoTuyens(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ChiDaoTuyens");
        Page<ChiDaoTuyenDTO> page = chiDaoTuyenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chi-dao-tuyens/:id} : get the "id" chiDaoTuyen.
     *
     * @param id the id of the chiDaoTuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiDaoTuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chi-dao-tuyens/{id}")
    public ResponseEntity<ChiDaoTuyenDTO> getChiDaoTuyen(@PathVariable Long id) {
        log.debug("REST request to get ChiDaoTuyen : {}", id);
        Optional<ChiDaoTuyenDTO> chiDaoTuyenDTO = chiDaoTuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiDaoTuyenDTO);
    }

    /**
     * {@code DELETE  /chi-dao-tuyens/:id} : delete the "id" chiDaoTuyen.
     *
     * @param id the id of the chiDaoTuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chi-dao-tuyens/{id}")
    public ResponseEntity<Void> deleteChiDaoTuyen(@PathVariable Long id) {
        log.debug("REST request to delete ChiDaoTuyen : {}", id);
        chiDaoTuyenService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
