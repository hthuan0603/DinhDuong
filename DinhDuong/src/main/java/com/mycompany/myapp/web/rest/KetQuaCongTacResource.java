package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.KetQuaCongTacService;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.KetQuaCongTac}.
 */
@RestController
@RequestMapping("/api")
public class KetQuaCongTacResource {

    private final Logger log = LoggerFactory.getLogger(KetQuaCongTacResource.class);

    private static final String ENTITY_NAME = "ketQuaCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KetQuaCongTacService ketQuaCongTacService;

    private final KetQuaCongTacRepository ketQuaCongTacRepository;

    public KetQuaCongTacResource(KetQuaCongTacService ketQuaCongTacService, KetQuaCongTacRepository ketQuaCongTacRepository) {
        this.ketQuaCongTacService = ketQuaCongTacService;
        this.ketQuaCongTacRepository = ketQuaCongTacRepository;
    }

    /**
     * {@code POST  /ket-qua-cong-tacs} : Create a new ketQuaCongTac.
     *
     * @param ketQuaCongTacDTO the ketQuaCongTacDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ketQuaCongTacDTO, or with status {@code 400 (Bad Request)} if the ketQuaCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ket-qua-cong-tacs")
    public ResponseEntity<KetQuaCongTacDTO> createKetQuaCongTac(@RequestBody KetQuaCongTacDTO ketQuaCongTacDTO) throws URISyntaxException {
        log.debug("REST request to save KetQuaCongTac : {}", ketQuaCongTacDTO);
        if (ketQuaCongTacDTO.getId() != null) {
            throw new BadRequestAlertException("A new ketQuaCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KetQuaCongTacDTO result = ketQuaCongTacService.save(ketQuaCongTacDTO);
        return ResponseEntity
            .created(new URI("/api/ket-qua-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ket-qua-cong-tacs/:id} : Updates an existing ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTacDTO to save.
     * @param ketQuaCongTacDTO the ketQuaCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ketQuaCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the ketQuaCongTacDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ketQuaCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<KetQuaCongTacDTO> updateKetQuaCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KetQuaCongTacDTO ketQuaCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to update KetQuaCongTac : {}, {}", id, ketQuaCongTacDTO);
        if (ketQuaCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ketQuaCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ketQuaCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        KetQuaCongTacDTO result = ketQuaCongTacService.update(ketQuaCongTacDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ketQuaCongTacDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ket-qua-cong-tacs/:id} : Partial updates given fields of an existing ketQuaCongTac, field will ignore if it is null
     *
     * @param id the id of the ketQuaCongTacDTO to save.
     * @param ketQuaCongTacDTO the ketQuaCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ketQuaCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the ketQuaCongTacDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ketQuaCongTacDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ketQuaCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ket-qua-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<KetQuaCongTacDTO> partialUpdateKetQuaCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KetQuaCongTacDTO ketQuaCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update KetQuaCongTac partially : {}, {}", id, ketQuaCongTacDTO);
        if (ketQuaCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ketQuaCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ketQuaCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<KetQuaCongTacDTO> result = ketQuaCongTacService.partialUpdate(ketQuaCongTacDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ketQuaCongTacDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ket-qua-cong-tacs} : get all the ketQuaCongTacs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ketQuaCongTacs in body.
     */
    @GetMapping("/ket-qua-cong-tacs")
    public ResponseEntity<List<KetQuaCongTacDTO>> getAllKetQuaCongTacs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of KetQuaCongTacs");
        Page<KetQuaCongTacDTO> page = ketQuaCongTacService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ket-qua-cong-tacs/:id} : get the "id" ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTacDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ketQuaCongTacDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<KetQuaCongTacDTO> getKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to get KetQuaCongTac : {}", id);
        Optional<KetQuaCongTacDTO> ketQuaCongTacDTO = ketQuaCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ketQuaCongTacDTO);
    }

    /**
     * {@code DELETE  /ket-qua-cong-tacs/:id} : delete the "id" ketQuaCongTac.
     *
     * @param id the id of the ketQuaCongTacDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ket-qua-cong-tacs/{id}")
    public ResponseEntity<Void> deleteKetQuaCongTac(@PathVariable Long id) {
        log.debug("REST request to delete KetQuaCongTac : {}", id);
        ketQuaCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
