package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.BaoCaoTaiChinhRepository;
import com.mycompany.myapp.service.BaoCaoTaiChinhService;
import com.mycompany.myapp.service.dto.BaoCaoTaiChinhDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BaoCaoTaiChinh}.
 */
@RestController
@RequestMapping("/api")
public class BaoCaoTaiChinhResource {

    private final Logger log = LoggerFactory.getLogger(BaoCaoTaiChinhResource.class);

    private static final String ENTITY_NAME = "baoCaoTaiChinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BaoCaoTaiChinhService baoCaoTaiChinhService;

    private final BaoCaoTaiChinhRepository baoCaoTaiChinhRepository;

    public BaoCaoTaiChinhResource(BaoCaoTaiChinhService baoCaoTaiChinhService, BaoCaoTaiChinhRepository baoCaoTaiChinhRepository) {
        this.baoCaoTaiChinhService = baoCaoTaiChinhService;
        this.baoCaoTaiChinhRepository = baoCaoTaiChinhRepository;
    }

    /**
     * {@code POST  /bao-cao-tai-chinhs} : Create a new baoCaoTaiChinh.
     *
     * @param baoCaoTaiChinhDTO the baoCaoTaiChinhDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new baoCaoTaiChinhDTO, or with status {@code 400 (Bad Request)} if the baoCaoTaiChinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bao-cao-tai-chinhs")
    public ResponseEntity<BaoCaoTaiChinhDTO> createBaoCaoTaiChinh(@RequestBody BaoCaoTaiChinhDTO baoCaoTaiChinhDTO)
        throws URISyntaxException {
        log.debug("REST request to save BaoCaoTaiChinh : {}", baoCaoTaiChinhDTO);
        if (baoCaoTaiChinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new baoCaoTaiChinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaoCaoTaiChinhDTO result = baoCaoTaiChinhService.save(baoCaoTaiChinhDTO);
        return ResponseEntity
            .created(new URI("/api/bao-cao-tai-chinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bao-cao-tai-chinhs/:id} : Updates an existing baoCaoTaiChinh.
     *
     * @param id the id of the baoCaoTaiChinhDTO to save.
     * @param baoCaoTaiChinhDTO the baoCaoTaiChinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baoCaoTaiChinhDTO,
     * or with status {@code 400 (Bad Request)} if the baoCaoTaiChinhDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the baoCaoTaiChinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bao-cao-tai-chinhs/{id}")
    public ResponseEntity<BaoCaoTaiChinhDTO> updateBaoCaoTaiChinh(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BaoCaoTaiChinhDTO baoCaoTaiChinhDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BaoCaoTaiChinh : {}, {}", id, baoCaoTaiChinhDTO);
        if (baoCaoTaiChinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, baoCaoTaiChinhDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!baoCaoTaiChinhRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BaoCaoTaiChinhDTO result = baoCaoTaiChinhService.update(baoCaoTaiChinhDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baoCaoTaiChinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bao-cao-tai-chinhs/:id} : Partial updates given fields of an existing baoCaoTaiChinh, field will ignore if it is null
     *
     * @param id the id of the baoCaoTaiChinhDTO to save.
     * @param baoCaoTaiChinhDTO the baoCaoTaiChinhDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baoCaoTaiChinhDTO,
     * or with status {@code 400 (Bad Request)} if the baoCaoTaiChinhDTO is not valid,
     * or with status {@code 404 (Not Found)} if the baoCaoTaiChinhDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the baoCaoTaiChinhDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bao-cao-tai-chinhs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BaoCaoTaiChinhDTO> partialUpdateBaoCaoTaiChinh(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BaoCaoTaiChinhDTO baoCaoTaiChinhDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BaoCaoTaiChinh partially : {}, {}", id, baoCaoTaiChinhDTO);
        if (baoCaoTaiChinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, baoCaoTaiChinhDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!baoCaoTaiChinhRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BaoCaoTaiChinhDTO> result = baoCaoTaiChinhService.partialUpdate(baoCaoTaiChinhDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, baoCaoTaiChinhDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bao-cao-tai-chinhs} : get all the baoCaoTaiChinhs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of baoCaoTaiChinhs in body.
     */
    @GetMapping("/bao-cao-tai-chinhs")
    public ResponseEntity<List<BaoCaoTaiChinhDTO>> getAllBaoCaoTaiChinhs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BaoCaoTaiChinhs");
        Page<BaoCaoTaiChinhDTO> page = baoCaoTaiChinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bao-cao-tai-chinhs/:id} : get the "id" baoCaoTaiChinh.
     *
     * @param id the id of the baoCaoTaiChinhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the baoCaoTaiChinhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bao-cao-tai-chinhs/{id}")
    public ResponseEntity<BaoCaoTaiChinhDTO> getBaoCaoTaiChinh(@PathVariable Long id) {
        log.debug("REST request to get BaoCaoTaiChinh : {}", id);
        Optional<BaoCaoTaiChinhDTO> baoCaoTaiChinhDTO = baoCaoTaiChinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(baoCaoTaiChinhDTO);
    }

    /**
     * {@code DELETE  /bao-cao-tai-chinhs/:id} : delete the "id" baoCaoTaiChinh.
     *
     * @param id the id of the baoCaoTaiChinhDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bao-cao-tai-chinhs/{id}")
    public ResponseEntity<Void> deleteBaoCaoTaiChinh(@PathVariable Long id) {
        log.debug("REST request to delete BaoCaoTaiChinh : {}", id);
        baoCaoTaiChinhService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
