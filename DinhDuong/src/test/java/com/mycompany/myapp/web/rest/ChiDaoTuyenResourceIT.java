package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.mapper.ChiDaoTuyenMapper;
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
 * Integration tests for the {@link ChiDaoTuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChiDaoTuyenResourceIT {

    private static final Integer DEFAULT_MA_CDT = 1;
    private static final Integer UPDATED_MA_CDT = 2;

    private static final Integer DEFAULT_SO_QUYET_DINH = 1;
    private static final Integer UPDATED_SO_QUYET_DINH = 2;

    private static final ZonedDateTime DEFAULT_NGAY_QUYET_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_QUYET_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SO_HD = 1;
    private static final Integer UPDATED_SO_HD = 2;

    private static final ZonedDateTime DEFAULT_NGAY_HD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_HD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LY_DO_CT = "AAAAAAAAAA";
    private static final String UPDATED_LY_DO_CT = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_CONG_TAC = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CONG_TAC = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_BAT_DAU = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_BAT_DAU = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_KET_THUC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_KET_THUC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOI_DUNG_HO_TRO = 1;
    private static final Integer UPDATED_NOI_DUNG_HO_TRO = 2;

    private static final Integer DEFAULT_BAO_CAO_TAI_CHINH = 1;
    private static final Integer UPDATED_BAO_CAO_TAI_CHINH = 2;

    private static final String ENTITY_API_URL = "/api/chi-dao-tuyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChiDaoTuyenRepository chiDaoTuyenRepository;

    @Autowired
    private ChiDaoTuyenMapper chiDaoTuyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiDaoTuyenMockMvc;

    private ChiDaoTuyen chiDaoTuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiDaoTuyen createEntity(EntityManager em) {
        ChiDaoTuyen chiDaoTuyen = new ChiDaoTuyen()
            .maCdt(DEFAULT_MA_CDT)
            .soQuyetDinh(DEFAULT_SO_QUYET_DINH)
            .ngayQuyetDinh(DEFAULT_NGAY_QUYET_DINH)
            .soHD(DEFAULT_SO_HD)
            .ngayHD(DEFAULT_NGAY_HD)
            .lyDoCT(DEFAULT_LY_DO_CT)
            .noiDung(DEFAULT_NOI_DUNG)
            .noiCongTac(DEFAULT_NOI_CONG_TAC)
            .ngayBatDau(DEFAULT_NGAY_BAT_DAU)
            .ngayKetThuc(DEFAULT_NGAY_KET_THUC)
            .ghiChu(DEFAULT_GHI_CHU)
            .noiDungHoTro(DEFAULT_NOI_DUNG_HO_TRO)
            .baoCaoTaiChinh(DEFAULT_BAO_CAO_TAI_CHINH);
        return chiDaoTuyen;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiDaoTuyen createUpdatedEntity(EntityManager em) {
        ChiDaoTuyen chiDaoTuyen = new ChiDaoTuyen()
            .maCdt(UPDATED_MA_CDT)
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .noiDungHoTro(UPDATED_NOI_DUNG_HO_TRO)
            .baoCaoTaiChinh(UPDATED_BAO_CAO_TAI_CHINH);
        return chiDaoTuyen;
    }

    @BeforeEach
    public void initTest() {
        chiDaoTuyen = createEntity(em);
    }

    @Test
    @Transactional
    void createChiDaoTuyen() throws Exception {
        int databaseSizeBeforeCreate = chiDaoTuyenRepository.findAll().size();
        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);
        restChiDaoTuyenMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeCreate + 1);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getMaCdt()).isEqualTo(DEFAULT_MA_CDT);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(DEFAULT_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(DEFAULT_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(DEFAULT_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(DEFAULT_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(DEFAULT_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(DEFAULT_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(DEFAULT_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(DEFAULT_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testChiDaoTuyen.getNoiDungHoTro()).isEqualTo(DEFAULT_NOI_DUNG_HO_TRO);
        assertThat(testChiDaoTuyen.getBaoCaoTaiChinh()).isEqualTo(DEFAULT_BAO_CAO_TAI_CHINH);
    }

    @Test
    @Transactional
    void createChiDaoTuyenWithExistingId() throws Exception {
        // Create the ChiDaoTuyen with an existing ID
        chiDaoTuyen.setId(1L);
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        int databaseSizeBeforeCreate = chiDaoTuyenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiDaoTuyenMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChiDaoTuyens() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get all the chiDaoTuyenList
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiDaoTuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCdt").value(hasItem(DEFAULT_MA_CDT)))
            .andExpect(jsonPath("$.[*].soQuyetDinh").value(hasItem(DEFAULT_SO_QUYET_DINH)))
            .andExpect(jsonPath("$.[*].ngayQuyetDinh").value(hasItem(sameInstant(DEFAULT_NGAY_QUYET_DINH))))
            .andExpect(jsonPath("$.[*].soHD").value(hasItem(DEFAULT_SO_HD)))
            .andExpect(jsonPath("$.[*].ngayHD").value(hasItem(sameInstant(DEFAULT_NGAY_HD))))
            .andExpect(jsonPath("$.[*].lyDoCT").value(hasItem(DEFAULT_LY_DO_CT)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].noiCongTac").value(hasItem(DEFAULT_NOI_CONG_TAC)))
            .andExpect(jsonPath("$.[*].ngayBatDau").value(hasItem(sameInstant(DEFAULT_NGAY_BAT_DAU))))
            .andExpect(jsonPath("$.[*].ngayKetThuc").value(hasItem(sameInstant(DEFAULT_NGAY_KET_THUC))))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].noiDungHoTro").value(hasItem(DEFAULT_NOI_DUNG_HO_TRO)))
            .andExpect(jsonPath("$.[*].baoCaoTaiChinh").value(hasItem(DEFAULT_BAO_CAO_TAI_CHINH)));
    }

    @Test
    @Transactional
    void getChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        // Get the chiDaoTuyen
        restChiDaoTuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, chiDaoTuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chiDaoTuyen.getId().intValue()))
            .andExpect(jsonPath("$.maCdt").value(DEFAULT_MA_CDT))
            .andExpect(jsonPath("$.soQuyetDinh").value(DEFAULT_SO_QUYET_DINH))
            .andExpect(jsonPath("$.ngayQuyetDinh").value(sameInstant(DEFAULT_NGAY_QUYET_DINH)))
            .andExpect(jsonPath("$.soHD").value(DEFAULT_SO_HD))
            .andExpect(jsonPath("$.ngayHD").value(sameInstant(DEFAULT_NGAY_HD)))
            .andExpect(jsonPath("$.lyDoCT").value(DEFAULT_LY_DO_CT))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.noiCongTac").value(DEFAULT_NOI_CONG_TAC))
            .andExpect(jsonPath("$.ngayBatDau").value(sameInstant(DEFAULT_NGAY_BAT_DAU)))
            .andExpect(jsonPath("$.ngayKetThuc").value(sameInstant(DEFAULT_NGAY_KET_THUC)))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.noiDungHoTro").value(DEFAULT_NOI_DUNG_HO_TRO))
            .andExpect(jsonPath("$.baoCaoTaiChinh").value(DEFAULT_BAO_CAO_TAI_CHINH));
    }

    @Test
    @Transactional
    void getNonExistingChiDaoTuyen() throws Exception {
        // Get the chiDaoTuyen
        restChiDaoTuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen
        ChiDaoTuyen updatedChiDaoTuyen = chiDaoTuyenRepository.findById(chiDaoTuyen.getId()).get();
        // Disconnect from session so that the updates on updatedChiDaoTuyen are not directly saved in db
        em.detach(updatedChiDaoTuyen);
        updatedChiDaoTuyen
            .maCdt(UPDATED_MA_CDT)
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .noiDungHoTro(UPDATED_NOI_DUNG_HO_TRO)
            .baoCaoTaiChinh(UPDATED_BAO_CAO_TAI_CHINH);
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(updatedChiDaoTuyen);

        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getMaCdt()).isEqualTo(UPDATED_MA_CDT);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(UPDATED_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(UPDATED_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(UPDATED_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(UPDATED_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testChiDaoTuyen.getNoiDungHoTro()).isEqualTo(UPDATED_NOI_DUNG_HO_TRO);
        assertThat(testChiDaoTuyen.getBaoCaoTaiChinh()).isEqualTo(UPDATED_BAO_CAO_TAI_CHINH);
    }

    @Test
    @Transactional
    void putNonExistingChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChiDaoTuyenWithPatch() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen using partial update
        ChiDaoTuyen partialUpdatedChiDaoTuyen = new ChiDaoTuyen();
        partialUpdatedChiDaoTuyen.setId(chiDaoTuyen.getId());

        partialUpdatedChiDaoTuyen
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ghiChu(UPDATED_GHI_CHU)
            .baoCaoTaiChinh(UPDATED_BAO_CAO_TAI_CHINH);

        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiDaoTuyen.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChiDaoTuyen))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getMaCdt()).isEqualTo(DEFAULT_MA_CDT);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(DEFAULT_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(DEFAULT_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(DEFAULT_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(DEFAULT_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testChiDaoTuyen.getNoiDungHoTro()).isEqualTo(DEFAULT_NOI_DUNG_HO_TRO);
        assertThat(testChiDaoTuyen.getBaoCaoTaiChinh()).isEqualTo(UPDATED_BAO_CAO_TAI_CHINH);
    }

    @Test
    @Transactional
    void fullUpdateChiDaoTuyenWithPatch() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();

        // Update the chiDaoTuyen using partial update
        ChiDaoTuyen partialUpdatedChiDaoTuyen = new ChiDaoTuyen();
        partialUpdatedChiDaoTuyen.setId(chiDaoTuyen.getId());

        partialUpdatedChiDaoTuyen
            .maCdt(UPDATED_MA_CDT)
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHD(UPDATED_SO_HD)
            .ngayHD(UPDATED_NGAY_HD)
            .lyDoCT(UPDATED_LY_DO_CT)
            .noiDung(UPDATED_NOI_DUNG)
            .noiCongTac(UPDATED_NOI_CONG_TAC)
            .ngayBatDau(UPDATED_NGAY_BAT_DAU)
            .ngayKetThuc(UPDATED_NGAY_KET_THUC)
            .ghiChu(UPDATED_GHI_CHU)
            .noiDungHoTro(UPDATED_NOI_DUNG_HO_TRO)
            .baoCaoTaiChinh(UPDATED_BAO_CAO_TAI_CHINH);

        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiDaoTuyen.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChiDaoTuyen))
            )
            .andExpect(status().isOk());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiDaoTuyen testChiDaoTuyen = chiDaoTuyenList.get(chiDaoTuyenList.size() - 1);
        assertThat(testChiDaoTuyen.getMaCdt()).isEqualTo(UPDATED_MA_CDT);
        assertThat(testChiDaoTuyen.getSoQuyetDinh()).isEqualTo(UPDATED_SO_QUYET_DINH);
        assertThat(testChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testChiDaoTuyen.getSoHD()).isEqualTo(UPDATED_SO_HD);
        assertThat(testChiDaoTuyen.getNgayHD()).isEqualTo(UPDATED_NGAY_HD);
        assertThat(testChiDaoTuyen.getLyDoCT()).isEqualTo(UPDATED_LY_DO_CT);
        assertThat(testChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testChiDaoTuyen.getNoiCongTac()).isEqualTo(UPDATED_NOI_CONG_TAC);
        assertThat(testChiDaoTuyen.getNgayBatDau()).isEqualTo(UPDATED_NGAY_BAT_DAU);
        assertThat(testChiDaoTuyen.getNgayKetThuc()).isEqualTo(UPDATED_NGAY_KET_THUC);
        assertThat(testChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testChiDaoTuyen.getNoiDungHoTro()).isEqualTo(UPDATED_NOI_DUNG_HO_TRO);
        assertThat(testChiDaoTuyen.getBaoCaoTaiChinh()).isEqualTo(UPDATED_BAO_CAO_TAI_CHINH);
    }

    @Test
    @Transactional
    void patchNonExistingChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chiDaoTuyenDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiDaoTuyenRepository.findAll().size();
        chiDaoTuyen.setId(count.incrementAndGet());

        // Create the ChiDaoTuyen
        ChiDaoTuyenDTO chiDaoTuyenDTO = chiDaoTuyenMapper.toDto(chiDaoTuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiDaoTuyenMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chiDaoTuyenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiDaoTuyen in the database
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChiDaoTuyen() throws Exception {
        // Initialize the database
        chiDaoTuyenRepository.saveAndFlush(chiDaoTuyen);

        int databaseSizeBeforeDelete = chiDaoTuyenRepository.findAll().size();

        // Delete the chiDaoTuyen
        restChiDaoTuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, chiDaoTuyen.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChiDaoTuyen> chiDaoTuyenList = chiDaoTuyenRepository.findAll();
        assertThat(chiDaoTuyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
