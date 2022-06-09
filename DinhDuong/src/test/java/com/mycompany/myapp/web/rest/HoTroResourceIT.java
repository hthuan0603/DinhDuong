package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.HoTro;
import com.mycompany.myapp.repository.HoTroRepository;
import com.mycompany.myapp.service.dto.HoTroDTO;
import com.mycompany.myapp.service.mapper.HoTroMapper;
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
 * Integration tests for the {@link HoTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HoTroResourceIT {

    private static final Integer DEFAULT_MA_NOI_DUNG = 1;
    private static final Integer UPDATED_MA_NOI_DUNG = 2;

    private static final ZonedDateTime DEFAULT_NGAY_TAO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_TAO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NHAN_VIEN_CV = "AAAAAAAAAA";
    private static final String UPDATED_NHAN_VIEN_CV = "BBBBBBBBBB";

    private static final String DEFAULT_KY_THUAT_HO_TRO = "AAAAAAAAAA";
    private static final String UPDATED_KY_THUAT_HO_TRO = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_TU_HO_TRO = "AAAAAAAAAA";
    private static final String UPDATED_VAT_TU_HO_TRO = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_BN_KHAM_DIEU_TRI = 1;
    private static final Integer UPDATED_SO_BN_KHAM_DIEU_TRI = 2;

    private static final Integer DEFAULT_SO_BN_PHAU_THUAT = 1;
    private static final Integer UPDATED_SO_BN_PHAU_THUAT = 2;

    private static final Integer DEFAULT_SO_CAN_BO_CHUYEN_GIAO = 1;
    private static final Integer UPDATED_SO_CAN_BO_CHUYEN_GIAO = 2;

    private static final String DEFAULT_KET_QUA_CONG_TAC = "AAAAAAAAAA";
    private static final String UPDATED_KET_QUA_CONG_TAC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ho-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HoTroRepository hoTroRepository;

    @Autowired
    private HoTroMapper hoTroMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHoTroMockMvc;

    private HoTro hoTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoTro createEntity(EntityManager em) {
        HoTro hoTro = new HoTro()
            .maNoiDung(DEFAULT_MA_NOI_DUNG)
            .ngayTao(DEFAULT_NGAY_TAO)
            .nhanVienCV(DEFAULT_NHAN_VIEN_CV)
            .kyThuatHoTro(DEFAULT_KY_THUAT_HO_TRO)
            .vatTuHoTro(DEFAULT_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(DEFAULT_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(DEFAULT_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(DEFAULT_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(DEFAULT_KET_QUA_CONG_TAC);
        return hoTro;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HoTro createUpdatedEntity(EntityManager em) {
        HoTro hoTro = new HoTro()
            .maNoiDung(UPDATED_MA_NOI_DUNG)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVienCV(UPDATED_NHAN_VIEN_CV)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC);
        return hoTro;
    }

    @BeforeEach
    public void initTest() {
        hoTro = createEntity(em);
    }

    @Test
    @Transactional
    void createHoTro() throws Exception {
        int databaseSizeBeforeCreate = hoTroRepository.findAll().size();
        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);
        restHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeCreate + 1);
        HoTro testHoTro = hoTroList.get(hoTroList.size() - 1);
        assertThat(testHoTro.getMaNoiDung()).isEqualTo(DEFAULT_MA_NOI_DUNG);
        assertThat(testHoTro.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testHoTro.getNhanVienCV()).isEqualTo(DEFAULT_NHAN_VIEN_CV);
        assertThat(testHoTro.getKyThuatHoTro()).isEqualTo(DEFAULT_KY_THUAT_HO_TRO);
        assertThat(testHoTro.getVatTuHoTro()).isEqualTo(DEFAULT_VAT_TU_HO_TRO);
        assertThat(testHoTro.getSoBnKhamDieuTri()).isEqualTo(DEFAULT_SO_BN_KHAM_DIEU_TRI);
        assertThat(testHoTro.getSoBnPhauThuat()).isEqualTo(DEFAULT_SO_BN_PHAU_THUAT);
        assertThat(testHoTro.getSoCanBoChuyenGiao()).isEqualTo(DEFAULT_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testHoTro.getKetQuaCongTac()).isEqualTo(DEFAULT_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void createHoTroWithExistingId() throws Exception {
        // Create the HoTro with an existing ID
        hoTro.setId(1L);
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        int databaseSizeBeforeCreate = hoTroRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoTroMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHoTros() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        // Get all the hoTroList
        restHoTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoTro.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNoiDung").value(hasItem(DEFAULT_MA_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(sameInstant(DEFAULT_NGAY_TAO))))
            .andExpect(jsonPath("$.[*].nhanVienCV").value(hasItem(DEFAULT_NHAN_VIEN_CV)))
            .andExpect(jsonPath("$.[*].kyThuatHoTro").value(hasItem(DEFAULT_KY_THUAT_HO_TRO)))
            .andExpect(jsonPath("$.[*].vatTuHoTro").value(hasItem(DEFAULT_VAT_TU_HO_TRO)))
            .andExpect(jsonPath("$.[*].soBnKhamDieuTri").value(hasItem(DEFAULT_SO_BN_KHAM_DIEU_TRI)))
            .andExpect(jsonPath("$.[*].soBnPhauThuat").value(hasItem(DEFAULT_SO_BN_PHAU_THUAT)))
            .andExpect(jsonPath("$.[*].soCanBoChuyenGiao").value(hasItem(DEFAULT_SO_CAN_BO_CHUYEN_GIAO)))
            .andExpect(jsonPath("$.[*].ketQuaCongTac").value(hasItem(DEFAULT_KET_QUA_CONG_TAC)));
    }

    @Test
    @Transactional
    void getHoTro() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        // Get the hoTro
        restHoTroMockMvc
            .perform(get(ENTITY_API_URL_ID, hoTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hoTro.getId().intValue()))
            .andExpect(jsonPath("$.maNoiDung").value(DEFAULT_MA_NOI_DUNG))
            .andExpect(jsonPath("$.ngayTao").value(sameInstant(DEFAULT_NGAY_TAO)))
            .andExpect(jsonPath("$.nhanVienCV").value(DEFAULT_NHAN_VIEN_CV))
            .andExpect(jsonPath("$.kyThuatHoTro").value(DEFAULT_KY_THUAT_HO_TRO))
            .andExpect(jsonPath("$.vatTuHoTro").value(DEFAULT_VAT_TU_HO_TRO))
            .andExpect(jsonPath("$.soBnKhamDieuTri").value(DEFAULT_SO_BN_KHAM_DIEU_TRI))
            .andExpect(jsonPath("$.soBnPhauThuat").value(DEFAULT_SO_BN_PHAU_THUAT))
            .andExpect(jsonPath("$.soCanBoChuyenGiao").value(DEFAULT_SO_CAN_BO_CHUYEN_GIAO))
            .andExpect(jsonPath("$.ketQuaCongTac").value(DEFAULT_KET_QUA_CONG_TAC));
    }

    @Test
    @Transactional
    void getNonExistingHoTro() throws Exception {
        // Get the hoTro
        restHoTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHoTro() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();

        // Update the hoTro
        HoTro updatedHoTro = hoTroRepository.findById(hoTro.getId()).get();
        // Disconnect from session so that the updates on updatedHoTro are not directly saved in db
        em.detach(updatedHoTro);
        updatedHoTro
            .maNoiDung(UPDATED_MA_NOI_DUNG)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVienCV(UPDATED_NHAN_VIEN_CV)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC);
        HoTroDTO hoTroDTO = hoTroMapper.toDto(updatedHoTro);

        restHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
        HoTro testHoTro = hoTroList.get(hoTroList.size() - 1);
        assertThat(testHoTro.getMaNoiDung()).isEqualTo(UPDATED_MA_NOI_DUNG);
        assertThat(testHoTro.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testHoTro.getNhanVienCV()).isEqualTo(UPDATED_NHAN_VIEN_CV);
        assertThat(testHoTro.getKyThuatHoTro()).isEqualTo(UPDATED_KY_THUAT_HO_TRO);
        assertThat(testHoTro.getVatTuHoTro()).isEqualTo(UPDATED_VAT_TU_HO_TRO);
        assertThat(testHoTro.getSoBnKhamDieuTri()).isEqualTo(UPDATED_SO_BN_KHAM_DIEU_TRI);
        assertThat(testHoTro.getSoBnPhauThuat()).isEqualTo(UPDATED_SO_BN_PHAU_THUAT);
        assertThat(testHoTro.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testHoTro.getKetQuaCongTac()).isEqualTo(UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void putNonExistingHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hoTroDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHoTroWithPatch() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();

        // Update the hoTro using partial update
        HoTro partialUpdatedHoTro = new HoTro();
        partialUpdatedHoTro.setId(hoTro.getId());

        partialUpdatedHoTro
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVienCV(UPDATED_NHAN_VIEN_CV)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO);

        restHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoTro))
            )
            .andExpect(status().isOk());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
        HoTro testHoTro = hoTroList.get(hoTroList.size() - 1);
        assertThat(testHoTro.getMaNoiDung()).isEqualTo(DEFAULT_MA_NOI_DUNG);
        assertThat(testHoTro.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testHoTro.getNhanVienCV()).isEqualTo(UPDATED_NHAN_VIEN_CV);
        assertThat(testHoTro.getKyThuatHoTro()).isEqualTo(DEFAULT_KY_THUAT_HO_TRO);
        assertThat(testHoTro.getVatTuHoTro()).isEqualTo(UPDATED_VAT_TU_HO_TRO);
        assertThat(testHoTro.getSoBnKhamDieuTri()).isEqualTo(DEFAULT_SO_BN_KHAM_DIEU_TRI);
        assertThat(testHoTro.getSoBnPhauThuat()).isEqualTo(UPDATED_SO_BN_PHAU_THUAT);
        assertThat(testHoTro.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testHoTro.getKetQuaCongTac()).isEqualTo(DEFAULT_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void fullUpdateHoTroWithPatch() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();

        // Update the hoTro using partial update
        HoTro partialUpdatedHoTro = new HoTro();
        partialUpdatedHoTro.setId(hoTro.getId());

        partialUpdatedHoTro
            .maNoiDung(UPDATED_MA_NOI_DUNG)
            .ngayTao(UPDATED_NGAY_TAO)
            .nhanVienCV(UPDATED_NHAN_VIEN_CV)
            .kyThuatHoTro(UPDATED_KY_THUAT_HO_TRO)
            .vatTuHoTro(UPDATED_VAT_TU_HO_TRO)
            .soBnKhamDieuTri(UPDATED_SO_BN_KHAM_DIEU_TRI)
            .soBnPhauThuat(UPDATED_SO_BN_PHAU_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO)
            .ketQuaCongTac(UPDATED_KET_QUA_CONG_TAC);

        restHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoTro.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHoTro))
            )
            .andExpect(status().isOk());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
        HoTro testHoTro = hoTroList.get(hoTroList.size() - 1);
        assertThat(testHoTro.getMaNoiDung()).isEqualTo(UPDATED_MA_NOI_DUNG);
        assertThat(testHoTro.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testHoTro.getNhanVienCV()).isEqualTo(UPDATED_NHAN_VIEN_CV);
        assertThat(testHoTro.getKyThuatHoTro()).isEqualTo(UPDATED_KY_THUAT_HO_TRO);
        assertThat(testHoTro.getVatTuHoTro()).isEqualTo(UPDATED_VAT_TU_HO_TRO);
        assertThat(testHoTro.getSoBnKhamDieuTri()).isEqualTo(UPDATED_SO_BN_KHAM_DIEU_TRI);
        assertThat(testHoTro.getSoBnPhauThuat()).isEqualTo(UPDATED_SO_BN_PHAU_THUAT);
        assertThat(testHoTro.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        assertThat(testHoTro.getKetQuaCongTac()).isEqualTo(UPDATED_KET_QUA_CONG_TAC);
    }

    @Test
    @Transactional
    void patchNonExistingHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hoTroDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHoTro() throws Exception {
        int databaseSizeBeforeUpdate = hoTroRepository.findAll().size();
        hoTro.setId(count.incrementAndGet());

        // Create the HoTro
        HoTroDTO hoTroDTO = hoTroMapper.toDto(hoTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoTroMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hoTroDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HoTro in the database
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHoTro() throws Exception {
        // Initialize the database
        hoTroRepository.saveAndFlush(hoTro);

        int databaseSizeBeforeDelete = hoTroRepository.findAll().size();

        // Delete the hoTro
        restHoTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, hoTro.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HoTro> hoTroList = hoTroRepository.findAll();
        assertThat(hoTroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
