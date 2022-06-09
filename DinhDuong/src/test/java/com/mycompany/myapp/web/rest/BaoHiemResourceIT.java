package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.BaoHiem;
import com.mycompany.myapp.repository.BaoHiemRepository;
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
 * Integration tests for the {@link BaoHiemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BaoHiemResourceIT {

    private static final String DEFAULT_MA_THE_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_THE_BHYT = "BBBBBBBBBB";

    private static final String DEFAULT_DOI_TUONG = "AAAAAAAAAA";
    private static final String UPDATED_DOI_TUONG = "BBBBBBBBBB";

    private static final Integer DEFAULT_BAO_HIEM_THANH_TOAN = 1;
    private static final Integer UPDATED_BAO_HIEM_THANH_TOAN = 2;

    private static final String ENTITY_API_URL = "/api/bao-hiems";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BaoHiemRepository baoHiemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBaoHiemMockMvc;

    private BaoHiem baoHiem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BaoHiem createEntity(EntityManager em) {
        BaoHiem baoHiem = new BaoHiem()
            .maTheBHYT(DEFAULT_MA_THE_BHYT)
            .doiTuong(DEFAULT_DOI_TUONG)
            .baoHiemThanhToan(DEFAULT_BAO_HIEM_THANH_TOAN);
        return baoHiem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BaoHiem createUpdatedEntity(EntityManager em) {
        BaoHiem baoHiem = new BaoHiem()
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .doiTuong(UPDATED_DOI_TUONG)
            .baoHiemThanhToan(UPDATED_BAO_HIEM_THANH_TOAN);
        return baoHiem;
    }

    @BeforeEach
    public void initTest() {
        baoHiem = createEntity(em);
    }

    @Test
    @Transactional
    void createBaoHiem() throws Exception {
        int databaseSizeBeforeCreate = baoHiemRepository.findAll().size();
        // Create the BaoHiem
        restBaoHiemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isCreated());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeCreate + 1);
        BaoHiem testBaoHiem = baoHiemList.get(baoHiemList.size() - 1);
        assertThat(testBaoHiem.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testBaoHiem.getDoiTuong()).isEqualTo(DEFAULT_DOI_TUONG);
        assertThat(testBaoHiem.getBaoHiemThanhToan()).isEqualTo(DEFAULT_BAO_HIEM_THANH_TOAN);
    }

    @Test
    @Transactional
    void createBaoHiemWithExistingId() throws Exception {
        // Create the BaoHiem with an existing ID
        baoHiem.setId(1L);

        int databaseSizeBeforeCreate = baoHiemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaoHiemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBaoHiems() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        // Get all the baoHiemList
        restBaoHiemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baoHiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].maTheBHYT").value(hasItem(DEFAULT_MA_THE_BHYT)))
            .andExpect(jsonPath("$.[*].doiTuong").value(hasItem(DEFAULT_DOI_TUONG)))
            .andExpect(jsonPath("$.[*].baoHiemThanhToan").value(hasItem(DEFAULT_BAO_HIEM_THANH_TOAN)));
    }

    @Test
    @Transactional
    void getBaoHiem() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        // Get the baoHiem
        restBaoHiemMockMvc
            .perform(get(ENTITY_API_URL_ID, baoHiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(baoHiem.getId().intValue()))
            .andExpect(jsonPath("$.maTheBHYT").value(DEFAULT_MA_THE_BHYT))
            .andExpect(jsonPath("$.doiTuong").value(DEFAULT_DOI_TUONG))
            .andExpect(jsonPath("$.baoHiemThanhToan").value(DEFAULT_BAO_HIEM_THANH_TOAN));
    }

    @Test
    @Transactional
    void getNonExistingBaoHiem() throws Exception {
        // Get the baoHiem
        restBaoHiemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBaoHiem() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();

        // Update the baoHiem
        BaoHiem updatedBaoHiem = baoHiemRepository.findById(baoHiem.getId()).get();
        // Disconnect from session so that the updates on updatedBaoHiem are not directly saved in db
        em.detach(updatedBaoHiem);
        updatedBaoHiem.maTheBHYT(UPDATED_MA_THE_BHYT).doiTuong(UPDATED_DOI_TUONG).baoHiemThanhToan(UPDATED_BAO_HIEM_THANH_TOAN);

        restBaoHiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBaoHiem.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBaoHiem))
            )
            .andExpect(status().isOk());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
        BaoHiem testBaoHiem = baoHiemList.get(baoHiemList.size() - 1);
        assertThat(testBaoHiem.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testBaoHiem.getDoiTuong()).isEqualTo(UPDATED_DOI_TUONG);
        assertThat(testBaoHiem.getBaoHiemThanhToan()).isEqualTo(UPDATED_BAO_HIEM_THANH_TOAN);
    }

    @Test
    @Transactional
    void putNonExistingBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, baoHiem.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBaoHiemWithPatch() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();

        // Update the baoHiem using partial update
        BaoHiem partialUpdatedBaoHiem = new BaoHiem();
        partialUpdatedBaoHiem.setId(baoHiem.getId());

        partialUpdatedBaoHiem.baoHiemThanhToan(UPDATED_BAO_HIEM_THANH_TOAN);

        restBaoHiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBaoHiem.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaoHiem))
            )
            .andExpect(status().isOk());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
        BaoHiem testBaoHiem = baoHiemList.get(baoHiemList.size() - 1);
        assertThat(testBaoHiem.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testBaoHiem.getDoiTuong()).isEqualTo(DEFAULT_DOI_TUONG);
        assertThat(testBaoHiem.getBaoHiemThanhToan()).isEqualTo(UPDATED_BAO_HIEM_THANH_TOAN);
    }

    @Test
    @Transactional
    void fullUpdateBaoHiemWithPatch() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();

        // Update the baoHiem using partial update
        BaoHiem partialUpdatedBaoHiem = new BaoHiem();
        partialUpdatedBaoHiem.setId(baoHiem.getId());

        partialUpdatedBaoHiem.maTheBHYT(UPDATED_MA_THE_BHYT).doiTuong(UPDATED_DOI_TUONG).baoHiemThanhToan(UPDATED_BAO_HIEM_THANH_TOAN);

        restBaoHiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBaoHiem.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaoHiem))
            )
            .andExpect(status().isOk());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
        BaoHiem testBaoHiem = baoHiemList.get(baoHiemList.size() - 1);
        assertThat(testBaoHiem.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testBaoHiem.getDoiTuong()).isEqualTo(UPDATED_DOI_TUONG);
        assertThat(testBaoHiem.getBaoHiemThanhToan()).isEqualTo(UPDATED_BAO_HIEM_THANH_TOAN);
    }

    @Test
    @Transactional
    void patchNonExistingBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, baoHiem.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBaoHiem() throws Exception {
        int databaseSizeBeforeUpdate = baoHiemRepository.findAll().size();
        baoHiem.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoHiemMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoHiem))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BaoHiem in the database
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBaoHiem() throws Exception {
        // Initialize the database
        baoHiemRepository.saveAndFlush(baoHiem);

        int databaseSizeBeforeDelete = baoHiemRepository.findAll().size();

        // Delete the baoHiem
        restBaoHiemMockMvc
            .perform(delete(ENTITY_API_URL_ID, baoHiem.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BaoHiem> baoHiemList = baoHiemRepository.findAll();
        assertThat(baoHiemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
