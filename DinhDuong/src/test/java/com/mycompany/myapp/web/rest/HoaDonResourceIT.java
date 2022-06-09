package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.HoaDon;
import com.mycompany.myapp.repository.HoaDonRepository;
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
 * Integration tests for the {@link HoaDonResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HoaDonResourceIT {

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_THE_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_THE_BHYT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DV = "AAAAAAAAAA";
    private static final String UPDATED_MA_DV = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAM_UNG = 1;
    private static final Integer UPDATED_TAM_UNG = 2;

    private static final Integer DEFAULT_DA_NOP = 1;
    private static final Integer UPDATED_DA_NOP = 2;

    private static final Integer DEFAULT_TONG = 1;
    private static final Integer UPDATED_TONG = 2;

    private static final String ENTITY_API_URL = "/api/hoa-dons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHoaDonMockMvc;

    private HoaDon hoaDon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoaDon createEntity(EntityManager em) {
        HoaDon hoaDon = new HoaDon()
            .noiDung(DEFAULT_NOI_DUNG)
            .maBN(DEFAULT_MA_BN)
            .maTheBHYT(DEFAULT_MA_THE_BHYT)
            .maDV(DEFAULT_MA_DV)
            .tamUng(DEFAULT_TAM_UNG)
            .daNop(DEFAULT_DA_NOP)
            .tong(DEFAULT_TONG);
        return hoaDon;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoaDon createUpdatedEntity(EntityManager em) {
        HoaDon hoaDon = new HoaDon()
            .noiDung(UPDATED_NOI_DUNG)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .maDV(UPDATED_MA_DV)
            .tamUng(UPDATED_TAM_UNG)
            .daNop(UPDATED_DA_NOP)
            .tong(UPDATED_TONG);
        return hoaDon;
    }

    @BeforeEach
    public void initTest() {
        hoaDon = createEntity(em);
    }

    @Test
    @Transactional
    void createHoaDon() throws Exception {
        int databaseSizeBeforeCreate = hoaDonRepository.findAll().size();
        // Create the HoaDon
        restHoaDonMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isCreated());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeCreate + 1);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testHoaDon.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testHoaDon.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testHoaDon.getMaDV()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testHoaDon.getTamUng()).isEqualTo(DEFAULT_TAM_UNG);
        assertThat(testHoaDon.getDaNop()).isEqualTo(DEFAULT_DA_NOP);
        assertThat(testHoaDon.getTong()).isEqualTo(DEFAULT_TONG);
    }

    @Test
    @Transactional
    void createHoaDonWithExistingId() throws Exception {
        // Create the HoaDon with an existing ID
        hoaDon.setId(1L);

        int databaseSizeBeforeCreate = hoaDonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoaDonMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHoaDons() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        // Get all the hoaDonList
        restHoaDonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoaDon.getId().intValue())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].maTheBHYT").value(hasItem(DEFAULT_MA_THE_BHYT)))
            .andExpect(jsonPath("$.[*].maDV").value(hasItem(DEFAULT_MA_DV)))
            .andExpect(jsonPath("$.[*].tamUng").value(hasItem(DEFAULT_TAM_UNG)))
            .andExpect(jsonPath("$.[*].daNop").value(hasItem(DEFAULT_DA_NOP)))
            .andExpect(jsonPath("$.[*].tong").value(hasItem(DEFAULT_TONG)));
    }

    @Test
    @Transactional
    void getHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        // Get the hoaDon
        restHoaDonMockMvc
            .perform(get(ENTITY_API_URL_ID, hoaDon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hoaDon.getId().intValue()))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.maTheBHYT").value(DEFAULT_MA_THE_BHYT))
            .andExpect(jsonPath("$.maDV").value(DEFAULT_MA_DV))
            .andExpect(jsonPath("$.tamUng").value(DEFAULT_TAM_UNG))
            .andExpect(jsonPath("$.daNop").value(DEFAULT_DA_NOP))
            .andExpect(jsonPath("$.tong").value(DEFAULT_TONG));
    }

    @Test
    @Transactional
    void getNonExistingHoaDon() throws Exception {
        // Get the hoaDon
        restHoaDonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();

        // Update the hoaDon
        HoaDon updatedHoaDon = hoaDonRepository.findById(hoaDon.getId()).get();
        // Disconnect from session so that the updates on updatedHoaDon are not directly saved in db
        em.detach(updatedHoaDon);
        updatedHoaDon
            .noiDung(UPDATED_NOI_DUNG)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .maDV(UPDATED_MA_DV)
            .tamUng(UPDATED_TAM_UNG)
            .daNop(UPDATED_DA_NOP)
            .tong(UPDATED_TONG);

        restHoaDonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHoaDon.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHoaDon))
            )
            .andExpect(status().isOk());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testHoaDon.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testHoaDon.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testHoaDon.getMaDV()).isEqualTo(UPDATED_MA_DV);
        assertThat(testHoaDon.getTamUng()).isEqualTo(UPDATED_TAM_UNG);
        assertThat(testHoaDon.getDaNop()).isEqualTo(UPDATED_DA_NOP);
        assertThat(testHoaDon.getTong()).isEqualTo(UPDATED_TONG);
    }

    @Test
    @Transactional
    void putNonExistingHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hoaDon.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHoaDonWithPatch() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();

        // Update the hoaDon using partial update
        HoaDon partialUpdatedHoaDon = new HoaDon();
        partialUpdatedHoaDon.setId(hoaDon.getId());

        partialUpdatedHoaDon.maBN(UPDATED_MA_BN).maTheBHYT(UPDATED_MA_THE_BHYT).tamUng(UPDATED_TAM_UNG).tong(UPDATED_TONG);

        restHoaDonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoaDon.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoaDon))
            )
            .andExpect(status().isOk());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testHoaDon.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testHoaDon.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testHoaDon.getMaDV()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testHoaDon.getTamUng()).isEqualTo(UPDATED_TAM_UNG);
        assertThat(testHoaDon.getDaNop()).isEqualTo(DEFAULT_DA_NOP);
        assertThat(testHoaDon.getTong()).isEqualTo(UPDATED_TONG);
    }

    @Test
    @Transactional
    void fullUpdateHoaDonWithPatch() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();

        // Update the hoaDon using partial update
        HoaDon partialUpdatedHoaDon = new HoaDon();
        partialUpdatedHoaDon.setId(hoaDon.getId());

        partialUpdatedHoaDon
            .noiDung(UPDATED_NOI_DUNG)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .maDV(UPDATED_MA_DV)
            .tamUng(UPDATED_TAM_UNG)
            .daNop(UPDATED_DA_NOP)
            .tong(UPDATED_TONG);

        restHoaDonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoaDon.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoaDon))
            )
            .andExpect(status().isOk());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
        HoaDon testHoaDon = hoaDonList.get(hoaDonList.size() - 1);
        assertThat(testHoaDon.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testHoaDon.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testHoaDon.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testHoaDon.getMaDV()).isEqualTo(UPDATED_MA_DV);
        assertThat(testHoaDon.getTamUng()).isEqualTo(UPDATED_TAM_UNG);
        assertThat(testHoaDon.getDaNop()).isEqualTo(UPDATED_DA_NOP);
        assertThat(testHoaDon.getTong()).isEqualTo(UPDATED_TONG);
    }

    @Test
    @Transactional
    void patchNonExistingHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hoaDon.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHoaDon() throws Exception {
        int databaseSizeBeforeUpdate = hoaDonRepository.findAll().size();
        hoaDon.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoaDonMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoaDon))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoaDon in the database
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHoaDon() throws Exception {
        // Initialize the database
        hoaDonRepository.saveAndFlush(hoaDon);

        int databaseSizeBeforeDelete = hoaDonRepository.findAll().size();

        // Delete the hoaDon
        restHoaDonMockMvc
            .perform(delete(ENTITY_API_URL_ID, hoaDon.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        assertThat(hoaDonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
