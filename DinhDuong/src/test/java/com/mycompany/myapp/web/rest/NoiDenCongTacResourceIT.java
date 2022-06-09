package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import com.mycompany.myapp.service.mapper.NoiDenCongTacMapper;
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
 * Integration tests for the {@link NoiDenCongTacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoiDenCongTacResourceIT {

    private static final Integer DEFAULT_MA_NOI_DEN = 1;
    private static final Integer UPDATED_MA_NOI_DEN = 2;

    private static final String DEFAULT_TEN_NOI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NOI_DEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_THU_TU_SX = 1;
    private static final Integer UPDATED_THU_TU_SX = 2;

    private static final String ENTITY_API_URL = "/api/noi-den-cong-tacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NoiDenCongTacRepository noiDenCongTacRepository;

    @Autowired
    private NoiDenCongTacMapper noiDenCongTacMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoiDenCongTacMockMvc;

    private NoiDenCongTac noiDenCongTac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiDenCongTac createEntity(EntityManager em) {
        NoiDenCongTac noiDenCongTac = new NoiDenCongTac()
            .maNoiDen(DEFAULT_MA_NOI_DEN)
            .tenNoiDen(DEFAULT_TEN_NOI_DEN)
            .thuTuSX(DEFAULT_THU_TU_SX);
        return noiDenCongTac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiDenCongTac createUpdatedEntity(EntityManager em) {
        NoiDenCongTac noiDenCongTac = new NoiDenCongTac()
            .maNoiDen(UPDATED_MA_NOI_DEN)
            .tenNoiDen(UPDATED_TEN_NOI_DEN)
            .thuTuSX(UPDATED_THU_TU_SX);
        return noiDenCongTac;
    }

    @BeforeEach
    public void initTest() {
        noiDenCongTac = createEntity(em);
    }

    @Test
    @Transactional
    void createNoiDenCongTac() throws Exception {
        int databaseSizeBeforeCreate = noiDenCongTacRepository.findAll().size();
        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);
        restNoiDenCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeCreate + 1);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(DEFAULT_THU_TU_SX);
    }

    @Test
    @Transactional
    void createNoiDenCongTacWithExistingId() throws Exception {
        // Create the NoiDenCongTac with an existing ID
        noiDenCongTac.setId(1L);
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        int databaseSizeBeforeCreate = noiDenCongTacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiDenCongTacMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoiDenCongTacs() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get all the noiDenCongTacList
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDenCongTac.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDen").value(hasItem(DEFAULT_MA_NOI_DEN)))
            .andExpect(jsonPath("$.[*].tenNoiDen").value(hasItem(DEFAULT_TEN_NOI_DEN)))
            .andExpect(jsonPath("$.[*].thuTuSX").value(hasItem(DEFAULT_THU_TU_SX)));
    }

    @Test
    @Transactional
    void getNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        // Get the noiDenCongTac
        restNoiDenCongTacMockMvc
            .perform(get(ENTITY_API_URL_ID, noiDenCongTac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noiDenCongTac.getId().intValue()))
            .andExpect(jsonPath("$.maNoiDen").value(DEFAULT_MA_NOI_DEN))
            .andExpect(jsonPath("$.tenNoiDen").value(DEFAULT_TEN_NOI_DEN))
            .andExpect(jsonPath("$.thuTuSX").value(DEFAULT_THU_TU_SX));
    }

    @Test
    @Transactional
    void getNonExistingNoiDenCongTac() throws Exception {
        // Get the noiDenCongTac
        restNoiDenCongTacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac
        NoiDenCongTac updatedNoiDenCongTac = noiDenCongTacRepository.findById(noiDenCongTac.getId()).get();
        // Disconnect from session so that the updates on updatedNoiDenCongTac are not directly saved in db
        em.detach(updatedNoiDenCongTac);
        updatedNoiDenCongTac.maNoiDen(UPDATED_MA_NOI_DEN).tenNoiDen(UPDATED_TEN_NOI_DEN).thuTuSX(UPDATED_THU_TU_SX);
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(updatedNoiDenCongTac);

        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void putNonExistingNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoiDenCongTacWithPatch() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac using partial update
        NoiDenCongTac partialUpdatedNoiDenCongTac = new NoiDenCongTac();
        partialUpdatedNoiDenCongTac.setId(noiDenCongTac.getId());

        partialUpdatedNoiDenCongTac.thuTuSX(UPDATED_THU_TU_SX);

        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiDenCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoiDenCongTac))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(DEFAULT_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(DEFAULT_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void fullUpdateNoiDenCongTacWithPatch() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();

        // Update the noiDenCongTac using partial update
        NoiDenCongTac partialUpdatedNoiDenCongTac = new NoiDenCongTac();
        partialUpdatedNoiDenCongTac.setId(noiDenCongTac.getId());

        partialUpdatedNoiDenCongTac.maNoiDen(UPDATED_MA_NOI_DEN).tenNoiDen(UPDATED_TEN_NOI_DEN).thuTuSX(UPDATED_THU_TU_SX);

        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiDenCongTac.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNoiDenCongTac))
            )
            .andExpect(status().isOk());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
        NoiDenCongTac testNoiDenCongTac = noiDenCongTacList.get(noiDenCongTacList.size() - 1);
        assertThat(testNoiDenCongTac.getMaNoiDen()).isEqualTo(UPDATED_MA_NOI_DEN);
        assertThat(testNoiDenCongTac.getTenNoiDen()).isEqualTo(UPDATED_TEN_NOI_DEN);
        assertThat(testNoiDenCongTac.getThuTuSX()).isEqualTo(UPDATED_THU_TU_SX);
    }

    @Test
    @Transactional
    void patchNonExistingNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noiDenCongTacDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoiDenCongTac() throws Exception {
        int databaseSizeBeforeUpdate = noiDenCongTacRepository.findAll().size();
        noiDenCongTac.setId(count.incrementAndGet());

        // Create the NoiDenCongTac
        NoiDenCongTacDTO noiDenCongTacDTO = noiDenCongTacMapper.toDto(noiDenCongTac);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiDenCongTacMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(noiDenCongTacDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiDenCongTac in the database
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoiDenCongTac() throws Exception {
        // Initialize the database
        noiDenCongTacRepository.saveAndFlush(noiDenCongTac);

        int databaseSizeBeforeDelete = noiDenCongTacRepository.findAll().size();

        // Delete the noiDenCongTac
        restNoiDenCongTacMockMvc
            .perform(delete(ENTITY_API_URL_ID, noiDenCongTac.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoiDenCongTac> noiDenCongTacList = noiDenCongTacRepository.findAll();
        assertThat(noiDenCongTacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
