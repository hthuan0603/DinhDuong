package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.LyDoCongTacService;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LyDoCongTac}.
 */
@RestController
@RequestMapping("/api")
public class LyDoCongTacResource {

    private final Logger log = LoggerFactory.getLogger(LyDoCongTacResource.class);

    private static final String ENTITY_NAME = "lyDoCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LyDoCongTacService lyDoCongTacService;

    private final LyDoCongTacRepository lyDoCongTacRepository;

    public LyDoCongTacResource(LyDoCongTacService lyDoCongTacService, LyDoCongTacRepository lyDoCongTacRepository) {
        this.lyDoCongTacService = lyDoCongTacService;
        this.lyDoCongTacRepository = lyDoCongTacRepository;
    }

    /**
     * {@code POST  /ly-do-cong-tacs} : Create a new lyDoCongTac.
     *
     * @param lyDoCongTacDTO the lyDoCongTacDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lyDoCongTacDTO, or with status {@code 400 (Bad Request)} if the lyDoCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ly-do-cong-tacs")
    public ResponseEntity<LyDoCongTacDTO> createLyDoCongTac(@RequestBody LyDoCongTacDTO lyDoCongTacDTO) throws URISyntaxException {
        log.debug("REST request to save LyDoCongTac : {}", lyDoCongTacDTO);
        if (lyDoCongTacDTO.getId() != null) {
            throw new BadRequestAlertException("A new lyDoCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LyDoCongTacDTO result = lyDoCongTacService.save(lyDoCongTacDTO);
        return ResponseEntity
            .created(new URI("/api/ly-do-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ly-do-cong-tacs/:id} : Updates an existing lyDoCongTac.
     *
     * @param id the id of the lyDoCongTacDTO to save.
     * @param lyDoCongTacDTO the lyDoCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lyDoCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the lyDoCongTacDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lyDoCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<LyDoCongTacDTO> updateLyDoCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LyDoCongTacDTO lyDoCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LyDoCongTac : {}, {}", id, lyDoCongTacDTO);
        if (lyDoCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lyDoCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lyDoCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LyDoCongTacDTO result = lyDoCongTacService.update(lyDoCongTacDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lyDoCongTacDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ly-do-cong-tacs/:id} : Partial updates given fields of an existing lyDoCongTac, field will ignore if it is null
     *
     * @param id the id of the lyDoCongTacDTO to save.
     * @param lyDoCongTacDTO the lyDoCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lyDoCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the lyDoCongTacDTO is not valid,
     * or with status {@code 404 (Not Found)} if the lyDoCongTacDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the lyDoCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ly-do-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LyDoCongTacDTO> partialUpdateLyDoCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LyDoCongTacDTO lyDoCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LyDoCongTac partially : {}, {}", id, lyDoCongTacDTO);
        if (lyDoCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lyDoCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lyDoCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LyDoCongTacDTO> result = lyDoCongTacService.partialUpdate(lyDoCongTacDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lyDoCongTacDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ly-do-cong-tacs} : get all the lyDoCongTacs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lyDoCongTacs in body.
     */
    @GetMapping("/ly-do-cong-tacs")
    public ResponseEntity<List<LyDoCongTacDTO>> getAllLyDoCongTacs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of LyDoCongTacs");
        Page<LyDoCongTacDTO> page = lyDoCongTacService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ly-do-cong-tacs/:id} : get the "id" lyDoCongTac.
     *
     * @param id the id of the lyDoCongTacDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lyDoCongTacDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<LyDoCongTacDTO> getLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to get LyDoCongTac : {}", id);
        Optional<LyDoCongTacDTO> lyDoCongTacDTO = lyDoCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lyDoCongTacDTO);
    }

    /**
     * {@code DELETE  /ly-do-cong-tacs/:id} : delete the "id" lyDoCongTac.
     *
     * @param id the id of the lyDoCongTacDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ly-do-cong-tacs/{id}")
    public ResponseEntity<Void> deleteLyDoCongTac(@PathVariable Long id) {
        log.debug("REST request to delete LyDoCongTac : {}", id);
        lyDoCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
