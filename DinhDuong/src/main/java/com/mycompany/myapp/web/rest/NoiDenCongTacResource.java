package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.NoiDenCongTacService;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.NoiDenCongTac}.
 */
@RestController
@RequestMapping("/api")
public class NoiDenCongTacResource {

    private final Logger log = LoggerFactory.getLogger(NoiDenCongTacResource.class);

    private static final String ENTITY_NAME = "noiDenCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoiDenCongTacService noiDenCongTacService;

    private final NoiDenCongTacRepository noiDenCongTacRepository;

    public NoiDenCongTacResource(NoiDenCongTacService noiDenCongTacService, NoiDenCongTacRepository noiDenCongTacRepository) {
        this.noiDenCongTacService = noiDenCongTacService;
        this.noiDenCongTacRepository = noiDenCongTacRepository;
    }

    /**
     * {@code POST  /noi-den-cong-tacs} : Create a new noiDenCongTac.
     *
     * @param noiDenCongTacDTO the noiDenCongTacDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noiDenCongTacDTO, or with status {@code 400 (Bad Request)} if the noiDenCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/noi-den-cong-tacs")
    public ResponseEntity<NoiDenCongTacDTO> createNoiDenCongTac(@RequestBody NoiDenCongTacDTO noiDenCongTacDTO) throws URISyntaxException {
        log.debug("REST request to save NoiDenCongTac : {}", noiDenCongTacDTO);
        if (noiDenCongTacDTO.getId() != null) {
            throw new BadRequestAlertException("A new noiDenCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoiDenCongTacDTO result = noiDenCongTacService.save(noiDenCongTacDTO);
        return ResponseEntity
            .created(new URI("/api/noi-den-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /noi-den-cong-tacs/:id} : Updates an existing noiDenCongTac.
     *
     * @param id the id of the noiDenCongTacDTO to save.
     * @param noiDenCongTacDTO the noiDenCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiDenCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the noiDenCongTacDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noiDenCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<NoiDenCongTacDTO> updateNoiDenCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoiDenCongTacDTO noiDenCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NoiDenCongTac : {}, {}", id, noiDenCongTacDTO);
        if (noiDenCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noiDenCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiDenCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NoiDenCongTacDTO result = noiDenCongTacService.update(noiDenCongTacDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiDenCongTacDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /noi-den-cong-tacs/:id} : Partial updates given fields of an existing noiDenCongTac, field will ignore if it is null
     *
     * @param id the id of the noiDenCongTacDTO to save.
     * @param noiDenCongTacDTO the noiDenCongTacDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noiDenCongTacDTO,
     * or with status {@code 400 (Bad Request)} if the noiDenCongTacDTO is not valid,
     * or with status {@code 404 (Not Found)} if the noiDenCongTacDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the noiDenCongTacDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/noi-den-cong-tacs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NoiDenCongTacDTO> partialUpdateNoiDenCongTac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NoiDenCongTacDTO noiDenCongTacDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NoiDenCongTac partially : {}, {}", id, noiDenCongTacDTO);
        if (noiDenCongTacDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, noiDenCongTacDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!noiDenCongTacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NoiDenCongTacDTO> result = noiDenCongTacService.partialUpdate(noiDenCongTacDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noiDenCongTacDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /noi-den-cong-tacs} : get all the noiDenCongTacs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noiDenCongTacs in body.
     */
    @GetMapping("/noi-den-cong-tacs")
    public ResponseEntity<List<NoiDenCongTacDTO>> getAllNoiDenCongTacs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of NoiDenCongTacs");
        Page<NoiDenCongTacDTO> page = noiDenCongTacService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /noi-den-cong-tacs/:id} : get the "id" noiDenCongTac.
     *
     * @param id the id of the noiDenCongTacDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noiDenCongTacDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<NoiDenCongTacDTO> getNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to get NoiDenCongTac : {}", id);
        Optional<NoiDenCongTacDTO> noiDenCongTacDTO = noiDenCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiDenCongTacDTO);
    }

    /**
     * {@code DELETE  /noi-den-cong-tacs/:id} : delete the "id" noiDenCongTac.
     *
     * @param id the id of the noiDenCongTacDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/noi-den-cong-tacs/{id}")
    public ResponseEntity<Void> deleteNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to delete NoiDenCongTac : {}", id);
        noiDenCongTacService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
