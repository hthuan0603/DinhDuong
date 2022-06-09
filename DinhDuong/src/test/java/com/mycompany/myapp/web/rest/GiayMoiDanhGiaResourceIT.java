package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.GiayMoiDanhGia;
import com.mycompany.myapp.repository.GiayMoiDanhGiaRepository;
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
 * Integration tests for the {@link GiayMoiDanhGiaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GiayMoiDanhGiaResourceIT {

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_MA_NGUOI_TAO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_THOI_GIAN_CHI_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_THOI_GIAN_CHI_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CHUAN_DOAN = "AAAAAAAAAA";
    private static final String UPDATED_CHUAN_DOAN = "BBBBBBBBBB";

    private static final String DEFAULT_CHUAN_DOAN_PHU = "AAAAAAAAAA";
    private static final String UPDATED_CHUAN_DOAN_PHU = "BBBBBBBBBB";

    private static final String DEFAULT_GUI_TU = "AAAAAAAAAA";
    private static final String UPDATED_GUI_TU = "BBBBBBBBBB";

    private static final String DEFAULT_GUI_DEN = "AAAAAAAAAA";
    private static final String UPDATED_GUI_DEN = "BBBBBBBBBB";

    private static final String DEFAULT_MOI = "AAAAAAAAAA";
    private static final String UPDATED_MOI = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG_HOI_CHUAN = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_HOI_CHUAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOI_CHUAN_LAN = 1;
    private static final Integer UPDATED_HOI_CHUAN_LAN = 2;

    private static final ZonedDateTime DEFAULT_THOI_GIAN_HOI_CHUAN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_THOI_GIAN_HOI_CHUAN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_TRANG_THAI = false;
    private static final Boolean UPDATED_TRANG_THAI = true;

    private static final String ENTITY_API_URL = "/api/giay-moi-danh-gias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiayMoiDanhGiaRepository giayMoiDanhGiaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiayMoiDanhGiaMockMvc;

    private GiayMoiDanhGia giayMoiDanhGia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiayMoiDanhGia createEntity(EntityManager em) {
        GiayMoiDanhGia giayMoiDanhGia = new GiayMoiDanhGia()
            .maBN(DEFAULT_MA_BN)
            .maNguoiTao(DEFAULT_MA_NGUOI_TAO)
            .thoiGianChiDinh(DEFAULT_THOI_GIAN_CHI_DINH)
            .chuanDoan(DEFAULT_CHUAN_DOAN)
            .chuanDoanPhu(DEFAULT_CHUAN_DOAN_PHU)
            .guiTu(DEFAULT_GUI_TU)
            .guiDen(DEFAULT_GUI_DEN)
            .moi(DEFAULT_MOI)
            .noiDungHoiChuan(DEFAULT_NOI_DUNG_HOI_CHUAN)
            .hoiChuanLan(DEFAULT_HOI_CHUAN_LAN)
            .thoiGianHoiChuan(DEFAULT_THOI_GIAN_HOI_CHUAN)
            .trangThai(DEFAULT_TRANG_THAI);
        return giayMoiDanhGia;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiayMoiDanhGia createUpdatedEntity(EntityManager em) {
        GiayMoiDanhGia giayMoiDanhGia = new GiayMoiDanhGia()
            .maBN(UPDATED_MA_BN)
            .maNguoiTao(UPDATED_MA_NGUOI_TAO)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanPhu(UPDATED_CHUAN_DOAN_PHU)
            .guiTu(UPDATED_GUI_TU)
            .guiDen(UPDATED_GUI_DEN)
            .moi(UPDATED_MOI)
            .noiDungHoiChuan(UPDATED_NOI_DUNG_HOI_CHUAN)
            .hoiChuanLan(UPDATED_HOI_CHUAN_LAN)
            .thoiGianHoiChuan(UPDATED_THOI_GIAN_HOI_CHUAN)
            .trangThai(UPDATED_TRANG_THAI);
        return giayMoiDanhGia;
    }

    @BeforeEach
    public void initTest() {
        giayMoiDanhGia = createEntity(em);
    }

    @Test
    @Transactional
    void createGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeCreate = giayMoiDanhGiaRepository.findAll().size();
        // Create the GiayMoiDanhGia
        restGiayMoiDanhGiaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isCreated());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeCreate + 1);
        GiayMoiDanhGia testGiayMoiDanhGia = giayMoiDanhGiaList.get(giayMoiDanhGiaList.size() - 1);
        assertThat(testGiayMoiDanhGia.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testGiayMoiDanhGia.getMaNguoiTao()).isEqualTo(DEFAULT_MA_NGUOI_TAO);
        assertThat(testGiayMoiDanhGia.getThoiGianChiDinh()).isEqualTo(DEFAULT_THOI_GIAN_CHI_DINH);
        assertThat(testGiayMoiDanhGia.getChuanDoan()).isEqualTo(DEFAULT_CHUAN_DOAN);
        assertThat(testGiayMoiDanhGia.getChuanDoanPhu()).isEqualTo(DEFAULT_CHUAN_DOAN_PHU);
        assertThat(testGiayMoiDanhGia.getGuiTu()).isEqualTo(DEFAULT_GUI_TU);
        assertThat(testGiayMoiDanhGia.getGuiDen()).isEqualTo(DEFAULT_GUI_DEN);
        assertThat(testGiayMoiDanhGia.getMoi()).isEqualTo(DEFAULT_MOI);
        assertThat(testGiayMoiDanhGia.getNoiDungHoiChuan()).isEqualTo(DEFAULT_NOI_DUNG_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getHoiChuanLan()).isEqualTo(DEFAULT_HOI_CHUAN_LAN);
        assertThat(testGiayMoiDanhGia.getThoiGianHoiChuan()).isEqualTo(DEFAULT_THOI_GIAN_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getTrangThai()).isEqualTo(DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void createGiayMoiDanhGiaWithExistingId() throws Exception {
        // Create the GiayMoiDanhGia with an existing ID
        giayMoiDanhGia.setId(1L);

        int databaseSizeBeforeCreate = giayMoiDanhGiaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiayMoiDanhGiaMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGiayMoiDanhGias() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        // Get all the giayMoiDanhGiaList
        restGiayMoiDanhGiaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giayMoiDanhGia.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].maNguoiTao").value(hasItem(DEFAULT_MA_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].thoiGianChiDinh").value(hasItem(sameInstant(DEFAULT_THOI_GIAN_CHI_DINH))))
            .andExpect(jsonPath("$.[*].chuanDoan").value(hasItem(DEFAULT_CHUAN_DOAN)))
            .andExpect(jsonPath("$.[*].chuanDoanPhu").value(hasItem(DEFAULT_CHUAN_DOAN_PHU)))
            .andExpect(jsonPath("$.[*].guiTu").value(hasItem(DEFAULT_GUI_TU)))
            .andExpect(jsonPath("$.[*].guiDen").value(hasItem(DEFAULT_GUI_DEN)))
            .andExpect(jsonPath("$.[*].moi").value(hasItem(DEFAULT_MOI)))
            .andExpect(jsonPath("$.[*].noiDungHoiChuan").value(hasItem(DEFAULT_NOI_DUNG_HOI_CHUAN)))
            .andExpect(jsonPath("$.[*].hoiChuanLan").value(hasItem(DEFAULT_HOI_CHUAN_LAN)))
            .andExpect(jsonPath("$.[*].thoiGianHoiChuan").value(hasItem(sameInstant(DEFAULT_THOI_GIAN_HOI_CHUAN))))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.booleanValue())));
    }

    @Test
    @Transactional
    void getGiayMoiDanhGia() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        // Get the giayMoiDanhGia
        restGiayMoiDanhGiaMockMvc
            .perform(get(ENTITY_API_URL_ID, giayMoiDanhGia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(giayMoiDanhGia.getId().intValue()))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.maNguoiTao").value(DEFAULT_MA_NGUOI_TAO))
            .andExpect(jsonPath("$.thoiGianChiDinh").value(sameInstant(DEFAULT_THOI_GIAN_CHI_DINH)))
            .andExpect(jsonPath("$.chuanDoan").value(DEFAULT_CHUAN_DOAN))
            .andExpect(jsonPath("$.chuanDoanPhu").value(DEFAULT_CHUAN_DOAN_PHU))
            .andExpect(jsonPath("$.guiTu").value(DEFAULT_GUI_TU))
            .andExpect(jsonPath("$.guiDen").value(DEFAULT_GUI_DEN))
            .andExpect(jsonPath("$.moi").value(DEFAULT_MOI))
            .andExpect(jsonPath("$.noiDungHoiChuan").value(DEFAULT_NOI_DUNG_HOI_CHUAN))
            .andExpect(jsonPath("$.hoiChuanLan").value(DEFAULT_HOI_CHUAN_LAN))
            .andExpect(jsonPath("$.thoiGianHoiChuan").value(sameInstant(DEFAULT_THOI_GIAN_HOI_CHUAN)))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingGiayMoiDanhGia() throws Exception {
        // Get the giayMoiDanhGia
        restGiayMoiDanhGiaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGiayMoiDanhGia() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();

        // Update the giayMoiDanhGia
        GiayMoiDanhGia updatedGiayMoiDanhGia = giayMoiDanhGiaRepository.findById(giayMoiDanhGia.getId()).get();
        // Disconnect from session so that the updates on updatedGiayMoiDanhGia are not directly saved in db
        em.detach(updatedGiayMoiDanhGia);
        updatedGiayMoiDanhGia
            .maBN(UPDATED_MA_BN)
            .maNguoiTao(UPDATED_MA_NGUOI_TAO)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanPhu(UPDATED_CHUAN_DOAN_PHU)
            .guiTu(UPDATED_GUI_TU)
            .guiDen(UPDATED_GUI_DEN)
            .moi(UPDATED_MOI)
            .noiDungHoiChuan(UPDATED_NOI_DUNG_HOI_CHUAN)
            .hoiChuanLan(UPDATED_HOI_CHUAN_LAN)
            .thoiGianHoiChuan(UPDATED_THOI_GIAN_HOI_CHUAN)
            .trangThai(UPDATED_TRANG_THAI);

        restGiayMoiDanhGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedGiayMoiDanhGia.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedGiayMoiDanhGia))
            )
            .andExpect(status().isOk());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
        GiayMoiDanhGia testGiayMoiDanhGia = giayMoiDanhGiaList.get(giayMoiDanhGiaList.size() - 1);
        assertThat(testGiayMoiDanhGia.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testGiayMoiDanhGia.getMaNguoiTao()).isEqualTo(UPDATED_MA_NGUOI_TAO);
        assertThat(testGiayMoiDanhGia.getThoiGianChiDinh()).isEqualTo(UPDATED_THOI_GIAN_CHI_DINH);
        assertThat(testGiayMoiDanhGia.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testGiayMoiDanhGia.getChuanDoanPhu()).isEqualTo(UPDATED_CHUAN_DOAN_PHU);
        assertThat(testGiayMoiDanhGia.getGuiTu()).isEqualTo(UPDATED_GUI_TU);
        assertThat(testGiayMoiDanhGia.getGuiDen()).isEqualTo(UPDATED_GUI_DEN);
        assertThat(testGiayMoiDanhGia.getMoi()).isEqualTo(UPDATED_MOI);
        assertThat(testGiayMoiDanhGia.getNoiDungHoiChuan()).isEqualTo(UPDATED_NOI_DUNG_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getHoiChuanLan()).isEqualTo(UPDATED_HOI_CHUAN_LAN);
        assertThat(testGiayMoiDanhGia.getThoiGianHoiChuan()).isEqualTo(UPDATED_THOI_GIAN_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getTrangThai()).isEqualTo(UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void putNonExistingGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giayMoiDanhGia.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiayMoiDanhGiaWithPatch() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();

        // Update the giayMoiDanhGia using partial update
        GiayMoiDanhGia partialUpdatedGiayMoiDanhGia = new GiayMoiDanhGia();
        partialUpdatedGiayMoiDanhGia.setId(giayMoiDanhGia.getId());

        partialUpdatedGiayMoiDanhGia
            .maBN(UPDATED_MA_BN)
            .maNguoiTao(UPDATED_MA_NGUOI_TAO)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .guiDen(UPDATED_GUI_DEN)
            .moi(UPDATED_MOI)
            .noiDungHoiChuan(UPDATED_NOI_DUNG_HOI_CHUAN)
            .hoiChuanLan(UPDATED_HOI_CHUAN_LAN)
            .trangThai(UPDATED_TRANG_THAI);

        restGiayMoiDanhGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiayMoiDanhGia.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiayMoiDanhGia))
            )
            .andExpect(status().isOk());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
        GiayMoiDanhGia testGiayMoiDanhGia = giayMoiDanhGiaList.get(giayMoiDanhGiaList.size() - 1);
        assertThat(testGiayMoiDanhGia.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testGiayMoiDanhGia.getMaNguoiTao()).isEqualTo(UPDATED_MA_NGUOI_TAO);
        assertThat(testGiayMoiDanhGia.getThoiGianChiDinh()).isEqualTo(UPDATED_THOI_GIAN_CHI_DINH);
        assertThat(testGiayMoiDanhGia.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testGiayMoiDanhGia.getChuanDoanPhu()).isEqualTo(DEFAULT_CHUAN_DOAN_PHU);
        assertThat(testGiayMoiDanhGia.getGuiTu()).isEqualTo(DEFAULT_GUI_TU);
        assertThat(testGiayMoiDanhGia.getGuiDen()).isEqualTo(UPDATED_GUI_DEN);
        assertThat(testGiayMoiDanhGia.getMoi()).isEqualTo(UPDATED_MOI);
        assertThat(testGiayMoiDanhGia.getNoiDungHoiChuan()).isEqualTo(UPDATED_NOI_DUNG_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getHoiChuanLan()).isEqualTo(UPDATED_HOI_CHUAN_LAN);
        assertThat(testGiayMoiDanhGia.getThoiGianHoiChuan()).isEqualTo(DEFAULT_THOI_GIAN_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getTrangThai()).isEqualTo(UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void fullUpdateGiayMoiDanhGiaWithPatch() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();

        // Update the giayMoiDanhGia using partial update
        GiayMoiDanhGia partialUpdatedGiayMoiDanhGia = new GiayMoiDanhGia();
        partialUpdatedGiayMoiDanhGia.setId(giayMoiDanhGia.getId());

        partialUpdatedGiayMoiDanhGia
            .maBN(UPDATED_MA_BN)
            .maNguoiTao(UPDATED_MA_NGUOI_TAO)
            .thoiGianChiDinh(UPDATED_THOI_GIAN_CHI_DINH)
            .chuanDoan(UPDATED_CHUAN_DOAN)
            .chuanDoanPhu(UPDATED_CHUAN_DOAN_PHU)
            .guiTu(UPDATED_GUI_TU)
            .guiDen(UPDATED_GUI_DEN)
            .moi(UPDATED_MOI)
            .noiDungHoiChuan(UPDATED_NOI_DUNG_HOI_CHUAN)
            .hoiChuanLan(UPDATED_HOI_CHUAN_LAN)
            .thoiGianHoiChuan(UPDATED_THOI_GIAN_HOI_CHUAN)
            .trangThai(UPDATED_TRANG_THAI);

        restGiayMoiDanhGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiayMoiDanhGia.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiayMoiDanhGia))
            )
            .andExpect(status().isOk());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
        GiayMoiDanhGia testGiayMoiDanhGia = giayMoiDanhGiaList.get(giayMoiDanhGiaList.size() - 1);
        assertThat(testGiayMoiDanhGia.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testGiayMoiDanhGia.getMaNguoiTao()).isEqualTo(UPDATED_MA_NGUOI_TAO);
        assertThat(testGiayMoiDanhGia.getThoiGianChiDinh()).isEqualTo(UPDATED_THOI_GIAN_CHI_DINH);
        assertThat(testGiayMoiDanhGia.getChuanDoan()).isEqualTo(UPDATED_CHUAN_DOAN);
        assertThat(testGiayMoiDanhGia.getChuanDoanPhu()).isEqualTo(UPDATED_CHUAN_DOAN_PHU);
        assertThat(testGiayMoiDanhGia.getGuiTu()).isEqualTo(UPDATED_GUI_TU);
        assertThat(testGiayMoiDanhGia.getGuiDen()).isEqualTo(UPDATED_GUI_DEN);
        assertThat(testGiayMoiDanhGia.getMoi()).isEqualTo(UPDATED_MOI);
        assertThat(testGiayMoiDanhGia.getNoiDungHoiChuan()).isEqualTo(UPDATED_NOI_DUNG_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getHoiChuanLan()).isEqualTo(UPDATED_HOI_CHUAN_LAN);
        assertThat(testGiayMoiDanhGia.getThoiGianHoiChuan()).isEqualTo(UPDATED_THOI_GIAN_HOI_CHUAN);
        assertThat(testGiayMoiDanhGia.getTrangThai()).isEqualTo(UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void patchNonExistingGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, giayMoiDanhGia.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGiayMoiDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = giayMoiDanhGiaRepository.findAll().size();
        giayMoiDanhGia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiayMoiDanhGiaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giayMoiDanhGia))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiayMoiDanhGia in the database
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGiayMoiDanhGia() throws Exception {
        // Initialize the database
        giayMoiDanhGiaRepository.saveAndFlush(giayMoiDanhGia);

        int databaseSizeBeforeDelete = giayMoiDanhGiaRepository.findAll().size();

        // Delete the giayMoiDanhGia
        restGiayMoiDanhGiaMockMvc
            .perform(delete(ENTITY_API_URL_ID, giayMoiDanhGia.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GiayMoiDanhGia> giayMoiDanhGiaList = giayMoiDanhGiaRepository.findAll();
        assertThat(giayMoiDanhGiaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
