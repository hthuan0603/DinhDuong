package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.repository.VatTuHoTroRepository;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import com.mycompany.myapp.service.mapper.VatTuHoTroMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VatTuHoTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VatTuHoTroResourceIT {

    private static final Integer DEFAULT_MA_VAT_TU = 1;
    private static final Integer UPDATED_MA_VAT_TU = 2;

    private static final String DEFAULT_TEN_VAT_TU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_VAT_TU = "BBBBBBBBBB";

    private static final Integer DEFAULT_THU_TU_SX = 1;
    private static final Integer UPDATED_THU_TU_SX = 2;

    private static final String ENTITY_API_URL = "/api/vat-tu-ho-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VatTuHoTroRepository vatTuHoTroRepository;

    @Autowired
    private VatTuHoTroMapper vatTuHoTroMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVatTuHoTroMockMvc;

    private VatTuHoTro vatTuHoTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VatTuHoTro createEntity(EntityManager em) {
        VatTuHoTro vatTuHoTro = new VatTuHoTro().maVatTu(DEFAULT_MA_VAT_TU).tenVatTu(DEFAULT_TEN_VAT_TU).thuTuSX(DEFAULT_THU_TU_SX);
        return vatTuHoTro;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VatTuHoTro createUpdatedEntity(EntityManager em) {
        VatTuHoTro vatTuHoTro = new VatTuHoTro().maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);
        return vatTuHoTro;
    }

    @BeforeEach
    public void initTest() {
        vatTuHoTro = createEntity(em);
    }

    @Test
    @Transactional
    void createVatTuHoTro() throws Exception {
        int databaseSizeBeforeCreate = vatTuHoTroRepository.findAll().size();
        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);
        restVatTuHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(DEFAULT_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(DEFAULT_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createVatTuHoTroWithExistingId() throws Exception {
        // Create the VatTuHoTro with an existing ID
        vatTuHoTro.setId(1L);
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        int databaseSizeBeforeCreate = vatTuHoTroRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVatTuHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVatTuHoTros() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get all the vatTuHoTroList
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vatTuHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maVatTu").value(hasItem(DEFAULT_MA_VAT_TU)))
            .andExpect(jsonPath("$.[*].tenVatTu").value(hasItem(DEFAULT_TEN_VAT_TU)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        // Get the vatTuHoTro
        restVatTuHoTroMockMvc
            .perform(get(ENTITY_API_URL_ID, vatTuHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vatTuHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maVatTu").value(DEFAULT_MA_VAT_TU))
            .andExpect(jsonPath("$.tenVatTu").value(DEFAULT_TEN_VAT_TU))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getNonExistingVatTuHoTro() throws Exception {
        // Get the vatTuHoTro
        restVatTuHoTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro
        VatTuHoTro updatedVatTuHoTro = vatTuHoTroRepository.findById(vatTuHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedVatTuHoTro are not directly saved in db
        em.detach(updatedVatTuHoTro);
        updatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(updatedVatTuHoTro);

        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVatTuHoTroWithPatch() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro using partial update
        VatTuHoTro partialUpdatedVatTuHoTro = new VatTuHoTro();
        partialUpdatedVatTuHoTro.setId(vatTuHoTro.getId());

        partialUpdatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);

        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVatTuHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVatTuHoTro))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateVatTuHoTroWithPatch() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();

        // Update the vatTuHoTro using partial update
        VatTuHoTro partialUpdatedVatTuHoTro = new VatTuHoTro();
        partialUpdatedVatTuHoTro.setId(vatTuHoTro.getId());

        partialUpdatedVatTuHoTro.maVatTu(UPDATED_MA_VAT_TU).tenVatTu(UPDATED_TEN_VAT_TU).thuTuSX(UPDATED_THU_TU_SX);

        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVatTuHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVatTuHoTro))
            )
            .andExpect(status().isOk());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
        VatTuHoTro testVatTuHoTro = vatTuHoTroList.get(vatTuHoTroList.size() - 1);
        assertThat(testVatTuHoTro.getMaVatTu()).isEqualTo(UPDATED_MA_VAT_TU);
        assertThat(testVatTuHoTro.getTenVatTu()).isEqualTo(UPDATED_TEN_VAT_TU);
        assertThat(testVatTuHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vatTuHoTroDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVatTuHoTro() throws Exception {
        int databaseSizeBeforeUpdate = vatTuHoTroRepository.findAll().size();
        vatTuHoTro.setId(count.incrementAndGet());

        // Create the VatTuHoTro
        VatTuHoTroDTO vatTuHoTroDTO = vatTuHoTroMapper.toDto(vatTuHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVatTuHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vatTuHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VatTuHoTro in the database
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVatTuHoTro() throws Exception {
        // Initialize the database
        vatTuHoTroRepository.saveAndFlush(vatTuHoTro);

        int databaseSizeBeforeDelete = vatTuHoTroRepository.findAll().size();

        // Delete the vatTuHoTro
        restVatTuHoTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, vatTuHoTro.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VatTuHoTro> vatTuHoTroList = vatTuHoTroRepository.findAll();
        assertThat(vatTuHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
