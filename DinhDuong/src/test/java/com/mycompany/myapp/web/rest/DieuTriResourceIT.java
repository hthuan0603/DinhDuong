package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DieuTri;
import com.mycompany.myapp.repository.DieuTriRepository;
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
 * Integration tests for the {@link DieuTriResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DieuTriResourceIT {

    private static final String DEFAULT_MA_DIEU_TRI = "AAAAAAAAAA";
    private static final String UPDATED_MA_DIEU_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BENH_AN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BENH_AN = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_BS_DIEU_TRI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BS_DIEU_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_NHA = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_NHA = "BBBBBBBBBB";

    private static final String DEFAULT_GIUONG = "AAAAAAAAAA";
    private static final String UPDATED_GIUONG = "BBBBBBBBBB";

    private static final String DEFAULT_MA_THE_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_THE_BHYT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_VAO_KHOA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_VAO_KHOA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_RA_VIEN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_RA_VIEN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_B_V_CHUYEN = "AAAAAAAAAA";
    private static final String UPDATED_B_V_CHUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_KET_QUA_DIEU_TRI = "AAAAAAAAAA";
    private static final String UPDATED_KET_QUA_DIEU_TRI = "BBBBBBBBBB";

    private static final String DEFAULT_LICH_SU_CHUYEN_KHOA = "AAAAAAAAAA";
    private static final String UPDATED_LICH_SU_CHUYEN_KHOA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dieu-tris";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DieuTriRepository dieuTriRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDieuTriMockMvc;

    private DieuTri dieuTri;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DieuTri createEntity(EntityManager em) {
        DieuTri dieuTri = new DieuTri()
            .maDieuTri(DEFAULT_MA_DIEU_TRI)
            .hoTen(DEFAULT_HO_TEN)
            .maBenhAn(DEFAULT_MA_BENH_AN)
            .tenBSDieuTri(DEFAULT_TEN_BS_DIEU_TRI)
            .nguoiNha(DEFAULT_NGUOI_NHA)
            .giuong(DEFAULT_GIUONG)
            .maTheBHYT(DEFAULT_MA_THE_BHYT)
            .ngayVaoKhoa(DEFAULT_NGAY_VAO_KHOA)
            .ngayRaVien(DEFAULT_NGAY_RA_VIEN)
            .bVChuyen(DEFAULT_B_V_CHUYEN)
            .ketQuaDieuTri(DEFAULT_KET_QUA_DIEU_TRI)
            .lichSuChuyenKhoa(DEFAULT_LICH_SU_CHUYEN_KHOA);
        return dieuTri;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DieuTri createUpdatedEntity(EntityManager em) {
        DieuTri dieuTri = new DieuTri()
            .maDieuTri(UPDATED_MA_DIEU_TRI)
            .hoTen(UPDATED_HO_TEN)
            .maBenhAn(UPDATED_MA_BENH_AN)
            .tenBSDieuTri(UPDATED_TEN_BS_DIEU_TRI)
            .nguoiNha(UPDATED_NGUOI_NHA)
            .giuong(UPDATED_GIUONG)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .ngayVaoKhoa(UPDATED_NGAY_VAO_KHOA)
            .ngayRaVien(UPDATED_NGAY_RA_VIEN)
            .bVChuyen(UPDATED_B_V_CHUYEN)
            .ketQuaDieuTri(UPDATED_KET_QUA_DIEU_TRI)
            .lichSuChuyenKhoa(UPDATED_LICH_SU_CHUYEN_KHOA);
        return dieuTri;
    }

    @BeforeEach
    public void initTest() {
        dieuTri = createEntity(em);
    }

    @Test
    @Transactional
    void createDieuTri() throws Exception {
        int databaseSizeBeforeCreate = dieuTriRepository.findAll().size();
        // Create the DieuTri
        restDieuTriMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isCreated());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeCreate + 1);
        DieuTri testDieuTri = dieuTriList.get(dieuTriList.size() - 1);
        assertThat(testDieuTri.getMaDieuTri()).isEqualTo(DEFAULT_MA_DIEU_TRI);
        assertThat(testDieuTri.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testDieuTri.getMaBenhAn()).isEqualTo(DEFAULT_MA_BENH_AN);
        assertThat(testDieuTri.getTenBSDieuTri()).isEqualTo(DEFAULT_TEN_BS_DIEU_TRI);
        assertThat(testDieuTri.getNguoiNha()).isEqualTo(DEFAULT_NGUOI_NHA);
        assertThat(testDieuTri.getGiuong()).isEqualTo(DEFAULT_GIUONG);
        assertThat(testDieuTri.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testDieuTri.getNgayVaoKhoa()).isEqualTo(DEFAULT_NGAY_VAO_KHOA);
        assertThat(testDieuTri.getNgayRaVien()).isEqualTo(DEFAULT_NGAY_RA_VIEN);
        assertThat(testDieuTri.getbVChuyen()).isEqualTo(DEFAULT_B_V_CHUYEN);
        assertThat(testDieuTri.getKetQuaDieuTri()).isEqualTo(DEFAULT_KET_QUA_DIEU_TRI);
        assertThat(testDieuTri.getLichSuChuyenKhoa()).isEqualTo(DEFAULT_LICH_SU_CHUYEN_KHOA);
    }

    @Test
    @Transactional
    void createDieuTriWithExistingId() throws Exception {
        // Create the DieuTri with an existing ID
        dieuTri.setId(1L);

        int databaseSizeBeforeCreate = dieuTriRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDieuTriMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isBadRequest());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDieuTris() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        // Get all the dieuTriList
        restDieuTriMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dieuTri.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDieuTri").value(hasItem(DEFAULT_MA_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].maBenhAn").value(hasItem(DEFAULT_MA_BENH_AN)))
            .andExpect(jsonPath("$.[*].tenBSDieuTri").value(hasItem(DEFAULT_TEN_BS_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].nguoiNha").value(hasItem(DEFAULT_NGUOI_NHA)))
            .andExpect(jsonPath("$.[*].giuong").value(hasItem(DEFAULT_GIUONG)))
            .andExpect(jsonPath("$.[*].maTheBHYT").value(hasItem(DEFAULT_MA_THE_BHYT)))
            .andExpect(jsonPath("$.[*].ngayVaoKhoa").value(hasItem(sameInstant(DEFAULT_NGAY_VAO_KHOA))))
            .andExpect(jsonPath("$.[*].ngayRaVien").value(hasItem(sameInstant(DEFAULT_NGAY_RA_VIEN))))
            .andExpect(jsonPath("$.[*].bVChuyen").value(hasItem(DEFAULT_B_V_CHUYEN)))
            .andExpect(jsonPath("$.[*].ketQuaDieuTri").value(hasItem(DEFAULT_KET_QUA_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].lichSuChuyenKhoa").value(hasItem(DEFAULT_LICH_SU_CHUYEN_KHOA)));
    }

    @Test
    @Transactional
    void getDieuTri() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        // Get the dieuTri
        restDieuTriMockMvc
            .perform(get(ENTITY_API_URL_ID, dieuTri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dieuTri.getId().intValue()))
            .andExpect(jsonPath("$.maDieuTri").value(DEFAULT_MA_DIEU_TRI))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN))
            .andExpect(jsonPath("$.maBenhAn").value(DEFAULT_MA_BENH_AN))
            .andExpect(jsonPath("$.tenBSDieuTri").value(DEFAULT_TEN_BS_DIEU_TRI))
            .andExpect(jsonPath("$.nguoiNha").value(DEFAULT_NGUOI_NHA))
            .andExpect(jsonPath("$.giuong").value(DEFAULT_GIUONG))
            .andExpect(jsonPath("$.maTheBHYT").value(DEFAULT_MA_THE_BHYT))
            .andExpect(jsonPath("$.ngayVaoKhoa").value(sameInstant(DEFAULT_NGAY_VAO_KHOA)))
            .andExpect(jsonPath("$.ngayRaVien").value(sameInstant(DEFAULT_NGAY_RA_VIEN)))
            .andExpect(jsonPath("$.bVChuyen").value(DEFAULT_B_V_CHUYEN))
            .andExpect(jsonPath("$.ketQuaDieuTri").value(DEFAULT_KET_QUA_DIEU_TRI))
            .andExpect(jsonPath("$.lichSuChuyenKhoa").value(DEFAULT_LICH_SU_CHUYEN_KHOA));
    }

    @Test
    @Transactional
    void getNonExistingDieuTri() throws Exception {
        // Get the dieuTri
        restDieuTriMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDieuTri() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();

        // Update the dieuTri
        DieuTri updatedDieuTri = dieuTriRepository.findById(dieuTri.getId()).get();
        // Disconnect from session so that the updates on updatedDieuTri are not directly saved in db
        em.detach(updatedDieuTri);
        updatedDieuTri
            .maDieuTri(UPDATED_MA_DIEU_TRI)
            .hoTen(UPDATED_HO_TEN)
            .maBenhAn(UPDATED_MA_BENH_AN)
            .tenBSDieuTri(UPDATED_TEN_BS_DIEU_TRI)
            .nguoiNha(UPDATED_NGUOI_NHA)
            .giuong(UPDATED_GIUONG)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .ngayVaoKhoa(UPDATED_NGAY_VAO_KHOA)
            .ngayRaVien(UPDATED_NGAY_RA_VIEN)
            .bVChuyen(UPDATED_B_V_CHUYEN)
            .ketQuaDieuTri(UPDATED_KET_QUA_DIEU_TRI)
            .lichSuChuyenKhoa(UPDATED_LICH_SU_CHUYEN_KHOA);

        restDieuTriMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDieuTri.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDieuTri))
            )
            .andExpect(status().isOk());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
        DieuTri testDieuTri = dieuTriList.get(dieuTriList.size() - 1);
        assertThat(testDieuTri.getMaDieuTri()).isEqualTo(UPDATED_MA_DIEU_TRI);
        assertThat(testDieuTri.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testDieuTri.getMaBenhAn()).isEqualTo(UPDATED_MA_BENH_AN);
        assertThat(testDieuTri.getTenBSDieuTri()).isEqualTo(UPDATED_TEN_BS_DIEU_TRI);
        assertThat(testDieuTri.getNguoiNha()).isEqualTo(UPDATED_NGUOI_NHA);
        assertThat(testDieuTri.getGiuong()).isEqualTo(UPDATED_GIUONG);
        assertThat(testDieuTri.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testDieuTri.getNgayVaoKhoa()).isEqualTo(UPDATED_NGAY_VAO_KHOA);
        assertThat(testDieuTri.getNgayRaVien()).isEqualTo(UPDATED_NGAY_RA_VIEN);
        assertThat(testDieuTri.getbVChuyen()).isEqualTo(UPDATED_B_V_CHUYEN);
        assertThat(testDieuTri.getKetQuaDieuTri()).isEqualTo(UPDATED_KET_QUA_DIEU_TRI);
        assertThat(testDieuTri.getLichSuChuyenKhoa()).isEqualTo(UPDATED_LICH_SU_CHUYEN_KHOA);
    }

    @Test
    @Transactional
    void putNonExistingDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dieuTri.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isBadRequest());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isBadRequest());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDieuTriWithPatch() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();

        // Update the dieuTri using partial update
        DieuTri partialUpdatedDieuTri = new DieuTri();
        partialUpdatedDieuTri.setId(dieuTri.getId());

        partialUpdatedDieuTri
            .maBenhAn(UPDATED_MA_BENH_AN)
            .tenBSDieuTri(UPDATED_TEN_BS_DIEU_TRI)
            .nguoiNha(UPDATED_NGUOI_NHA)
            .giuong(UPDATED_GIUONG)
            .ngayVaoKhoa(UPDATED_NGAY_VAO_KHOA)
            .ngayRaVien(UPDATED_NGAY_RA_VIEN)
            .ketQuaDieuTri(UPDATED_KET_QUA_DIEU_TRI);

        restDieuTriMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDieuTri.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDieuTri))
            )
            .andExpect(status().isOk());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
        DieuTri testDieuTri = dieuTriList.get(dieuTriList.size() - 1);
        assertThat(testDieuTri.getMaDieuTri()).isEqualTo(DEFAULT_MA_DIEU_TRI);
        assertThat(testDieuTri.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testDieuTri.getMaBenhAn()).isEqualTo(UPDATED_MA_BENH_AN);
        assertThat(testDieuTri.getTenBSDieuTri()).isEqualTo(UPDATED_TEN_BS_DIEU_TRI);
        assertThat(testDieuTri.getNguoiNha()).isEqualTo(UPDATED_NGUOI_NHA);
        assertThat(testDieuTri.getGiuong()).isEqualTo(UPDATED_GIUONG);
        assertThat(testDieuTri.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testDieuTri.getNgayVaoKhoa()).isEqualTo(UPDATED_NGAY_VAO_KHOA);
        assertThat(testDieuTri.getNgayRaVien()).isEqualTo(UPDATED_NGAY_RA_VIEN);
        assertThat(testDieuTri.getbVChuyen()).isEqualTo(DEFAULT_B_V_CHUYEN);
        assertThat(testDieuTri.getKetQuaDieuTri()).isEqualTo(UPDATED_KET_QUA_DIEU_TRI);
        assertThat(testDieuTri.getLichSuChuyenKhoa()).isEqualTo(DEFAULT_LICH_SU_CHUYEN_KHOA);
    }

    @Test
    @Transactional
    void fullUpdateDieuTriWithPatch() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();

        // Update the dieuTri using partial update
        DieuTri partialUpdatedDieuTri = new DieuTri();
        partialUpdatedDieuTri.setId(dieuTri.getId());

        partialUpdatedDieuTri
            .maDieuTri(UPDATED_MA_DIEU_TRI)
            .hoTen(UPDATED_HO_TEN)
            .maBenhAn(UPDATED_MA_BENH_AN)
            .tenBSDieuTri(UPDATED_TEN_BS_DIEU_TRI)
            .nguoiNha(UPDATED_NGUOI_NHA)
            .giuong(UPDATED_GIUONG)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .ngayVaoKhoa(UPDATED_NGAY_VAO_KHOA)
            .ngayRaVien(UPDATED_NGAY_RA_VIEN)
            .bVChuyen(UPDATED_B_V_CHUYEN)
            .ketQuaDieuTri(UPDATED_KET_QUA_DIEU_TRI)
            .lichSuChuyenKhoa(UPDATED_LICH_SU_CHUYEN_KHOA);

        restDieuTriMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDieuTri.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDieuTri))
            )
            .andExpect(status().isOk());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
        DieuTri testDieuTri = dieuTriList.get(dieuTriList.size() - 1);
        assertThat(testDieuTri.getMaDieuTri()).isEqualTo(UPDATED_MA_DIEU_TRI);
        assertThat(testDieuTri.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testDieuTri.getMaBenhAn()).isEqualTo(UPDATED_MA_BENH_AN);
        assertThat(testDieuTri.getTenBSDieuTri()).isEqualTo(UPDATED_TEN_BS_DIEU_TRI);
        assertThat(testDieuTri.getNguoiNha()).isEqualTo(UPDATED_NGUOI_NHA);
        assertThat(testDieuTri.getGiuong()).isEqualTo(UPDATED_GIUONG);
        assertThat(testDieuTri.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testDieuTri.getNgayVaoKhoa()).isEqualTo(UPDATED_NGAY_VAO_KHOA);
        assertThat(testDieuTri.getNgayRaVien()).isEqualTo(UPDATED_NGAY_RA_VIEN);
        assertThat(testDieuTri.getbVChuyen()).isEqualTo(UPDATED_B_V_CHUYEN);
        assertThat(testDieuTri.getKetQuaDieuTri()).isEqualTo(UPDATED_KET_QUA_DIEU_TRI);
        assertThat(testDieuTri.getLichSuChuyenKhoa()).isEqualTo(UPDATED_LICH_SU_CHUYEN_KHOA);
    }

    @Test
    @Transactional
    void patchNonExistingDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dieuTri.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isBadRequest());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isBadRequest());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDieuTri() throws Exception {
        int databaseSizeBeforeUpdate = dieuTriRepository.findAll().size();
        dieuTri.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDieuTriMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dieuTri))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DieuTri in the database
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDieuTri() throws Exception {
        // Initialize the database
        dieuTriRepository.saveAndFlush(dieuTri);

        int databaseSizeBeforeDelete = dieuTriRepository.findAll().size();

        // Delete the dieuTri
        restDieuTriMockMvc
            .perform(delete(ENTITY_API_URL_ID, dieuTri.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DieuTri> dieuTriList = dieuTriRepository.findAll();
        assertThat(dieuTriList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
