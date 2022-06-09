package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.repository.KetQuaCongTacRepository;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import com.mycompany.myapp.service.mapper.KetQuaCongTacMapper;
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
 * Integration tests for the {@link KetQuaCongTacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class KetQuaCongTacResourceIT {

    private static final Integer DEFAULT_MA_KET_QUA = 1;
    private static final Integer UPDATED_MA_KET_QUA = 2;

    private static final String DEFAULT_TEN_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KET_QUA = "BBBBBBBBBB";

    private static final Integer DEFAULT_THU_TU_SX = 1;
    private static final Integer UPDATED_THU_TU_SX = 2;

    private static final String ENTITY_API_URL = "/api/ket-qua-cong-tacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private KetQuaCongTacRepository ketQuaCongTacRepository;

    @Autowired
    private KetQuaCongTacMapper ketQuaCongTacMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKetQuaCongTacMockMvc;

    private KetQuaCongTac ketQuaCongTac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KetQuaCongTac createEntity(EntityManager em) {
        KetQuaCongTac ketQuaCongTac = new KetQuaCongTac()
            .maKetQua(DEFAULT_MA_KET_QUA)
            .tenKetQua(DEFAULT_TEN_KET_QUA)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return ketQuaCongTac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KetQuaCongTac createUpdatedEntity(EntityManager em) {
        KetQuaCongTac ketQuaCongTac = new KetQuaCongTac()
            .maKetQua(UPDATED_MA_KET_QUA)
            .tenKetQua(UPDATED_TEN_KET_QUA)
            .thuTuSX(UPDATED_THU_TU_SX);
        return ketQuaCongTac;
    }

    @BeforeEach
    public void initTest() {
        ketQuaCongTac = createEntity(em);
    }

    @Test
    @Transactional
    void createKetQuaCongTac() throws Exception {
        int databaseSizeBeforeCreate = ketQuaCongTacRepository.findAll().size();
        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);
        restKetQuaCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isCreated());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createKetQuaCongTacWithExistingId() throws Exception {
        // Create the KetQuaCongTac with an existing ID
        ketQuaCongTac.setId(1L);
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        int databaseSizeBeforeCreate = ketQuaCongTacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restKetQuaCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllKetQuaCongTacs() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get all the ketQuaCongTacList
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ketQuaCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKetQua").value(hasItem(DEFAULT_MA_KET_QUA)))
            .andExpect(jsonPath("$.[*].tenKetQua").value(hasItem(DEFAULT_TEN_KET_QUA)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        // Get the ketQuaCongTac
        restKetQuaCongTacMockMvc
            .perform(get(ENTITY_API_URL_ID, ketQuaCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ketQuaCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maKetQua").value(DEFAULT_MA_KET_QUA))
            .andExpect(jsonPath("$.tenKetQua").value(DEFAULT_TEN_KET_QUA))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getNonExistingKetQuaCongTac() throws Exception {
        // Get the ketQuaCongTac
        restKetQuaCongTacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac
        KetQuaCongTac updatedKetQuaCongTac = ketQuaCongTacRepository.findById(ketQuaCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedKetQuaCongTac are not directly saved in db
        em.detach(updatedKetQuaCongTac);
        updatedKetQuaCongTac.maKetQua(UPDATED_MA_KET_QUA).tenKetQua(UPDATED_TEN_KET_QUA).thuTuSX(UPDATED_THU_TU_SX);
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(updatedKetQuaCongTac);

        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateKetQuaCongTacWithPatch() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac using partial update
        KetQuaCongTac partialUpdatedKetQuaCongTac = new KetQuaCongTac();
        partialUpdatedKetQuaCongTac.setId(ketQuaCongTac.getId());

        partialUpdatedKetQuaCongTac.thuTuSX(UPDATED_THU_TU_SX);

        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKetQuaCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKetQuaCongTac))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(DEFAULT_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(DEFAULT_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateKetQuaCongTacWithPatch() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();

        // Update the ketQuaCongTac using partial update
        KetQuaCongTac partialUpdatedKetQuaCongTac = new KetQuaCongTac();
        partialUpdatedKetQuaCongTac.setId(ketQuaCongTac.getId());

        partialUpdatedKetQuaCongTac.maKetQua(UPDATED_MA_KET_QUA).tenKetQua(UPDATED_TEN_KET_QUA).thuTuSX(UPDATED_THU_TU_SX);

        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedKetQuaCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedKetQuaCongTac))
            )
            .andExpect(status().isOk());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
        KetQuaCongTac testKetQuaCongTac = ketQuaCongTacList.get(ketQuaCongTacList.size() - 1);
        assertThat(testKetQuaCongTac.getMaKetQua()).isEqualTo(UPDATED_MA_KET_QUA);
        assertThat(testKetQuaCongTac.getTenKetQua()).isEqualTo(UPDATED_TEN_KET_QUA);
        assertThat(testKetQuaCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ketQuaCongTacDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamKetQuaCongTac() throws Exception {
        int databaseSizeBeforeUpdate = ketQuaCongTacRepository.findAll().size();
        ketQuaCongTac.setId(count.incrementAndGet());

        // Create the KetQuaCongTac
        KetQuaCongTacDTO ketQuaCongTacDTO = ketQuaCongTacMapper.toDto(ketQuaCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restKetQuaCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ketQuaCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the KetQuaCongTac in the database
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteKetQuaCongTac() throws Exception {
        // Initialize the database
        ketQuaCongTacRepository.saveAndFlush(ketQuaCongTac);

        int databaseSizeBeforeDelete = ketQuaCongTacRepository.findAll().size();

        // Delete the ketQuaCongTac
        restKetQuaCongTacMockMvc
            .perform(delete(ENTITY_API_URL_ID, ketQuaCongTac.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KetQuaCongTac> ketQuaCongTacList = ketQuaCongTacRepository.findAll();
        assertThat(ketQuaCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
