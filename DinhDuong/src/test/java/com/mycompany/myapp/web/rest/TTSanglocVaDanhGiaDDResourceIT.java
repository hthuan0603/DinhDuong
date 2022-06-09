package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TTSanglocVaDanhGiaDD;
import com.mycompany.myapp.repository.TTSanglocVaDanhGiaDDRepository;
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
 * Integration tests for the {@link TTSanglocVaDanhGiaDDResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TTSanglocVaDanhGiaDDResourceIT {

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MANG_THAI = false;
    private static final Boolean UPDATED_MANG_THAI = true;

    private static final Float DEFAULT_B_MI = 1F;
    private static final Float UPDATED_B_MI = 2F;

    private static final Integer DEFAULT_DANH_GIA_BMI = 1;
    private static final Integer UPDATED_DANH_GIA_BMI = 2;

    private static final Float DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG = 1F;
    private static final Float UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG = 2F;

    private static final Integer DEFAULT_DANH_GIA_CAN_NANG = 1;
    private static final Integer UPDATED_DANH_GIA_CAN_NANG = 2;

    private static final String DEFAULT_AN_UONG_TRONG_TUAN = "AAAAAAAAAA";
    private static final String UPDATED_AN_UONG_TRONG_TUAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_AN_UONG = 1;
    private static final Integer UPDATED_DANH_GIA_AN_UONG = 2;

    private static final ZonedDateTime DEFAULT_THOI_GIAN_CHI_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_THOI_GIAN_CHI_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_NGUY_CO_SDD = false;
    private static final Boolean UPDATED_NGUY_CO_SDD = true;

    private static final String DEFAULT_CHE_DO_AN = "AAAAAAAAAA";
    private static final String UPDATED_CHE_DO_AN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tt-sangloc-va-danh-gia-dds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TTSanglocVaDanhGiaDDRepository tTSanglocVaDanhGiaDDRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTTSanglocVaDanhGiaDDMockMvc;

    private TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TTSanglocVaDanhGiaDD createEntity(EntityManager em) {
        TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD = new TTSanglocVaDanhGiaDD()
            .maBN(DEFAULT_MA_BN)
            .mangThai(DEFAULT_MANG_THAI)
            .bMI(DEFAULT_B_MI)
            .danhGiaBMI(DEFAULT_DANH_GIA_BMI)
            .phanTramCanNangTrong3Thang(DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG)
            .danhGiaCanNang(DEFAULT_DANH_GIA_CAN_NANG)
            .anUongTrongTuan(DEFAULT_AN_UONG_TRONG_TUAN)
            .danhGiaAnUong(DEFAULT_DANH_GIA_AN_UONG)
            .thoiGianChiDinh(DEFAULT_THOI_GIAN_CHI_DINH)
            .nguyCoSDD(DEFAULT_NGUY_CO_SDD)
            .cheDoAn(DEFAULT_CHE_DO_AN);
        return tTSanglocVaDanhGiaDD;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TTSanglocVaDanhGiaDD createUpdatedEntity(EntityManager em) {
        TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD = new TTSanglocVaDanhGiaDD()
            .maBN(UPDATED_MA_BN)
            .mangThai(UPDATED_MANG_THAI)
            .bMI(UPDATED_B_MI)
            .danhGiaBMI(UPDATED_DANH_GIA_BMI)
            .phanTramCanNangTrong3Thang(UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG)
            .danhGiaCanNang(UPDATED_DANH_GIA_CAN_NANG)
            .anUongTrongTuan(UPDATED_AN_UONG_TRONG_TUAN)
            .danhGiaAnUong(UPDATED_DANH_GIA_AN_UONG)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .nguyCoSDD(UPDATED_NGUY_CO_SDD)
            .cheDoAn(UPDATED_CHE_DO_AN);
        return tTSanglocVaDanhGiaDD;
    }

    @BeforeEach
    public void initTest() {
        tTSanglocVaDanhGiaDD = createEntity(em);
    }

    @Test
    @Transactional
    void createTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeCreate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        // Create the TTSanglocVaDanhGiaDD
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isCreated());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeCreate + 1);
        TTSanglocVaDanhGiaDD testTTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDList.get(tTSanglocVaDanhGiaDDList.size() - 1);
        assertThat(testTTSanglocVaDanhGiaDD.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testTTSanglocVaDanhGiaDD.getMangThai()).isEqualTo(DEFAULT_MANG_THAI);
        assertThat(testTTSanglocVaDanhGiaDD.getbMI()).isEqualTo(DEFAULT_B_MI);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaBMI()).isEqualTo(DEFAULT_DANH_GIA_BMI);
        assertThat(testTTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang()).isEqualTo(DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaCanNang()).isEqualTo(DEFAULT_DANH_GIA_CAN_NANG);
        assertThat(testTTSanglocVaDanhGiaDD.getAnUongTrongTuan()).isEqualTo(DEFAULT_AN_UONG_TRONG_TUAN);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaAnUong()).isEqualTo(DEFAULT_DANH_GIA_AN_UONG);
        assertThat(testTTSanglocVaDanhGiaDD.getThoiGianChiDinh()).isEqualTo(DEFAULT_THOI_GIAN_CHI_DINH);
        assertThat(testTTSanglocVaDanhGiaDD.getNguyCoSDD()).isEqualTo(DEFAULT_NGUY_CO_SDD);
        assertThat(testTTSanglocVaDanhGiaDD.getCheDoAn()).isEqualTo(DEFAULT_CHE_DO_AN);
    }

    @Test
    @Transactional
    void createTTSanglocVaDanhGiaDDWithExistingId() throws Exception {
        // Create the TTSanglocVaDanhGiaDD with an existing ID
        tTSanglocVaDanhGiaDD.setId(1L);

        int databaseSizeBeforeCreate = tTSanglocVaDanhGiaDDRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTTSanglocVaDanhGiaDDS() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        // Get all the tTSanglocVaDanhGiaDDList
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tTSanglocVaDanhGiaDD.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].mangThai").value(hasItem(DEFAULT_MANG_THAI.booleanValue())))
            .andExpect(jsonPath("$.[*].bMI").value(hasItem(DEFAULT_B_MI.doubleValue())))
            .andExpect(jsonPath("$.[*].danhGiaBMI").value(hasItem(DEFAULT_DANH_GIA_BMI)))
            .andExpect(jsonPath("$.[*].phanTramCanNangTrong3Thang").value(hasItem(DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG.doubleValue())))
            .andExpect(jsonPath("$.[*].danhGiaCanNang").value(hasItem(DEFAULT_DANH_GIA_CAN_NANG)))
            .andExpect(jsonPath("$.[*].anUongTrongTuan").value(hasItem(DEFAULT_AN_UONG_TRONG_TUAN)))
            .andExpect(jsonPath("$.[*].danhGiaAnUong").value(hasItem(DEFAULT_DANH_GIA_AN_UONG)))
            .andExpect(jsonPath("$.[*].thoiGianChiDinh").value(hasItem(sameInstant(DEFAULT_THOI_GIAN_CHI_DINH))))
            .andExpect(jsonPath("$.[*].nguyCoSDD").value(hasItem(DEFAULT_NGUY_CO_SDD.booleanValue())))
            .andExpect(jsonPath("$.[*].cheDoAn").value(hasItem(DEFAULT_CHE_DO_AN)));
    }

    @Test
    @Transactional
    void getTTSanglocVaDanhGiaDD() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        // Get the tTSanglocVaDanhGiaDD
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(get(ENTITY_API_URL_ID, tTSanglocVaDanhGiaDD.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tTSanglocVaDanhGiaDD.getId().intValue()))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.mangThai").value(DEFAULT_MANG_THAI.booleanValue()))
            .andExpect(jsonPath("$.bMI").value(DEFAULT_B_MI.doubleValue()))
            .andExpect(jsonPath("$.danhGiaBMI").value(DEFAULT_DANH_GIA_BMI))
            .andExpect(jsonPath("$.phanTramCanNangTrong3Thang").value(DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG.doubleValue()))
            .andExpect(jsonPath("$.danhGiaCanNang").value(DEFAULT_DANH_GIA_CAN_NANG))
            .andExpect(jsonPath("$.anUongTrongTuan").value(DEFAULT_AN_UONG_TRONG_TUAN))
            .andExpect(jsonPath("$.danhGiaAnUong").value(DEFAULT_DANH_GIA_AN_UONG))
            .andExpect(jsonPath("$.thoiGianChiDinh").value(sameInstant(DEFAULT_THOI_GIAN_CHI_DINH)))
            .andExpect(jsonPath("$.nguyCoSDD").value(DEFAULT_NGUY_CO_SDD.booleanValue()))
            .andExpect(jsonPath("$.cheDoAn").value(DEFAULT_CHE_DO_AN));
    }

    @Test
    @Transactional
    void getNonExistingTTSanglocVaDanhGiaDD() throws Exception {
        // Get the tTSanglocVaDanhGiaDD
        restTTSanglocVaDanhGiaDDMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTTSanglocVaDanhGiaDD() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();

        // Update the tTSanglocVaDanhGiaDD
        TTSanglocVaDanhGiaDD updatedTTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDRepository.findById(tTSanglocVaDanhGiaDD.getId()).get();
        // Disconnect from session so that the updates on updatedTTSanglocVaDanhGiaDD are not directly saved in db
        em.detach(updatedTTSanglocVaDanhGiaDD);
        updatedTTSanglocVaDanhGiaDD
            .maBN(UPDATED_MA_BN)
            .mangThai(UPDATED_MANG_THAI)
            .bMI(UPDATED_B_MI)
            .danhGiaBMI(UPDATED_DANH_GIA_BMI)
            .phanTramCanNangTrong3Thang(UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG)
            .danhGiaCanNang(UPDATED_DANH_GIA_CAN_NANG)
            .anUongTrongTuan(UPDATED_AN_UONG_TRONG_TUAN)
            .danhGiaAnUong(UPDATED_DANH_GIA_AN_UONG)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .nguyCoSDD(UPDATED_NGUY_CO_SDD)
            .cheDoAn(UPDATED_CHE_DO_AN);

        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTTSanglocVaDanhGiaDD.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isOk());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
        TTSanglocVaDanhGiaDD testTTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDList.get(tTSanglocVaDanhGiaDDList.size() - 1);
        assertThat(testTTSanglocVaDanhGiaDD.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testTTSanglocVaDanhGiaDD.getMangThai()).isEqualTo(UPDATED_MANG_THAI);
        assertThat(testTTSanglocVaDanhGiaDD.getbMI()).isEqualTo(UPDATED_B_MI);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaBMI()).isEqualTo(UPDATED_DANH_GIA_BMI);
        assertThat(testTTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang()).isEqualTo(UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaCanNang()).isEqualTo(UPDATED_DANH_GIA_CAN_NANG);
        assertThat(testTTSanglocVaDanhGiaDD.getAnUongTrongTuan()).isEqualTo(UPDATED_AN_UONG_TRONG_TUAN);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaAnUong()).isEqualTo(UPDATED_DANH_GIA_AN_UONG);
        assertThat(testTTSanglocVaDanhGiaDD.getThoiGianChiDinh()).isEqualTo(UPDATED_THOI_GIAN_CHI_DINH);
        assertThat(testTTSanglocVaDanhGiaDD.getNguyCoSDD()).isEqualTo(UPDATED_NGUY_CO_SDD);
        assertThat(testTTSanglocVaDanhGiaDD.getCheDoAn()).isEqualTo(UPDATED_CHE_DO_AN);
    }

    @Test
    @Transactional
    void putNonExistingTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tTSanglocVaDanhGiaDD.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTTSanglocVaDanhGiaDDWithPatch() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();

        // Update the tTSanglocVaDanhGiaDD using partial update
        TTSanglocVaDanhGiaDD partialUpdatedTTSanglocVaDanhGiaDD = new TTSanglocVaDanhGiaDD();
        partialUpdatedTTSanglocVaDanhGiaDD.setId(tTSanglocVaDanhGiaDD.getId());

        partialUpdatedTTSanglocVaDanhGiaDD
            .mangThai(UPDATED_MANG_THAI)
            .danhGiaBMI(UPDATED_DANH_GIA_BMI)
            .danhGiaCanNang(UPDATED_DANH_GIA_CAN_NANG)
            .anUongTrongTuan(UPDATED_AN_UONG_TRONG_TUAN)
            .cheDoAn(UPDATED_CHE_DO_AN);

        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTTSanglocVaDanhGiaDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isOk());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
        TTSanglocVaDanhGiaDD testTTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDList.get(tTSanglocVaDanhGiaDDList.size() - 1);
        assertThat(testTTSanglocVaDanhGiaDD.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testTTSanglocVaDanhGiaDD.getMangThai()).isEqualTo(UPDATED_MANG_THAI);
        assertThat(testTTSanglocVaDanhGiaDD.getbMI()).isEqualTo(DEFAULT_B_MI);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaBMI()).isEqualTo(UPDATED_DANH_GIA_BMI);
        assertThat(testTTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang()).isEqualTo(DEFAULT_PHAN_TRAM_CAN_NANG_TRONG_3_THANG);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaCanNang()).isEqualTo(UPDATED_DANH_GIA_CAN_NANG);
        assertThat(testTTSanglocVaDanhGiaDD.getAnUongTrongTuan()).isEqualTo(UPDATED_AN_UONG_TRONG_TUAN);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaAnUong()).isEqualTo(DEFAULT_DANH_GIA_AN_UONG);
        assertThat(testTTSanglocVaDanhGiaDD.getThoiGianChiDinh()).isEqualTo(DEFAULT_THOI_GIAN_CHI_DINH);
        assertThat(testTTSanglocVaDanhGiaDD.getNguyCoSDD()).isEqualTo(DEFAULT_NGUY_CO_SDD);
        assertThat(testTTSanglocVaDanhGiaDD.getCheDoAn()).isEqualTo(UPDATED_CHE_DO_AN);
    }

    @Test
    @Transactional
    void fullUpdateTTSanglocVaDanhGiaDDWithPatch() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();

        // Update the tTSanglocVaDanhGiaDD using partial update
        TTSanglocVaDanhGiaDD partialUpdatedTTSanglocVaDanhGiaDD = new TTSanglocVaDanhGiaDD();
        partialUpdatedTTSanglocVaDanhGiaDD.setId(tTSanglocVaDanhGiaDD.getId());

        partialUpdatedTTSanglocVaDanhGiaDD
            .maBN(UPDATED_MA_BN)
            .mangThai(UPDATED_MANG_THAI)
            .bMI(UPDATED_B_MI)
            .danhGiaBMI(UPDATED_DANH_GIA_BMI)
            .phanTramCanNangTrong3Thang(UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG)
            .danhGiaCanNang(UPDATED_DANH_GIA_CAN_NANG)
            .anUongTrongTuan(UPDATED_AN_UONG_TRONG_TUAN)
            .danhGiaAnUong(UPDATED_DANH_GIA_AN_UONG)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .nguyCoSDD(UPDATED_NGUY_CO_SDD)
            .cheDoAn(UPDATED_CHE_DO_AN);

        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTTSanglocVaDanhGiaDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isOk());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
        TTSanglocVaDanhGiaDD testTTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDDList.get(tTSanglocVaDanhGiaDDList.size() - 1);
        assertThat(testTTSanglocVaDanhGiaDD.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testTTSanglocVaDanhGiaDD.getMangThai()).isEqualTo(UPDATED_MANG_THAI);
        assertThat(testTTSanglocVaDanhGiaDD.getbMI()).isEqualTo(UPDATED_B_MI);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaBMI()).isEqualTo(UPDATED_DANH_GIA_BMI);
        assertThat(testTTSanglocVaDanhGiaDD.getPhanTramCanNangTrong3Thang()).isEqualTo(UPDATED_PHAN_TRAM_CAN_NANG_TRONG_3_THANG);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaCanNang()).isEqualTo(UPDATED_DANH_GIA_CAN_NANG);
        assertThat(testTTSanglocVaDanhGiaDD.getAnUongTrongTuan()).isEqualTo(UPDATED_AN_UONG_TRONG_TUAN);
        assertThat(testTTSanglocVaDanhGiaDD.getDanhGiaAnUong()).isEqualTo(UPDATED_DANH_GIA_AN_UONG);
        assertThat(testTTSanglocVaDanhGiaDD.getThoiGianChiDinh()).isEqualTo(UPDATED_THOI_GIAN_CHI_DINH);
        assertThat(testTTSanglocVaDanhGiaDD.getNguyCoSDD()).isEqualTo(UPDATED_NGUY_CO_SDD);
        assertThat(testTTSanglocVaDanhGiaDD.getCheDoAn()).isEqualTo(UPDATED_CHE_DO_AN);
    }

    @Test
    @Transactional
    void patchNonExistingTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tTSanglocVaDanhGiaDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTTSanglocVaDanhGiaDD() throws Exception {
        int databaseSizeBeforeUpdate = tTSanglocVaDanhGiaDDRepository.findAll().size();
        tTSanglocVaDanhGiaDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tTSanglocVaDanhGiaDD))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TTSanglocVaDanhGiaDD in the database
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTTSanglocVaDanhGiaDD() throws Exception {
        // Initialize the database
        tTSanglocVaDanhGiaDDRepository.saveAndFlush(tTSanglocVaDanhGiaDD);

        int databaseSizeBeforeDelete = tTSanglocVaDanhGiaDDRepository.findAll().size();

        // Delete the tTSanglocVaDanhGiaDD
        restTTSanglocVaDanhGiaDDMockMvc
            .perform(delete(ENTITY_API_URL_ID, tTSanglocVaDanhGiaDD.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TTSanglocVaDanhGiaDD> tTSanglocVaDanhGiaDDList = tTSanglocVaDanhGiaDDRepository.findAll();
        assertThat(tTSanglocVaDanhGiaDDList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
