package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.HoTroRepository;
import com.mycompany.myapp.service.HoTroService;
import com.mycompany.myapp.service.dto.HoTroDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.HoTro}.
 */
@RestController
@RequestMapping("/api")
public class HoTroResource {

    private final Logger log = LoggerFactory.getLogger(HoTroResource.class);

    private static final String ENTITY_NAME = "hoTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HoTroService hoTroService;

    private final HoTroRepository hoTroRepository;

    public HoTroResource(HoTroService hoTroService, HoTroRepository hoTroRepository) {
        this.hoTroService = hoTroService;
        this.hoTroRepository = hoTroRepository;
    }

    /**
     * {@code POST  /ho-tros} : Create a new hoTro.
     *
     * @param hoTroDTO the hoTroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hoTroDTO, or with status {@code 400 (Bad Request)} if the hoTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ho-tros")
    public ResponseEntity<HoTroDTO> createHoTro(@RequestBody HoTroDTO hoTroDTO) throws URISyntaxException {
        log.debug("REST request to save HoTro : {}", hoTroDTO);
        if (hoTroDTO.getId() != null) {
            throw new BadRequestAlertException("A new hoTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoTroDTO result = hoTroService.save(hoTroDTO);
        return ResponseEntity
            .created(new URI("/api/ho-tros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ho-tros/:id} : Updates an existing hoTro.
     *
     * @param id the id of the hoTroDTO to save.
     * @param hoTroDTO the hoTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hoTroDTO,
     * or with status {@code 400 (Bad Request)} if the hoTroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hoTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ho-tros/{id}")
    public ResponseEntity<HoTroDTO> updateHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HoTroDTO hoTroDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HoTro : {}, {}", id, hoTroDTO);
        if (hoTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hoTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HoTroDTO result = hoTroService.update(hoTroDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hoTroDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ho-tros/:id} : Partial updates given fields of an existing hoTro, field will ignore if it is null
     *
     * @param id the id of the hoTroDTO to save.
     * @param hoTroDTO the hoTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hoTroDTO,
     * or with status {@code 400 (Bad Request)} if the hoTroDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hoTroDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hoTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ho-tros/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HoTroDTO> partialUpdateHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HoTroDTO hoTroDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HoTro partially : {}, {}", id, hoTroDTO);
        if (hoTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hoTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HoTroDTO> result = hoTroService.partialUpdate(hoTroDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hoTroDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ho-tros} : get all the hoTros.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hoTros in body.
     */
    @GetMapping("/ho-tros")
    public ResponseEntity<List<HoTroDTO>> getAllHoTros(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HoTros");
        Page<HoTroDTO> page = hoTroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ho-tros/:id} : get the "id" hoTro.
     *
     * @param id the id of the hoTroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hoTroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ho-tros/{id}")
    public ResponseEntity<HoTroDTO> getHoTro(@PathVariable Long id) {
        log.debug("REST request to get HoTro : {}", id);
        Optional<HoTroDTO> hoTroDTO = hoTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hoTroDTO);
    }

    /**
     * {@code DELETE  /ho-tros/:id} : delete the "id" hoTro.
     *
     * @param id the id of the hoTroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ho-tros/{id}")
    public ResponseEntity<Void> deleteHoTro(@PathVariable Long id) {
        log.debug("REST request to delete HoTro : {}", id);
        hoTroService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
