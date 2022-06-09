package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PhieuSuatAn;
import com.mycompany.myapp.repository.PhieuSuatAnRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link PhieuSuatAnResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PhieuSuatAnResourceIT {

    private static final String DEFAULT_TEN_DV = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DV = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DV = "AAAAAAAAAA";
    private static final String UPDATED_MA_DV = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_THE_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_THE_BHYT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_T_G_SU_DUNG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_T_G_SU_DUNG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_T_G_CHI_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_T_G_CHI_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CHUAN_DOAN = "AAAAAAAAAA";
    private static final String UPDATED_CHUAN_DOAN = "BBBBBBBBBB";

    private static final String DEFAULT_CHUAN_DOAN_KT = "AAAAAAAAAA";
    private static final String UPDATED_CHUAN_DOAN_KT = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/phieu-suat-ans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PhieuSuatAnRepository phieuSuatAnRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhieuSuatAnMockMvc;

    private PhieuSuatAn phieuSuatAn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuSuatAn createEntity(EntityManager em) {
        PhieuSuatAn phieuSuatAn = new PhieuSuatAn()
            .tenDv(DEFAULT_TEN_DV)
            .maDV(DEFAULT_MA_DV)
            .maBN(DEFAULT_MA_BN)
            .maTheBHYT(DEFAULT_MA_THE_BHYT)
            .tGSuDung(DEFAULT_T_G_SU_DUNG)
            .tGChiDinh(DEFAULT_T_G_CHI_DINH)
            .chuanDoan(DEFAULT_CHUAN_DOAN)
            .chuanDoanKT(DEFAULT_CHUAN_DOAN_KT)
            .ghiChu(DEFAULT_GHI_CHU);
        return phieuSuatAn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuSuatAn createUpdatedEntity(EntityManager em) {
        PhieuSuatAn phieuSuatAn = new PhieuSuatAn()
            .tenDv(UPDATED_TEN_DV)
            .maDV(UPDATED_MA_DV)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .tGSuDung(UPDATED_T_G_SU_DUNG)
            .tGChiDinh(UPDATED_T_G_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanKT(UPDATED_CHUAN_DOAN_KT)
            .ghiChu(UPDATED_GHI_CHU);
        return phieuSuatAn;
    }

    @BeforeEach
    public void initTest() {
        phieuSuatAn = createEntity(em);
    }

    @Test
    @Transactional
    void createPhieuSuatAn() throws Exception {
        int databaseSizeBeforeCreate = phieuSuatAnRepository.findAll().size();
        // Create the PhieuSuatAn
        restPhieuSuatAnMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isCreated());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeCreate + 1);
        PhieuSuatAn testPhieuSuatAn = phieuSuatAnList.get(phieuSuatAnList.size() - 1);
        assertThat(testPhieuSuatAn.getTenDv()).isEqualTo(DEFAULT_TEN_DV);
        assertThat(testPhieuSuatAn.getMaDV()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testPhieuSuatAn.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testPhieuSuatAn.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testPhieuSuatAn.gettGSuDung()).isEqualTo(DEFAULT_T_G_SU_DUNG);
        assertThat(testPhieuSuatAn.gettGChiDinh()).isEqualTo(DEFAULT_T_G_CHI_DINH);
        assertThat(testPhieuSuatAn.getChuanDoan()).isEqualTo(DEFAULT_CHUAN_DOAN);
        assertThat(testPhieuSuatAn.getChuanDoanKT()).isEqualTo(DEFAULT_CHUAN_DOAN_KT);
        assertThat(testPhieuSuatAn.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void createPhieuSuatAnWithExistingId() throws Exception {
        // Create the PhieuSuatAn with an existing ID
        phieuSuatAn.setId(1L);

        int databaseSizeBeforeCreate = phieuSuatAnRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhieuSuatAnMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPhieuSuatAns() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        // Get all the phieuSuatAnList
        restPhieuSuatAnMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phieuSuatAn.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenDv").value(hasItem(DEFAULT_TEN_DV)))
            .andExpect(jsonPath("$.[*].maDV").value(hasItem(DEFAULT_MA_DV)))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].maTheBHYT").value(hasItem(DEFAULT_MA_THE_BHYT)))
            .andExpect(jsonPath("$.[*].tGSuDung").value(hasItem(sameInstant(DEFAULT_T_G_SU_DUNG))))
            .andExpect(jsonPath("$.[*].tGChiDinh").value(hasItem(sameInstant(DEFAULT_T_G_CHI_DINH))))
            .andExpect(jsonPath("$.[*].chuanDoan").value(hasItem(DEFAULT_CHUAN_DOAN)))
            .andExpect(jsonPath("$.[*].chuanDoanKT").value(hasItem(DEFAULT_CHUAN_DOAN_KT)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)));
    }

    @Test
    @Transactional
    void getPhieuSuatAn() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        // Get the phieuSuatAn
        restPhieuSuatAnMockMvc
            .perform(get(ENTITY_API_URL_ID, phieuSuatAn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phieuSuatAn.getId().intValue()))
            .andExpect(jsonPath("$.tenDv").value(DEFAULT_TEN_DV))
            .andExpect(jsonPath("$.maDV").value(DEFAULT_MA_DV))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.maTheBHYT").value(DEFAULT_MA_THE_BHYT))
            .andExpect(jsonPath("$.tGSuDung").value(sameInstant(DEFAULT_T_G_SU_DUNG)))
            .andExpect(jsonPath("$.tGChiDinh").value(sameInstant(DEFAULT_T_G_CHI_DINH)))
            .andExpect(jsonPath("$.chuanDoan").value(DEFAULT_CHUAN_DOAN))
            .andExpect(jsonPath("$.chuanDoanKT").value(DEFAULT_CHUAN_DOAN_KT))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU));
    }

    @Test
    @Transactional
    void getNonExistingPhieuSuatAn() throws Exception {
        // Get the phieuSuatAn
        restPhieuSuatAnMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPhieuSuatAn() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();

        // Update the phieuSuatAn
        PhieuSuatAn updatedPhieuSuatAn = phieuSuatAnRepository.findById(phieuSuatAn.getId()).get();
        // Disconnect from session so that the updates on updatedPhieuSuatAn are not directly saved in db
        em.detach(updatedPhieuSuatAn);
        updatedPhieuSuatAn
            .tenDv(UPDATED_TEN_DV)
            .maDV(UPDATED_MA_DV)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .tGSuDung(UPDATED_T_G_SU_DUNG)
            .tGChiDinh(UPDATED_T_G_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanKT(UPDATED_CHUAN_DOAN_KT)
            .ghiChu(UPDATED_GHI_CHU);

        restPhieuSuatAnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPhieuSuatAn.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPhieuSuatAn))
            )
            .andExpect(status().isOk());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
        PhieuSuatAn testPhieuSuatAn = phieuSuatAnList.get(phieuSuatAnList.size() - 1);
        assertThat(testPhieuSuatAn.getTenDv()).isEqualTo(UPDATED_TEN_DV);
        assertThat(testPhieuSuatAn.getMaDV()).isEqualTo(UPDATED_MA_DV);
        assertThat(testPhieuSuatAn.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testPhieuSuatAn.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testPhieuSuatAn.gettGSuDung()).isEqualTo(UPDATED_T_G_SU_DUNG);
        assertThat(testPhieuSuatAn.gettGChiDinh()).isEqualTo(UPDATED_T_G_CHI_DINH);
        assertThat(testPhieuSuatAn.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testPhieuSuatAn.getChuanDoanKT()).isEqualTo(UPDATED_CHUAN_DOAN_KT);
        assertThat(testPhieuSuatAn.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void putNonExistingPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phieuSuatAn.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePhieuSuatAnWithPatch() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();

        // Update the phieuSuatAn using partial update
        PhieuSuatAn partialUpdatedPhieuSuatAn = new PhieuSuatAn();
        partialUpdatedPhieuSuatAn.setId(phieuSuatAn.getId());

        partialUpdatedPhieuSuatAn
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .tGChiDinh(UPDATED_T_G_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .ghiChu(UPDATED_GHI_CHU);

        restPhieuSuatAnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhieuSuatAn.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPhieuSuatAn))
            )
            .andExpect(status().isOk());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
        PhieuSuatAn testPhieuSuatAn = phieuSuatAnList.get(phieuSuatAnList.size() - 1);
        assertThat(testPhieuSuatAn.getTenDv()).isEqualTo(DEFAULT_TEN_DV);
        assertThat(testPhieuSuatAn.getMaDV()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testPhieuSuatAn.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testPhieuSuatAn.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testPhieuSuatAn.gettGSuDung()).isEqualTo(DEFAULT_T_G_SU_DUNG);
        assertThat(testPhieuSuatAn.gettGChiDinh()).isEqualTo(UPDATED_T_G_CHI_DINH);
        assertThat(testPhieuSuatAn.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testPhieuSuatAn.getChuanDoanKT()).isEqualTo(DEFAULT_CHUAN_DOAN_KT);
        assertThat(testPhieuSuatAn.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void fullUpdatePhieuSuatAnWithPatch() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();

        // Update the phieuSuatAn using partial update
        PhieuSuatAn partialUpdatedPhieuSuatAn = new PhieuSuatAn();
        partialUpdatedPhieuSuatAn.setId(phieuSuatAn.getId());

        partialUpdatedPhieuSuatAn
            .tenDv(UPDATED_TEN_DV)
            .maDV(UPDATED_MA_DV)
            .maBN(UPDATED_MA_BN)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .tGSuDung(UPDATED_T_G_SU_DUNG)
            .tGChiDinh(UPDATED_T_G_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanKT(UPDATED_CHUAN_DOAN_KT)
            .ghiChu(UPDATED_GHI_CHU);

        restPhieuSuatAnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhieuSuatAn.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPhieuSuatAn))
            )
            .andExpect(status().isOk());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
        PhieuSuatAn testPhieuSuatAn = phieuSuatAnList.get(phieuSuatAnList.size() - 1);
        assertThat(testPhieuSuatAn.getTenDv()).isEqualTo(UPDATED_TEN_DV);
        assertThat(testPhieuSuatAn.getMaDV()).isEqualTo(UPDATED_MA_DV);
        assertThat(testPhieuSuatAn.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testPhieuSuatAn.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testPhieuSuatAn.gettGSuDung()).isEqualTo(UPDATED_T_G_SU_DUNG);
        assertThat(testPhieuSuatAn.gettGChiDinh()).isEqualTo(UPDATED_T_G_CHI_DINH);
        assertThat(testPhieuSuatAn.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testPhieuSuatAn.getChuanDoanKT()).isEqualTo(UPDATED_CHUAN_DOAN_KT);
        assertThat(testPhieuSuatAn.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void patchNonExistingPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, phieuSuatAn.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPhieuSuatAn() throws Exception {
        int databaseSizeBeforeUpdate = phieuSuatAnRepository.findAll().size();
        phieuSuatAn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhieuSuatAnMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(phieuSuatAn))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhieuSuatAn in the database
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePhieuSuatAn() throws Exception {
        // Initialize the database
        phieuSuatAnRepository.saveAndFlush(phieuSuatAn);

        int databaseSizeBeforeDelete = phieuSuatAnRepository.findAll().size();

        // Delete the phieuSuatAn
        restPhieuSuatAnMockMvc
            .perform(delete(ENTITY_API_URL_ID, phieuSuatAn.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PhieuSuatAn> phieuSuatAnList = phieuSuatAnRepository.findAll();
        assertThat(phieuSuatAnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
