package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.KyThuatHoTroService;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.KyThuatHoTro}.
 */
@RestController
@RequestMapping("/api")
public class KyThuatHoTroResource {

    private final Logger log = LoggerFactory.getLogger(KyThuatHoTroResource.class);

    private static final String ENTITY_NAME = "kyThuatHoTro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KyThuatHoTroService kyThuatHoTroService;

    private final KyThuatHoTroRepository kyThuatHoTroRepository;

    public KyThuatHoTroResource(KyThuatHoTroService kyThuatHoTroService, KyThuatHoTroRepository kyThuatHoTroRepository) {
        this.kyThuatHoTroService = kyThuatHoTroService;
        this.kyThuatHoTroRepository = kyThuatHoTroRepository;
    }

    /**
     * {@code POST  /ky-thuat-ho-tros} : Create a new kyThuatHoTro.
     *
     * @param kyThuatHoTroDTO the kyThuatHoTroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kyThuatHoTroDTO, or with status {@code 400 (Bad Request)} if the kyThuatHoTro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ky-thuat-ho-tros")
    public ResponseEntity<KyThuatHoTroDTO> createKyThuatHoTro(@RequestBody KyThuatHoTroDTO kyThuatHoTroDTO) throws URISyntaxException {
        log.debug("REST request to save KyThuatHoTro : {}", kyThuatHoTroDTO);
        if (kyThuatHoTroDTO.getId() != null) {
            throw new BadRequestAlertException("A new kyThuatHoTro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KyThuatHoTroDTO result = kyThuatHoTroService.save(kyThuatHoTroDTO);
        return ResponseEntity
            .created(new URI("/api/ky-thuat-ho-tros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ky-thuat-ho-tros/:id} : Updates an existing kyThuatHoTro.
     *
     * @param id the id of the kyThuatHoTroDTO to save.
     * @param kyThuatHoTroDTO the kyThuatHoTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kyThuatHoTroDTO,
     * or with status {@code 400 (Bad Request)} if the kyThuatHoTroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kyThuatHoTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ky-thuat-ho-tros/{id}")
    public ResponseEntity<KyThuatHoTroDTO> updateKyThuatHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KyThuatHoTroDTO kyThuatHoTroDTO
    ) throws URISyntaxException {
        log.debug("REST request to update KyThuatHoTro : {}, {}", id, kyThuatHoTroDTO);
        if (kyThuatHoTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kyThuatHoTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kyThuatHoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        KyThuatHoTroDTO result = kyThuatHoTroService.update(kyThuatHoTroDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kyThuatHoTroDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ky-thuat-ho-tros/:id} : Partial updates given fields of an existing kyThuatHoTro, field will ignore if it is null
     *
     * @param id the id of the kyThuatHoTroDTO to save.
     * @param kyThuatHoTroDTO the kyThuatHoTroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kyThuatHoTroDTO,
     * or with status {@code 400 (Bad Request)} if the kyThuatHoTroDTO is not valid,
     * or with status {@code 404 (Not Found)} if the kyThuatHoTroDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the kyThuatHoTroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ky-thuat-ho-tros/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<KyThuatHoTroDTO> partialUpdateKyThuatHoTro(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody KyThuatHoTroDTO kyThuatHoTroDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update KyThuatHoTro partially : {}, {}", id, kyThuatHoTroDTO);
        if (kyThuatHoTroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, kyThuatHoTroDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!kyThuatHoTroRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<KyThuatHoTroDTO> result = kyThuatHoTroService.partialUpdate(kyThuatHoTroDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kyThuatHoTroDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ky-thuat-ho-tros} : get all the kyThuatHoTros.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kyThuatHoTros in body.
     */
    @GetMapping("/ky-thuat-ho-tros")
    public ResponseEntity<List<KyThuatHoTroDTO>> getAllKyThuatHoTros(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of KyThuatHoTros");
        Page<KyThuatHoTroDTO> page = kyThuatHoTroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ky-thuat-ho-tros/:id} : get the "id" kyThuatHoTro.
     *
     * @param id the id of the kyThuatHoTroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kyThuatHoTroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ky-thuat-ho-tros/{id}")
    public ResponseEntity<KyThuatHoTroDTO> getKyThuatHoTro(@PathVariable Long id) {
        log.debug("REST request to get KyThuatHoTro : {}", id);
        Optional<KyThuatHoTroDTO> kyThuatHoTroDTO = kyThuatHoTroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kyThuatHoTroDTO);
    }

    /**
     * {@code DELETE  /ky-thuat-ho-tros/:id} : delete the "id" kyThuatHoTro.
     *
     * @param id the id of the kyThuatHoTroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ky-thuat-ho-tros/{id}")
    public ResponseEntity<Void> deleteKyThuatHoTro(@PathVariable Long id) {
        log.debug("REST request to delete KyThuatHoTro : {}", id);
        kyThuatHoTroService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
