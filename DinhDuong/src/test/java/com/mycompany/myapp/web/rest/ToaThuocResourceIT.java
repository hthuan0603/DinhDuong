package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ToaThuoc;
import com.mycompany.myapp.repository.ToaThuocRepository;
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
 * Integration tests for the {@link ToaThuocResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ToaThuocResourceIT {

    private static final String DEFAULT_I_CD_10 = "AAAAAAAAAA";
    private static final String UPDATED_I_CD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BA = "AAAAAAAAAA";
    private static final String UPDATED_MA_BA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_THUOC = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_THUOC = "BBBBBBBBBB";

    private static final String DEFAULT_DOI_TUONG = "AAAAAAAAAA";
    private static final String UPDATED_DOI_TUONG = "BBBBBBBBBB";

    private static final Float DEFAULT_TI_LE = 1F;
    private static final Float UPDATED_TI_LE = 2F;

    private static final Integer DEFAULT_SO_NGAY_HEN_TAI_KHAM = 1;
    private static final Integer UPDATED_SO_NGAY_HEN_TAI_KHAM = 2;

    private static final Boolean DEFAULT_CAP_PHIEU_HEN_KHAM = false;
    private static final Boolean UPDATED_CAP_PHIEU_HEN_KHAM = true;

    private static final String DEFAULT_LOI_DAN_BAC_SI = "AAAAAAAAAA";
    private static final String UPDATED_LOI_DAN_BAC_SI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_CHI_DINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_CHI_DINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_DUNG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_DUNG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SO_NGAYKE_DON = 1;
    private static final Integer UPDATED_SO_NGAYKE_DON = 2;

    private static final Boolean DEFAULT_KHO_THUOC = false;
    private static final Boolean UPDATED_KHO_THUOC = true;

    private static final String ENTITY_API_URL = "/api/toa-thuocs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ToaThuocRepository toaThuocRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restToaThuocMockMvc;

    private ToaThuoc toaThuoc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToaThuoc createEntity(EntityManager em) {
        ToaThuoc toaThuoc = new ToaThuoc()
            .iCD10(DEFAULT_I_CD_10)
            .maBA(DEFAULT_MA_BA)
            .maBN(DEFAULT_MA_BN)
            .loaiThuoc(DEFAULT_LOAI_THUOC)
            .doiTuong(DEFAULT_DOI_TUONG)
            .tiLe(DEFAULT_TI_LE)
            .soNgayHenTaiKham(DEFAULT_SO_NGAY_HEN_TAI_KHAM)
            .capPhieuHenKham(DEFAULT_CAP_PHIEU_HEN_KHAM)
            .loiDanBacSi(DEFAULT_LOI_DAN_BAC_SI)
            .ngayChiDinh(DEFAULT_NGAY_CHI_DINH)
            .ngayDung(DEFAULT_NGAY_DUNG)
            .soNgaykeDon(DEFAULT_SO_NGAYKE_DON)
            .khoThuoc(DEFAULT_KHO_THUOC);
        return toaThuoc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToaThuoc createUpdatedEntity(EntityManager em) {
        ToaThuoc toaThuoc = new ToaThuoc()
            .iCD10(UPDATED_I_CD_10)
            .maBA(UPDATED_MA_BA)
            .maBN(UPDATED_MA_BN)
            .loaiThuoc(UPDATED_LOAI_THUOC)
            .doiTuong(UPDATED_DOI_TUONG)
            .tiLe(UPDATED_TI_LE)
            .soNgayHenTaiKham(UPDATED_SO_NGAY_HEN_TAI_KHAM)
            .capPhieuHenKham(UPDATED_CAP_PHIEU_HEN_KHAM)
            .loiDanBacSi(UPDATED_LOI_DAN_BAC_SI)
            .ngayChiDinh(UPDATED_NGAY_CHI_DINH)
            .ngayDung(UPDATED_NGAY_DUNG)
            .soNgaykeDon(UPDATED_SO_NGAYKE_DON)
            .khoThuoc(UPDATED_KHO_THUOC);
        return toaThuoc;
    }

    @BeforeEach
    public void initTest() {
        toaThuoc = createEntity(em);
    }

    @Test
    @Transactional
    void createToaThuoc() throws Exception {
        int databaseSizeBeforeCreate = toaThuocRepository.findAll().size();
        // Create the ToaThuoc
        restToaThuocMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isCreated());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeCreate + 1);
        ToaThuoc testToaThuoc = toaThuocList.get(toaThuocList.size() - 1);
        assertThat(testToaThuoc.getiCD10()).isEqualTo(DEFAULT_I_CD_10);
        assertThat(testToaThuoc.getMaBA()).isEqualTo(DEFAULT_MA_BA);
        assertThat(testToaThuoc.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testToaThuoc.getLoaiThuoc()).isEqualTo(DEFAULT_LOAI_THUOC);
        assertThat(testToaThuoc.getDoiTuong()).isEqualTo(DEFAULT_DOI_TUONG);
        assertThat(testToaThuoc.getTiLe()).isEqualTo(DEFAULT_TI_LE);
        assertThat(testToaThuoc.getSoNgayHenTaiKham()).isEqualTo(DEFAULT_SO_NGAY_HEN_TAI_KHAM);
        assertThat(testToaThuoc.getCapPhieuHenKham()).isEqualTo(DEFAULT_CAP_PHIEU_HEN_KHAM);
        assertThat(testToaThuoc.getLoiDanBacSi()).isEqualTo(DEFAULT_LOI_DAN_BAC_SI);
        assertThat(testToaThuoc.getNgayChiDinh()).isEqualTo(DEFAULT_NGAY_CHI_DINH);
        assertThat(testToaThuoc.getNgayDung()).isEqualTo(DEFAULT_NGAY_DUNG);
        assertThat(testToaThuoc.getSoNgaykeDon()).isEqualTo(DEFAULT_SO_NGAYKE_DON);
        assertThat(testToaThuoc.getKhoThuoc()).isEqualTo(DEFAULT_KHO_THUOC);
    }

    @Test
    @Transactional
    void createToaThuocWithExistingId() throws Exception {
        // Create the ToaThuoc with an existing ID
        toaThuoc.setId(1L);

        int databaseSizeBeforeCreate = toaThuocRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restToaThuocMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllToaThuocs() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        // Get all the toaThuocList
        restToaThuocMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toaThuoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].iCD10").value(hasItem(DEFAULT_I_CD_10)))
            .andExpect(jsonPath("$.[*].maBA").value(hasItem(DEFAULT_MA_BA)))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].loaiThuoc").value(hasItem(DEFAULT_LOAI_THUOC)))
            .andExpect(jsonPath("$.[*].doiTuong").value(hasItem(DEFAULT_DOI_TUONG)))
            .andExpect(jsonPath("$.[*].tiLe").value(hasItem(DEFAULT_TI_LE.doubleValue())))
            .andExpect(jsonPath("$.[*].soNgayHenTaiKham").value(hasItem(DEFAULT_SO_NGAY_HEN_TAI_KHAM)))
            .andExpect(jsonPath("$.[*].capPhieuHenKham").value(hasItem(DEFAULT_CAP_PHIEU_HEN_KHAM.booleanValue())))
            .andExpect(jsonPath("$.[*].loiDanBacSi").value(hasItem(DEFAULT_LOI_DAN_BAC_SI)))
            .andExpect(jsonPath("$.[*].ngayChiDinh").value(hasItem(sameInstant(DEFAULT_NGAY_CHI_DINH))))
            .andExpect(jsonPath("$.[*].ngayDung").value(hasItem(sameInstant(DEFAULT_NGAY_DUNG))))
            .andExpect(jsonPath("$.[*].soNgaykeDon").value(hasItem(DEFAULT_SO_NGAYKE_DON)))
            .andExpect(jsonPath("$.[*].khoThuoc").value(hasItem(DEFAULT_KHO_THUOC.booleanValue())));
    }

    @Test
    @Transactional
    void getToaThuoc() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        // Get the toaThuoc
        restToaThuocMockMvc
            .perform(get(ENTITY_API_URL_ID, toaThuoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(toaThuoc.getId().intValue()))
            .andExpect(jsonPath("$.iCD10").value(DEFAULT_I_CD_10))
            .andExpect(jsonPath("$.maBA").value(DEFAULT_MA_BA))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.loaiThuoc").value(DEFAULT_LOAI_THUOC))
            .andExpect(jsonPath("$.doiTuong").value(DEFAULT_DOI_TUONG))
            .andExpect(jsonPath("$.tiLe").value(DEFAULT_TI_LE.doubleValue()))
            .andExpect(jsonPath("$.soNgayHenTaiKham").value(DEFAULT_SO_NGAY_HEN_TAI_KHAM))
            .andExpect(jsonPath("$.capPhieuHenKham").value(DEFAULT_CAP_PHIEU_HEN_KHAM.booleanValue()))
            .andExpect(jsonPath("$.loiDanBacSi").value(DEFAULT_LOI_DAN_BAC_SI))
            .andExpect(jsonPath("$.ngayChiDinh").value(sameInstant(DEFAULT_NGAY_CHI_DINH)))
            .andExpect(jsonPath("$.ngayDung").value(sameInstant(DEFAULT_NGAY_DUNG)))
            .andExpect(jsonPath("$.soNgaykeDon").value(DEFAULT_SO_NGAYKE_DON))
            .andExpect(jsonPath("$.khoThuoc").value(DEFAULT_KHO_THUOC.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingToaThuoc() throws Exception {
        // Get the toaThuoc
        restToaThuocMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewToaThuoc() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();

        // Update the toaThuoc
        ToaThuoc updatedToaThuoc = toaThuocRepository.findById(toaThuoc.getId()).get();
        // Disconnect from session so that the updates on updatedToaThuoc are not directly saved in db
        em.detach(updatedToaThuoc);
        updatedToaThuoc
            .iCD10(UPDATED_I_CD_10)
            .maBA(UPDATED_MA_BA)
            .maBN(UPDATED_MA_BN)
            .loaiThuoc(UPDATED_LOAI_THUOC)
            .doiTuong(UPDATED_DOI_TUONG)
            .tiLe(UPDATED_TI_LE)
            .soNgayHenTaiKham(UPDATED_SO_NGAY_HEN_TAI_KHAM)
            .capPhieuHenKham(UPDATED_CAP_PHIEU_HEN_KHAM)
            .loiDanBacSi(UPDATED_LOI_DAN_BAC_SI)
            .ngayChiDinh(UPDATED_NGAY_CHI_DINH)
            .ngayDung(UPDATED_NGAY_DUNG)
            .soNgaykeDon(UPDATED_SO_NGAYKE_DON)
            .khoThuoc(UPDATED_KHO_THUOC);

        restToaThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedToaThuoc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedToaThuoc))
            )
            .andExpect(status().isOk());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
        ToaThuoc testToaThuoc = toaThuocList.get(toaThuocList.size() - 1);
        assertThat(testToaThuoc.getiCD10()).isEqualTo(UPDATED_I_CD_10);
        assertThat(testToaThuoc.getMaBA()).isEqualTo(UPDATED_MA_BA);
        assertThat(testToaThuoc.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testToaThuoc.getLoaiThuoc()).isEqualTo(UPDATED_LOAI_THUOC);
        assertThat(testToaThuoc.getDoiTuong()).isEqualTo(UPDATED_DOI_TUONG);
        assertThat(testToaThuoc.getTiLe()).isEqualTo(UPDATED_TI_LE);
        assertThat(testToaThuoc.getSoNgayHenTaiKham()).isEqualTo(UPDATED_SO_NGAY_HEN_TAI_KHAM);
        assertThat(testToaThuoc.getCapPhieuHenKham()).isEqualTo(UPDATED_CAP_PHIEU_HEN_KHAM);
        assertThat(testToaThuoc.getLoiDanBacSi()).isEqualTo(UPDATED_LOI_DAN_BAC_SI);
        assertThat(testToaThuoc.getNgayChiDinh()).isEqualTo(UPDATED_NGAY_CHI_DINH);
        assertThat(testToaThuoc.getNgayDung()).isEqualTo(UPDATED_NGAY_DUNG);
        assertThat(testToaThuoc.getSoNgaykeDon()).isEqualTo(UPDATED_SO_NGAYKE_DON);
        assertThat(testToaThuoc.getKhoThuoc()).isEqualTo(UPDATED_KHO_THUOC);
    }

    @Test
    @Transactional
    void putNonExistingToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toaThuoc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateToaThuocWithPatch() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();

        // Update the toaThuoc using partial update
        ToaThuoc partialUpdatedToaThuoc = new ToaThuoc();
        partialUpdatedToaThuoc.setId(toaThuoc.getId());

        partialUpdatedToaThuoc
            .maBA(UPDATED_MA_BA)
            .loaiThuoc(UPDATED_LOAI_THUOC)
            .soNgayHenTaiKham(UPDATED_SO_NGAY_HEN_TAI_KHAM)
            .ngayChiDinh(UPDATED_NGAY_CHI_DINH)
            .ngayDung(UPDATED_NGAY_DUNG)
            .soNgaykeDon(UPDATED_SO_NGAYKE_DON)
            .khoThuoc(UPDATED_KHO_THUOC);

        restToaThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToaThuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToaThuoc))
            )
            .andExpect(status().isOk());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
        ToaThuoc testToaThuoc = toaThuocList.get(toaThuocList.size() - 1);
        assertThat(testToaThuoc.getiCD10()).isEqualTo(DEFAULT_I_CD_10);
        assertThat(testToaThuoc.getMaBA()).isEqualTo(UPDATED_MA_BA);
        assertThat(testToaThuoc.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testToaThuoc.getLoaiThuoc()).isEqualTo(UPDATED_LOAI_THUOC);
        assertThat(testToaThuoc.getDoiTuong()).isEqualTo(DEFAULT_DOI_TUONG);
        assertThat(testToaThuoc.getTiLe()).isEqualTo(DEFAULT_TI_LE);
        assertThat(testToaThuoc.getSoNgayHenTaiKham()).isEqualTo(UPDATED_SO_NGAY_HEN_TAI_KHAM);
        assertThat(testToaThuoc.getCapPhieuHenKham()).isEqualTo(DEFAULT_CAP_PHIEU_HEN_KHAM);
        assertThat(testToaThuoc.getLoiDanBacSi()).isEqualTo(DEFAULT_LOI_DAN_BAC_SI);
        assertThat(testToaThuoc.getNgayChiDinh()).isEqualTo(UPDATED_NGAY_CHI_DINH);
        assertThat(testToaThuoc.getNgayDung()).isEqualTo(UPDATED_NGAY_DUNG);
        assertThat(testToaThuoc.getSoNgaykeDon()).isEqualTo(UPDATED_SO_NGAYKE_DON);
        assertThat(testToaThuoc.getKhoThuoc()).isEqualTo(UPDATED_KHO_THUOC);
    }

    @Test
    @Transactional
    void fullUpdateToaThuocWithPatch() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();

        // Update the toaThuoc using partial update
        ToaThuoc partialUpdatedToaThuoc = new ToaThuoc();
        partialUpdatedToaThuoc.setId(toaThuoc.getId());

        partialUpdatedToaThuoc
            .iCD10(UPDATED_I_CD_10)
            .maBA(UPDATED_MA_BA)
            .maBN(UPDATED_MA_BN)
            .loaiThuoc(UPDATED_LOAI_THUOC)
            .doiTuong(UPDATED_DOI_TUONG)
            .tiLe(UPDATED_TI_LE)
            .soNgayHenTaiKham(UPDATED_SO_NGAY_HEN_TAI_KHAM)
            .capPhieuHenKham(UPDATED_CAP_PHIEU_HEN_KHAM)
            .loiDanBacSi(UPDATED_LOI_DAN_BAC_SI)
            .ngayChiDinh(UPDATED_NGAY_CHI_DINH)
            .ngayDung(UPDATED_NGAY_DUNG)
            .soNgaykeDon(UPDATED_SO_NGAYKE_DON)
            .khoThuoc(UPDATED_KHO_THUOC);

        restToaThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToaThuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToaThuoc))
            )
            .andExpect(status().isOk());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
        ToaThuoc testToaThuoc = toaThuocList.get(toaThuocList.size() - 1);
        assertThat(testToaThuoc.getiCD10()).isEqualTo(UPDATED_I_CD_10);
        assertThat(testToaThuoc.getMaBA()).isEqualTo(UPDATED_MA_BA);
        assertThat(testToaThuoc.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testToaThuoc.getLoaiThuoc()).isEqualTo(UPDATED_LOAI_THUOC);
        assertThat(testToaThuoc.getDoiTuong()).isEqualTo(UPDATED_DOI_TUONG);
        assertThat(testToaThuoc.getTiLe()).isEqualTo(UPDATED_TI_LE);
        assertThat(testToaThuoc.getSoNgayHenTaiKham()).isEqualTo(UPDATED_SO_NGAY_HEN_TAI_KHAM);
        assertThat(testToaThuoc.getCapPhieuHenKham()).isEqualTo(UPDATED_CAP_PHIEU_HEN_KHAM);
        assertThat(testToaThuoc.getLoiDanBacSi()).isEqualTo(UPDATED_LOI_DAN_BAC_SI);
        assertThat(testToaThuoc.getNgayChiDinh()).isEqualTo(UPDATED_NGAY_CHI_DINH);
        assertThat(testToaThuoc.getNgayDung()).isEqualTo(UPDATED_NGAY_DUNG);
        assertThat(testToaThuoc.getSoNgaykeDon()).isEqualTo(UPDATED_SO_NGAYKE_DON);
        assertThat(testToaThuoc.getKhoThuoc()).isEqualTo(UPDATED_KHO_THUOC);
    }

    @Test
    @Transactional
    void patchNonExistingToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, toaThuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamToaThuoc() throws Exception {
        int databaseSizeBeforeUpdate = toaThuocRepository.findAll().size();
        toaThuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToaThuocMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toaThuoc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToaThuoc in the database
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteToaThuoc() throws Exception {
        // Initialize the database
        toaThuocRepository.saveAndFlush(toaThuoc);

        int databaseSizeBeforeDelete = toaThuocRepository.findAll().size();

        // Delete the toaThuoc
        restToaThuocMockMvc
            .perform(delete(ENTITY_API_URL_ID, toaThuoc.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ToaThuoc> toaThuocList = toaThuocRepository.findAll();
        assertThat(toaThuocList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
