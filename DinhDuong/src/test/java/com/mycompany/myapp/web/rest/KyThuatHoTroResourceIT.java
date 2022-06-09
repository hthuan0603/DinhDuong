package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.KyThuatHoTro;
import com.mycompany.myapp.repository.KyThuatHoTroRepository;
import com.mycompany.myapp.service.dto.KyThuatHoTroDTO;
import com.mycompany.myapp.service.mapper.KyThuatHoTroMapper;
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
 * Integration tests for the {@link KyThuatHoTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KyThuatHoTroResourceIT {

    private static final Integer DEFAULT_MA_KY_THUAT = 1;
    private static final Integer UPDATED_MA_KY_THUAT = 2;

    private static final String DEFAULT_TEN_KY_THUAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KY_THUAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_THU_TU_SX = 1;
    private static final Integer UPDATED_THU_TU_SX = 2;

    private static final String ENTITY_API_URL = "/api/ky-thuat-ho-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private KyThuatHoTroRepository kyThuatHoTroRepository;

    @Autowired
    private KyThuatHoTroMapper kyThuatHoTroMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKyThuatHoTroMockMvc;

    private KyThuatHoTro kyThuatHoTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KyThuatHoTro createEntity(EntityManager em) {
        KyThuatHoTro kyThuatHoTro = new KyThuatHoTro()
            .maKyThuat(DEFAULT_MA_KY_THUAT)
            .tenKyThuat(DEFAULT_TEN_KY_THUAT)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return kyThuatHoTro;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KyThuatHoTro createUpdatedEntity(EntityManager em) {
        KyThuatHoTro kyThuatHoTro = new KyThuatHoTro()
            .maKyThuat(UPDATED_MA_KY_THUAT)
            .tenKyThuat(UPDATED_TEN_KY_THUAT)
            .thuTuSX(UPDATED_THU_TU_SX);
        return kyThuatHoTro;
    }

    @BeforeEach
    public void initTest() {
        kyThuatHoTro = createEntity(em);
    }

    @Test
    @Transactional
    void createKyThuatHoTro() throws Exception {
        int databaseSizeBeforeCreate = kyThuatHoTroRepository.findAll().size();
        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);
        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isCreated());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeCreate + 1);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(DEFAULT_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(DEFAULT_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createKyThuatHoTroWithExistingId() throws Exception {
        // Create the KyThuatHoTro with an existing ID
        kyThuatHoTro.setId(1L);
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        int databaseSizeBeforeCreate = kyThuatHoTroRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKyThuatHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllKyThuatHoTros() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get all the kyThuatHoTroList
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kyThuatHoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKyThuat").value(hasItem(DEFAULT_MA_KY_THUAT)))
            .andExpect(jsonPath("$.[*].tenKyThuat").value(hasItem(DEFAULT_TEN_KY_THUAT)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        // Get the kyThuatHoTro
        restKyThuatHoTroMockMvc
            .perform(get(ENTITY_API_URL_ID, kyThuatHoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kyThuatHoTro.getId().intValue()))
            .andExpect(jsonPath("$.maKyThuat").value(DEFAULT_MA_KY_THUAT))
            .andExpect(jsonPath("$.tenKyThuat").value(DEFAULT_TEN_KY_THUAT))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getNonExistingKyThuatHoTro() throws Exception {
        // Get the kyThuatHoTro
        restKyThuatHoTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro
        KyThuatHoTro updatedKyThuatHoTro = kyThuatHoTroRepository.findById(kyThuatHoTro.getId()).get();
        // Disconnect from session so that the updates on updatedKyThuatHoTro are not directly saved in db
        em.detach(updatedKyThuatHoTro);
        updatedKyThuatHoTro.maKyThuat(UPDATED_MA_KY_THUAT).tenKyThuat(UPDATED_TEN_KY_THUAT).thuTuSX(UPDATED_THU_TU_SX);
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(updatedKyThuatHoTro);

        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(UPDATED_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(UPDATED_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateKyThuatHoTroWithPatch() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro using partial update
        KyThuatHoTro partialUpdatedKyThuatHoTro = new KyThuatHoTro();
        partialUpdatedKyThuatHoTro.setId(kyThuatHoTro.getId());

        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKyThuatHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKyThuatHoTro))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(DEFAULT_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(DEFAULT_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateKyThuatHoTroWithPatch() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();

        // Update the kyThuatHoTro using partial update
        KyThuatHoTro partialUpdatedKyThuatHoTro = new KyThuatHoTro();
        partialUpdatedKyThuatHoTro.setId(kyThuatHoTro.getId());

        partialUpdatedKyThuatHoTro.maKyThuat(UPDATED_MA_KY_THUAT).tenKyThuat(UPDATED_TEN_KY_THUAT).thuTuSX(UPDATED_THU_TU_SX);

        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKyThuatHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKyThuatHoTro))
            )
            .andExpect(status().isOk());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
        KyThuatHoTro testKyThuatHoTro = kyThuatHoTroList.get(kyThuatHoTroList.size() - 1);
        assertThat(testKyThuatHoTro.getMaKyThuat()).isEqualTo(UPDATED_MA_KY_THUAT);
        assertThat(testKyThuatHoTro.getTenKyThuat()).isEqualTo(UPDATED_TEN_KY_THUAT);
        assertThat(testKyThuatHoTro.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, kyThuatHoTroDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamKyThuatHoTro() throws Exception {
        int databaseSizeBeforeUpdate = kyThuatHoTroRepository.findAll().size();
        kyThuatHoTro.setId(count.incrementAndGet());

        // Create the KyThuatHoTro
        KyThuatHoTroDTO kyThuatHoTroDTO = kyThuatHoTroMapper.toDto(kyThuatHoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKyThuatHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(kyThuatHoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KyThuatHoTro in the database
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteKyThuatHoTro() throws Exception {
        // Initialize the database
        kyThuatHoTroRepository.saveAndFlush(kyThuatHoTro);

        int databaseSizeBeforeDelete = kyThuatHoTroRepository.findAll().size();

        // Delete the kyThuatHoTro
        restKyThuatHoTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, kyThuatHoTro.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KyThuatHoTro> kyThuatHoTroList = kyThuatHoTroRepository.findAll();
        assertThat(kyThuatHoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
