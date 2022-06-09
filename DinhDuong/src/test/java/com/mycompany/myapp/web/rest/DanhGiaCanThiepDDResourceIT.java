package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DanhGiaCanThiepDD;
import com.mycompany.myapp.repository.DanhGiaCanThiepDDRepository;
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
 * Integration tests for the {@link DanhGiaCanThiepDDResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhGiaCanThiepDDResourceIT {

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final String DEFAULT_CHUAN_DOAN_LS = "AAAAAAAAAA";
    private static final String UPDATED_CHUAN_DOAN_LS = "BBBBBBBBBB";

    private static final String DEFAULT_B_S_DIEU_TRI = "AAAAAAAAAA";
    private static final String UPDATED_B_S_DIEU_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_THAY_DOI_CAN_NANG_TRONG_1_THANG = "AAAAAAAAAA";
    private static final String UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_CN = 1;
    private static final Integer UPDATED_DANH_GIA_CN = 2;

    private static final String DEFAULT_KHAU_PHAN_AN = "AAAAAAAAAA";
    private static final String UPDATED_KHAU_PHAN_AN = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_KPA = 1;
    private static final Integer UPDATED_DANH_GIA_KPA = 2;

    private static final String DEFAULT_TRIEU_CHUNG_TIEU_HOA = "AAAAAAAAAA";
    private static final String UPDATED_TRIEU_CHUNG_TIEU_HOA = "BBBBBBBBBB";

    private static final String DEFAULT_MUC_DO = "AAAAAAAAAA";
    private static final String UPDATED_MUC_DO = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_MD = 1;
    private static final Integer UPDATED_DANH_GIA_MD = 2;

    private static final String DEFAULT_GIAM_CHUC_NANG_HOAT_DONG = "AAAAAAAAAA";
    private static final String UPDATED_GIAM_CHUC_NANG_HOAT_DONG = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_CNHD = 1;
    private static final Integer UPDATED_DANH_GIA_CNHD = 2;

    private static final String DEFAULT_STRESS = "AAAAAAAAAA";
    private static final String UPDATED_STRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_DANH_GIA_STRESS = 1;
    private static final Integer UPDATED_DANH_GIA_STRESS = 2;

    private static final Boolean DEFAULT_REFEEDING = false;
    private static final Boolean UPDATED_REFEEDING = true;

    private static final Integer DEFAULT_DANH_GIA_REFEEDING = 1;
    private static final Integer UPDATED_DANH_GIA_REFEEDING = 2;

    private static final Integer DEFAULT_TONG_DIEM = 1;
    private static final Integer UPDATED_TONG_DIEM = 2;

    private static final String ENTITY_API_URL = "/api/danh-gia-can-thiep-dds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DanhGiaCanThiepDDRepository danhGiaCanThiepDDRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhGiaCanThiepDDMockMvc;

    private DanhGiaCanThiepDD danhGiaCanThiepDD;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhGiaCanThiepDD createEntity(EntityManager em) {
        DanhGiaCanThiepDD danhGiaCanThiepDD = new DanhGiaCanThiepDD()
            .maBN(DEFAULT_MA_BN)
            .chuanDoanLS(DEFAULT_CHUAN_DOAN_LS)
            .bSDieuTri(DEFAULT_B_S_DIEU_TRI)
            .thayDoiCanNangTrong1Thang(DEFAULT_THAY_DOI_CAN_NANG_TRONG_1_THANG)
            .danhGiaCN(DEFAULT_DANH_GIA_CN)
            .khauPhanAn(DEFAULT_KHAU_PHAN_AN)
            .danhGiaKPA(DEFAULT_DANH_GIA_KPA)
            .trieuChungTieuHoa(DEFAULT_TRIEU_CHUNG_TIEU_HOA)
            .mucDo(DEFAULT_MUC_DO)
            .danhGiaMD(DEFAULT_DANH_GIA_MD)
            .giamChucNangHoatDong(DEFAULT_GIAM_CHUC_NANG_HOAT_DONG)
            .danhGiaCNHD(DEFAULT_DANH_GIA_CNHD)
            .stress(DEFAULT_STRESS)
            .danhGiaStress(DEFAULT_DANH_GIA_STRESS)
            .refeeding(DEFAULT_REFEEDING)
            .danhGiaRefeeding(DEFAULT_DANH_GIA_REFEEDING)
            .tongDiem(DEFAULT_TONG_DIEM);
        return danhGiaCanThiepDD;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhGiaCanThiepDD createUpdatedEntity(EntityManager em) {
        DanhGiaCanThiepDD danhGiaCanThiepDD = new DanhGiaCanThiepDD()
            .maBN(UPDATED_MA_BN)
            .chuanDoanLS(UPDATED_CHUAN_DOAN_LS)
            .bSDieuTri(UPDATED_B_S_DIEU_TRI)
            .thayDoiCanNangTrong1Thang(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG)
            .danhGiaCN(UPDATED_DANH_GIA_CN)
            .khauPhanAn(UPDATED_KHAU_PHAN_AN)
            .danhGiaKPA(UPDATED_DANH_GIA_KPA)
            .trieuChungTieuHoa(UPDATED_TRIEU_CHUNG_TIEU_HOA)
            .mucDo(UPDATED_MUC_DO)
            .danhGiaMD(UPDATED_DANH_GIA_MD)
            .giamChucNangHoatDong(UPDATED_GIAM_CHUC_NANG_HOAT_DONG)
            .danhGiaCNHD(UPDATED_DANH_GIA_CNHD)
            .stress(UPDATED_STRESS)
            .danhGiaStress(UPDATED_DANH_GIA_STRESS)
            .refeeding(UPDATED_REFEEDING)
            .danhGiaRefeeding(UPDATED_DANH_GIA_REFEEDING)
            .tongDiem(UPDATED_TONG_DIEM);
        return danhGiaCanThiepDD;
    }

    @BeforeEach
    public void initTest() {
        danhGiaCanThiepDD = createEntity(em);
    }

    @Test
    @Transactional
    void createDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeCreate = danhGiaCanThiepDDRepository.findAll().size();
        // Create the DanhGiaCanThiepDD
        restDanhGiaCanThiepDDMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isCreated());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeCreate + 1);
        DanhGiaCanThiepDD testDanhGiaCanThiepDD = danhGiaCanThiepDDList.get(danhGiaCanThiepDDList.size() - 1);
        assertThat(testDanhGiaCanThiepDD.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testDanhGiaCanThiepDD.getChuanDoanLS()).isEqualTo(DEFAULT_CHUAN_DOAN_LS);
        assertThat(testDanhGiaCanThiepDD.getbSDieuTri()).isEqualTo(DEFAULT_B_S_DIEU_TRI);
        assertThat(testDanhGiaCanThiepDD.getThayDoiCanNangTrong1Thang()).isEqualTo(DEFAULT_THAY_DOI_CAN_NANG_TRONG_1_THANG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCN()).isEqualTo(DEFAULT_DANH_GIA_CN);
        assertThat(testDanhGiaCanThiepDD.getKhauPhanAn()).isEqualTo(DEFAULT_KHAU_PHAN_AN);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaKPA()).isEqualTo(DEFAULT_DANH_GIA_KPA);
        assertThat(testDanhGiaCanThiepDD.getTrieuChungTieuHoa()).isEqualTo(DEFAULT_TRIEU_CHUNG_TIEU_HOA);
        assertThat(testDanhGiaCanThiepDD.getMucDo()).isEqualTo(DEFAULT_MUC_DO);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaMD()).isEqualTo(DEFAULT_DANH_GIA_MD);
        assertThat(testDanhGiaCanThiepDD.getGiamChucNangHoatDong()).isEqualTo(DEFAULT_GIAM_CHUC_NANG_HOAT_DONG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCNHD()).isEqualTo(DEFAULT_DANH_GIA_CNHD);
        assertThat(testDanhGiaCanThiepDD.getStress()).isEqualTo(DEFAULT_STRESS);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaStress()).isEqualTo(DEFAULT_DANH_GIA_STRESS);
        assertThat(testDanhGiaCanThiepDD.getRefeeding()).isEqualTo(DEFAULT_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaRefeeding()).isEqualTo(DEFAULT_DANH_GIA_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getTongDiem()).isEqualTo(DEFAULT_TONG_DIEM);
    }

    @Test
    @Transactional
    void createDanhGiaCanThiepDDWithExistingId() throws Exception {
        // Create the DanhGiaCanThiepDD with an existing ID
        danhGiaCanThiepDD.setId(1L);

        int databaseSizeBeforeCreate = danhGiaCanThiepDDRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhGiaCanThiepDDMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhGiaCanThiepDDS() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        // Get all the danhGiaCanThiepDDList
        restDanhGiaCanThiepDDMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhGiaCanThiepDD.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].chuanDoanLS").value(hasItem(DEFAULT_CHUAN_DOAN_LS)))
            .andExpect(jsonPath("$.[*].bSDieuTri").value(hasItem(DEFAULT_B_S_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].thayDoiCanNangTrong1Thang").value(hasItem(DEFAULT_THAY_DOI_CAN_NANG_TRONG_1_THANG)))
            .andExpect(jsonPath("$.[*].danhGiaCN").value(hasItem(DEFAULT_DANH_GIA_CN)))
            .andExpect(jsonPath("$.[*].khauPhanAn").value(hasItem(DEFAULT_KHAU_PHAN_AN)))
            .andExpect(jsonPath("$.[*].danhGiaKPA").value(hasItem(DEFAULT_DANH_GIA_KPA)))
            .andExpect(jsonPath("$.[*].trieuChungTieuHoa").value(hasItem(DEFAULT_TRIEU_CHUNG_TIEU_HOA)))
            .andExpect(jsonPath("$.[*].mucDo").value(hasItem(DEFAULT_MUC_DO)))
            .andExpect(jsonPath("$.[*].danhGiaMD").value(hasItem(DEFAULT_DANH_GIA_MD)))
            .andExpect(jsonPath("$.[*].giamChucNangHoatDong").value(hasItem(DEFAULT_GIAM_CHUC_NANG_HOAT_DONG)))
            .andExpect(jsonPath("$.[*].danhGiaCNHD").value(hasItem(DEFAULT_DANH_GIA_CNHD)))
            .andExpect(jsonPath("$.[*].stress").value(hasItem(DEFAULT_STRESS)))
            .andExpect(jsonPath("$.[*].danhGiaStress").value(hasItem(DEFAULT_DANH_GIA_STRESS)))
            .andExpect(jsonPath("$.[*].refeeding").value(hasItem(DEFAULT_REFEEDING.booleanValue())))
            .andExpect(jsonPath("$.[*].danhGiaRefeeding").value(hasItem(DEFAULT_DANH_GIA_REFEEDING)))
            .andExpect(jsonPath("$.[*].tongDiem").value(hasItem(DEFAULT_TONG_DIEM)));
    }

    @Test
    @Transactional
    void getDanhGiaCanThiepDD() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        // Get the danhGiaCanThiepDD
        restDanhGiaCanThiepDDMockMvc
            .perform(get(ENTITY_API_URL_ID, danhGiaCanThiepDD.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhGiaCanThiepDD.getId().intValue()))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.chuanDoanLS").value(DEFAULT_CHUAN_DOAN_LS))
            .andExpect(jsonPath("$.bSDieuTri").value(DEFAULT_B_S_DIEU_TRI))
            .andExpect(jsonPath("$.thayDoiCanNangTrong1Thang").value(DEFAULT_THAY_DOI_CAN_NANG_TRONG_1_THANG))
            .andExpect(jsonPath("$.danhGiaCN").value(DEFAULT_DANH_GIA_CN))
            .andExpect(jsonPath("$.khauPhanAn").value(DEFAULT_KHAU_PHAN_AN))
            .andExpect(jsonPath("$.danhGiaKPA").value(DEFAULT_DANH_GIA_KPA))
            .andExpect(jsonPath("$.trieuChungTieuHoa").value(DEFAULT_TRIEU_CHUNG_TIEU_HOA))
            .andExpect(jsonPath("$.mucDo").value(DEFAULT_MUC_DO))
            .andExpect(jsonPath("$.danhGiaMD").value(DEFAULT_DANH_GIA_MD))
            .andExpect(jsonPath("$.giamChucNangHoatDong").value(DEFAULT_GIAM_CHUC_NANG_HOAT_DONG))
            .andExpect(jsonPath("$.danhGiaCNHD").value(DEFAULT_DANH_GIA_CNHD))
            .andExpect(jsonPath("$.stress").value(DEFAULT_STRESS))
            .andExpect(jsonPath("$.danhGiaStress").value(DEFAULT_DANH_GIA_STRESS))
            .andExpect(jsonPath("$.refeeding").value(DEFAULT_REFEEDING.booleanValue()))
            .andExpect(jsonPath("$.danhGiaRefeeding").value(DEFAULT_DANH_GIA_REFEEDING))
            .andExpect(jsonPath("$.tongDiem").value(DEFAULT_TONG_DIEM));
    }

    @Test
    @Transactional
    void getNonExistingDanhGiaCanThiepDD() throws Exception {
        // Get the danhGiaCanThiepDD
        restDanhGiaCanThiepDDMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDanhGiaCanThiepDD() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();

        // Update the danhGiaCanThiepDD
        DanhGiaCanThiepDD updatedDanhGiaCanThiepDD = danhGiaCanThiepDDRepository.findById(danhGiaCanThiepDD.getId()).get();
        // Disconnect from session so that the updates on updatedDanhGiaCanThiepDD are not directly saved in db
        em.detach(updatedDanhGiaCanThiepDD);
        updatedDanhGiaCanThiepDD
            .maBN(UPDATED_MA_BN)
            .chuanDoanLS(UPDATED_CHUAN_DOAN_LS)
            .bSDieuTri(UPDATED_B_S_DIEU_TRI)
            .thayDoiCanNangTrong1Thang(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG)
            .danhGiaCN(UPDATED_DANH_GIA_CN)
            .khauPhanAn(UPDATED_KHAU_PHAN_AN)
            .danhGiaKPA(UPDATED_DANH_GIA_KPA)
            .trieuChungTieuHoa(UPDATED_TRIEU_CHUNG_TIEU_HOA)
            .mucDo(UPDATED_MUC_DO)
            .danhGiaMD(UPDATED_DANH_GIA_MD)
            .giamChucNangHoatDong(UPDATED_GIAM_CHUC_NANG_HOAT_DONG)
            .danhGiaCNHD(UPDATED_DANH_GIA_CNHD)
            .stress(UPDATED_STRESS)
            .danhGiaStress(UPDATED_DANH_GIA_STRESS)
            .refeeding(UPDATED_REFEEDING)
            .danhGiaRefeeding(UPDATED_DANH_GIA_REFEEDING)
            .tongDiem(UPDATED_TONG_DIEM);

        restDanhGiaCanThiepDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDanhGiaCanThiepDD.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDanhGiaCanThiepDD))
            )
            .andExpect(status().isOk());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
        DanhGiaCanThiepDD testDanhGiaCanThiepDD = danhGiaCanThiepDDList.get(danhGiaCanThiepDDList.size() - 1);
        assertThat(testDanhGiaCanThiepDD.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testDanhGiaCanThiepDD.getChuanDoanLS()).isEqualTo(UPDATED_CHUAN_DOAN_LS);
        assertThat(testDanhGiaCanThiepDD.getbSDieuTri()).isEqualTo(UPDATED_B_S_DIEU_TRI);
        assertThat(testDanhGiaCanThiepDD.getThayDoiCanNangTrong1Thang()).isEqualTo(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCN()).isEqualTo(UPDATED_DANH_GIA_CN);
        assertThat(testDanhGiaCanThiepDD.getKhauPhanAn()).isEqualTo(UPDATED_KHAU_PHAN_AN);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaKPA()).isEqualTo(UPDATED_DANH_GIA_KPA);
        assertThat(testDanhGiaCanThiepDD.getTrieuChungTieuHoa()).isEqualTo(UPDATED_TRIEU_CHUNG_TIEU_HOA);
        assertThat(testDanhGiaCanThiepDD.getMucDo()).isEqualTo(UPDATED_MUC_DO);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaMD()).isEqualTo(UPDATED_DANH_GIA_MD);
        assertThat(testDanhGiaCanThiepDD.getGiamChucNangHoatDong()).isEqualTo(UPDATED_GIAM_CHUC_NANG_HOAT_DONG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCNHD()).isEqualTo(UPDATED_DANH_GIA_CNHD);
        assertThat(testDanhGiaCanThiepDD.getStress()).isEqualTo(UPDATED_STRESS);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaStress()).isEqualTo(UPDATED_DANH_GIA_STRESS);
        assertThat(testDanhGiaCanThiepDD.getRefeeding()).isEqualTo(UPDATED_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaRefeeding()).isEqualTo(UPDATED_DANH_GIA_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getTongDiem()).isEqualTo(UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    void putNonExistingDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhGiaCanThiepDD.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhGiaCanThiepDDWithPatch() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();

        // Update the danhGiaCanThiepDD using partial update
        DanhGiaCanThiepDD partialUpdatedDanhGiaCanThiepDD = new DanhGiaCanThiepDD();
        partialUpdatedDanhGiaCanThiepDD.setId(danhGiaCanThiepDD.getId());

        partialUpdatedDanhGiaCanThiepDD
            .chuanDoanLS(UPDATED_CHUAN_DOAN_LS)
            .bSDieuTri(UPDATED_B_S_DIEU_TRI)
            .thayDoiCanNangTrong1Thang(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG)
            .trieuChungTieuHoa(UPDATED_TRIEU_CHUNG_TIEU_HOA)
            .refeeding(UPDATED_REFEEDING)
            .danhGiaRefeeding(UPDATED_DANH_GIA_REFEEDING);

        restDanhGiaCanThiepDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhGiaCanThiepDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDanhGiaCanThiepDD))
            )
            .andExpect(status().isOk());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
        DanhGiaCanThiepDD testDanhGiaCanThiepDD = danhGiaCanThiepDDList.get(danhGiaCanThiepDDList.size() - 1);
        assertThat(testDanhGiaCanThiepDD.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testDanhGiaCanThiepDD.getChuanDoanLS()).isEqualTo(UPDATED_CHUAN_DOAN_LS);
        assertThat(testDanhGiaCanThiepDD.getbSDieuTri()).isEqualTo(UPDATED_B_S_DIEU_TRI);
        assertThat(testDanhGiaCanThiepDD.getThayDoiCanNangTrong1Thang()).isEqualTo(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCN()).isEqualTo(DEFAULT_DANH_GIA_CN);
        assertThat(testDanhGiaCanThiepDD.getKhauPhanAn()).isEqualTo(DEFAULT_KHAU_PHAN_AN);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaKPA()).isEqualTo(DEFAULT_DANH_GIA_KPA);
        assertThat(testDanhGiaCanThiepDD.getTrieuChungTieuHoa()).isEqualTo(UPDATED_TRIEU_CHUNG_TIEU_HOA);
        assertThat(testDanhGiaCanThiepDD.getMucDo()).isEqualTo(DEFAULT_MUC_DO);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaMD()).isEqualTo(DEFAULT_DANH_GIA_MD);
        assertThat(testDanhGiaCanThiepDD.getGiamChucNangHoatDong()).isEqualTo(DEFAULT_GIAM_CHUC_NANG_HOAT_DONG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCNHD()).isEqualTo(DEFAULT_DANH_GIA_CNHD);
        assertThat(testDanhGiaCanThiepDD.getStress()).isEqualTo(DEFAULT_STRESS);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaStress()).isEqualTo(DEFAULT_DANH_GIA_STRESS);
        assertThat(testDanhGiaCanThiepDD.getRefeeding()).isEqualTo(UPDATED_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaRefeeding()).isEqualTo(UPDATED_DANH_GIA_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getTongDiem()).isEqualTo(DEFAULT_TONG_DIEM);
    }

    @Test
    @Transactional
    void fullUpdateDanhGiaCanThiepDDWithPatch() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();

        // Update the danhGiaCanThiepDD using partial update
        DanhGiaCanThiepDD partialUpdatedDanhGiaCanThiepDD = new DanhGiaCanThiepDD();
        partialUpdatedDanhGiaCanThiepDD.setId(danhGiaCanThiepDD.getId());

        partialUpdatedDanhGiaCanThiepDD
            .maBN(UPDATED_MA_BN)
            .chuanDoanLS(UPDATED_CHUAN_DOAN_LS)
            .bSDieuTri(UPDATED_B_S_DIEU_TRI)
            .thayDoiCanNangTrong1Thang(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG)
            .danhGiaCN(UPDATED_DANH_GIA_CN)
            .khauPhanAn(UPDATED_KHAU_PHAN_AN)
            .danhGiaKPA(UPDATED_DANH_GIA_KPA)
            .trieuChungTieuHoa(UPDATED_TRIEU_CHUNG_TIEU_HOA)
            .mucDo(UPDATED_MUC_DO)
            .danhGiaMD(UPDATED_DANH_GIA_MD)
            .giamChucNangHoatDong(UPDATED_GIAM_CHUC_NANG_HOAT_DONG)
            .danhGiaCNHD(UPDATED_DANH_GIA_CNHD)
            .stress(UPDATED_STRESS)
            .danhGiaStress(UPDATED_DANH_GIA_STRESS)
            .refeeding(UPDATED_REFEEDING)
            .danhGiaRefeeding(UPDATED_DANH_GIA_REFEEDING)
            .tongDiem(UPDATED_TONG_DIEM);

        restDanhGiaCanThiepDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhGiaCanThiepDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDanhGiaCanThiepDD))
            )
            .andExpect(status().isOk());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
        DanhGiaCanThiepDD testDanhGiaCanThiepDD = danhGiaCanThiepDDList.get(danhGiaCanThiepDDList.size() - 1);
        assertThat(testDanhGiaCanThiepDD.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testDanhGiaCanThiepDD.getChuanDoanLS()).isEqualTo(UPDATED_CHUAN_DOAN_LS);
        assertThat(testDanhGiaCanThiepDD.getbSDieuTri()).isEqualTo(UPDATED_B_S_DIEU_TRI);
        assertThat(testDanhGiaCanThiepDD.getThayDoiCanNangTrong1Thang()).isEqualTo(UPDATED_THAY_DOI_CAN_NANG_TRONG_1_THANG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCN()).isEqualTo(UPDATED_DANH_GIA_CN);
        assertThat(testDanhGiaCanThiepDD.getKhauPhanAn()).isEqualTo(UPDATED_KHAU_PHAN_AN);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaKPA()).isEqualTo(UPDATED_DANH_GIA_KPA);
        assertThat(testDanhGiaCanThiepDD.getTrieuChungTieuHoa()).isEqualTo(UPDATED_TRIEU_CHUNG_TIEU_HOA);
        assertThat(testDanhGiaCanThiepDD.getMucDo()).isEqualTo(UPDATED_MUC_DO);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaMD()).isEqualTo(UPDATED_DANH_GIA_MD);
        assertThat(testDanhGiaCanThiepDD.getGiamChucNangHoatDong()).isEqualTo(UPDATED_GIAM_CHUC_NANG_HOAT_DONG);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaCNHD()).isEqualTo(UPDATED_DANH_GIA_CNHD);
        assertThat(testDanhGiaCanThiepDD.getStress()).isEqualTo(UPDATED_STRESS);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaStress()).isEqualTo(UPDATED_DANH_GIA_STRESS);
        assertThat(testDanhGiaCanThiepDD.getRefeeding()).isEqualTo(UPDATED_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getDanhGiaRefeeding()).isEqualTo(UPDATED_DANH_GIA_REFEEDING);
        assertThat(testDanhGiaCanThiepDD.getTongDiem()).isEqualTo(UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    void patchNonExistingDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhGiaCanThiepDD.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhGiaCanThiepDD() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaCanThiepDDRepository.findAll().size();
        danhGiaCanThiepDD.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhGiaCanThiepDDMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(danhGiaCanThiepDD))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhGiaCanThiepDD in the database
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhGiaCanThiepDD() throws Exception {
        // Initialize the database
        danhGiaCanThiepDDRepository.saveAndFlush(danhGiaCanThiepDD);

        int databaseSizeBeforeDelete = danhGiaCanThiepDDRepository.findAll().size();

        // Delete the danhGiaCanThiepDD
        restDanhGiaCanThiepDDMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhGiaCanThiepDD.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanhGiaCanThiepDD> danhGiaCanThiepDDList = danhGiaCanThiepDDRepository.findAll();
        assertThat(danhGiaCanThiepDDList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
