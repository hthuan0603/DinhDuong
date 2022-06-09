package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.BenhNhan;
import com.mycompany.myapp.repository.BenhNhanRepository;
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
 * Integration tests for the {@link BenhNhanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BenhNhanResourceIT {

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BN = "AAAAAAAAAA";
    private static final String UPDATED_MA_BN = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_SINH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_SINH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_GIOI_TINH = false;
    private static final Boolean UPDATED_GIOI_TINH = true;

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_NGHE_NGHIEP = "AAAAAAAAAA";
    private static final String UPDATED_NGHE_NGHIEP = "BBBBBBBBBB";

    private static final Float DEFAULT_CHIEU_CAO = 1F;
    private static final Float UPDATED_CHIEU_CAO = 2F;

    private static final Float DEFAULT_CAN_HT = 1F;
    private static final Float UPDATED_CAN_HT = 2F;

    private static final Float DEFAULT_CAN_TC = 1F;
    private static final Float UPDATED_CAN_TC = 2F;

    private static final Float DEFAULT_VONG_CT = 1F;
    private static final Float UPDATED_VONG_CT = 2F;

    private static final Float DEFAULT_B_MI = 1F;
    private static final Float UPDATED_B_MI = 2F;

    private static final String DEFAULT_MA_THE_BHYT = "AAAAAAAAAA";
    private static final String UPDATED_MA_THE_BHYT = "BBBBBBBBBB";

    private static final Integer DEFAULT_S_DT = 1;
    private static final Integer UPDATED_S_DT = 2;

    private static final String ENTITY_API_URL = "/api/benh-nhans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBenhNhanMockMvc;

    private BenhNhan benhNhan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenhNhan createEntity(EntityManager em) {
        BenhNhan benhNhan = new BenhNhan()
            .hoTen(DEFAULT_HO_TEN)
            .maBN(DEFAULT_MA_BN)
            .ngaySinh(DEFAULT_NGAY_SINH)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .diaChi(DEFAULT_DIA_CHI)
            .ngheNghiep(DEFAULT_NGHE_NGHIEP)
            .chieuCao(DEFAULT_CHIEU_CAO)
            .canHT(DEFAULT_CAN_HT)
            .canTC(DEFAULT_CAN_TC)
            .vongCT(DEFAULT_VONG_CT)
            .bMI(DEFAULT_B_MI)
            .maTheBHYT(DEFAULT_MA_THE_BHYT)
            .sDT(DEFAULT_S_DT);
        return benhNhan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BenhNhan createUpdatedEntity(EntityManager em) {
        BenhNhan benhNhan = new BenhNhan()
            .hoTen(UPDATED_HO_TEN)
            .maBN(UPDATED_MA_BN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .diaChi(UPDATED_DIA_CHI)
            .ngheNghiep(UPDATED_NGHE_NGHIEP)
            .chieuCao(UPDATED_CHIEU_CAO)
            .canHT(UPDATED_CAN_HT)
            .canTC(UPDATED_CAN_TC)
            .vongCT(UPDATED_VONG_CT)
            .bMI(UPDATED_B_MI)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .sDT(UPDATED_S_DT);
        return benhNhan;
    }

    @BeforeEach
    public void initTest() {
        benhNhan = createEntity(em);
    }

    @Test
    @Transactional
    void createBenhNhan() throws Exception {
        int databaseSizeBeforeCreate = benhNhanRepository.findAll().size();
        // Create the BenhNhan
        restBenhNhanMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isCreated());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeCreate + 1);
        BenhNhan testBenhNhan = benhNhanList.get(benhNhanList.size() - 1);
        assertThat(testBenhNhan.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testBenhNhan.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testBenhNhan.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testBenhNhan.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testBenhNhan.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testBenhNhan.getNgheNghiep()).isEqualTo(DEFAULT_NGHE_NGHIEP);
        assertThat(testBenhNhan.getChieuCao()).isEqualTo(DEFAULT_CHIEU_CAO);
        assertThat(testBenhNhan.getCanHT()).isEqualTo(DEFAULT_CAN_HT);
        assertThat(testBenhNhan.getCanTC()).isEqualTo(DEFAULT_CAN_TC);
        assertThat(testBenhNhan.getVongCT()).isEqualTo(DEFAULT_VONG_CT);
        assertThat(testBenhNhan.getbMI()).isEqualTo(DEFAULT_B_MI);
        assertThat(testBenhNhan.getMaTheBHYT()).isEqualTo(DEFAULT_MA_THE_BHYT);
        assertThat(testBenhNhan.getsDT()).isEqualTo(DEFAULT_S_DT);
    }

    @Test
    @Transactional
    void createBenhNhanWithExistingId() throws Exception {
        // Create the BenhNhan with an existing ID
        benhNhan.setId(1L);

        int databaseSizeBeforeCreate = benhNhanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBenhNhanMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBenhNhans() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        // Get all the benhNhanList
        restBenhNhanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(benhNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].maBN").value(hasItem(DEFAULT_MA_BN)))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(sameInstant(DEFAULT_NGAY_SINH))))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.booleanValue())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].ngheNghiep").value(hasItem(DEFAULT_NGHE_NGHIEP)))
            .andExpect(jsonPath("$.[*].chieuCao").value(hasItem(DEFAULT_CHIEU_CAO.doubleValue())))
            .andExpect(jsonPath("$.[*].canHT").value(hasItem(DEFAULT_CAN_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].canTC").value(hasItem(DEFAULT_CAN_TC.doubleValue())))
            .andExpect(jsonPath("$.[*].vongCT").value(hasItem(DEFAULT_VONG_CT.doubleValue())))
            .andExpect(jsonPath("$.[*].bMI").value(hasItem(DEFAULT_B_MI.doubleValue())))
            .andExpect(jsonPath("$.[*].maTheBHYT").value(hasItem(DEFAULT_MA_THE_BHYT)))
            .andExpect(jsonPath("$.[*].sDT").value(hasItem(DEFAULT_S_DT)));
    }

    @Test
    @Transactional
    void getBenhNhan() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        // Get the benhNhan
        restBenhNhanMockMvc
            .perform(get(ENTITY_API_URL_ID, benhNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(benhNhan.getId().intValue()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN))
            .andExpect(jsonPath("$.maBN").value(DEFAULT_MA_BN))
            .andExpect(jsonPath("$.ngaySinh").value(sameInstant(DEFAULT_NGAY_SINH)))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.booleanValue()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.ngheNghiep").value(DEFAULT_NGHE_NGHIEP))
            .andExpect(jsonPath("$.chieuCao").value(DEFAULT_CHIEU_CAO.doubleValue()))
            .andExpect(jsonPath("$.canHT").value(DEFAULT_CAN_HT.doubleValue()))
            .andExpect(jsonPath("$.canTC").value(DEFAULT_CAN_TC.doubleValue()))
            .andExpect(jsonPath("$.vongCT").value(DEFAULT_VONG_CT.doubleValue()))
            .andExpect(jsonPath("$.bMI").value(DEFAULT_B_MI.doubleValue()))
            .andExpect(jsonPath("$.maTheBHYT").value(DEFAULT_MA_THE_BHYT))
            .andExpect(jsonPath("$.sDT").value(DEFAULT_S_DT));
    }

    @Test
    @Transactional
    void getNonExistingBenhNhan() throws Exception {
        // Get the benhNhan
        restBenhNhanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBenhNhan() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();

        // Update the benhNhan
        BenhNhan updatedBenhNhan = benhNhanRepository.findById(benhNhan.getId()).get();
        // Disconnect from session so that the updates on updatedBenhNhan are not directly saved in db
        em.detach(updatedBenhNhan);
        updatedBenhNhan
            .hoTen(UPDATED_HO_TEN)
            .maBN(UPDATED_MA_BN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .diaChi(UPDATED_DIA_CHI)
            .ngheNghiep(UPDATED_NGHE_NGHIEP)
            .chieuCao(UPDATED_CHIEU_CAO)
            .canHT(UPDATED_CAN_HT)
            .canTC(UPDATED_CAN_TC)
            .vongCT(UPDATED_VONG_CT)
            .bMI(UPDATED_B_MI)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .sDT(UPDATED_S_DT);

        restBenhNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBenhNhan.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBenhNhan))
            )
            .andExpect(status().isOk());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
        BenhNhan testBenhNhan = benhNhanList.get(benhNhanList.size() - 1);
        assertThat(testBenhNhan.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testBenhNhan.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testBenhNhan.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testBenhNhan.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testBenhNhan.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testBenhNhan.getNgheNghiep()).isEqualTo(UPDATED_NGHE_NGHIEP);
        assertThat(testBenhNhan.getChieuCao()).isEqualTo(UPDATED_CHIEU_CAO);
        assertThat(testBenhNhan.getCanHT()).isEqualTo(UPDATED_CAN_HT);
        assertThat(testBenhNhan.getCanTC()).isEqualTo(UPDATED_CAN_TC);
        assertThat(testBenhNhan.getVongCT()).isEqualTo(UPDATED_VONG_CT);
        assertThat(testBenhNhan.getbMI()).isEqualTo(UPDATED_B_MI);
        assertThat(testBenhNhan.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testBenhNhan.getsDT()).isEqualTo(UPDATED_S_DT);
    }

    @Test
    @Transactional
    void putNonExistingBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, benhNhan.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBenhNhanWithPatch() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();

        // Update the benhNhan using partial update
        BenhNhan partialUpdatedBenhNhan = new BenhNhan();
        partialUpdatedBenhNhan.setId(benhNhan.getId());

        partialUpdatedBenhNhan
            .hoTen(UPDATED_HO_TEN)
            .diaChi(UPDATED_DIA_CHI)
            .canTC(UPDATED_CAN_TC)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .sDT(UPDATED_S_DT);

        restBenhNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenhNhan.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenhNhan))
            )
            .andExpect(status().isOk());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
        BenhNhan testBenhNhan = benhNhanList.get(benhNhanList.size() - 1);
        assertThat(testBenhNhan.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testBenhNhan.getMaBN()).isEqualTo(DEFAULT_MA_BN);
        assertThat(testBenhNhan.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testBenhNhan.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testBenhNhan.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testBenhNhan.getNgheNghiep()).isEqualTo(DEFAULT_NGHE_NGHIEP);
        assertThat(testBenhNhan.getChieuCao()).isEqualTo(DEFAULT_CHIEU_CAO);
        assertThat(testBenhNhan.getCanHT()).isEqualTo(DEFAULT_CAN_HT);
        assertThat(testBenhNhan.getCanTC()).isEqualTo(UPDATED_CAN_TC);
        assertThat(testBenhNhan.getVongCT()).isEqualTo(DEFAULT_VONG_CT);
        assertThat(testBenhNhan.getbMI()).isEqualTo(DEFAULT_B_MI);
        assertThat(testBenhNhan.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testBenhNhan.getsDT()).isEqualTo(UPDATED_S_DT);
    }

    @Test
    @Transactional
    void fullUpdateBenhNhanWithPatch() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();

        // Update the benhNhan using partial update
        BenhNhan partialUpdatedBenhNhan = new BenhNhan();
        partialUpdatedBenhNhan.setId(benhNhan.getId());

        partialUpdatedBenhNhan
            .hoTen(UPDATED_HO_TEN)
            .maBN(UPDATED_MA_BN)
            .ngaySinh(UPDATED_NGAY_SINH)
            .gioiTinh(UPDATED_GIOI_TINH)
            .diaChi(UPDATED_DIA_CHI)
            .ngheNghiep(UPDATED_NGHE_NGHIEP)
            .chieuCao(UPDATED_CHIEU_CAO)
            .canHT(UPDATED_CAN_HT)
            .canTC(UPDATED_CAN_TC)
            .vongCT(UPDATED_VONG_CT)
            .bMI(UPDATED_B_MI)
            .maTheBHYT(UPDATED_MA_THE_BHYT)
            .sDT(UPDATED_S_DT);

        restBenhNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBenhNhan.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBenhNhan))
            )
            .andExpect(status().isOk());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
        BenhNhan testBenhNhan = benhNhanList.get(benhNhanList.size() - 1);
        assertThat(testBenhNhan.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testBenhNhan.getMaBN()).isEqualTo(UPDATED_MA_BN);
        assertThat(testBenhNhan.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testBenhNhan.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testBenhNhan.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testBenhNhan.getNgheNghiep()).isEqualTo(UPDATED_NGHE_NGHIEP);
        assertThat(testBenhNhan.getChieuCao()).isEqualTo(UPDATED_CHIEU_CAO);
        assertThat(testBenhNhan.getCanHT()).isEqualTo(UPDATED_CAN_HT);
        assertThat(testBenhNhan.getCanTC()).isEqualTo(UPDATED_CAN_TC);
        assertThat(testBenhNhan.getVongCT()).isEqualTo(UPDATED_VONG_CT);
        assertThat(testBenhNhan.getbMI()).isEqualTo(UPDATED_B_MI);
        assertThat(testBenhNhan.getMaTheBHYT()).isEqualTo(UPDATED_MA_THE_BHYT);
        assertThat(testBenhNhan.getsDT()).isEqualTo(UPDATED_S_DT);
    }

    @Test
    @Transactional
    void patchNonExistingBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, benhNhan.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isBadRequest());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBenhNhan() throws Exception {
        int databaseSizeBeforeUpdate = benhNhanRepository.findAll().size();
        benhNhan.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBenhNhanMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(benhNhan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BenhNhan in the database
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBenhNhan() throws Exception {
        // Initialize the database
        benhNhanRepository.saveAndFlush(benhNhan);

        int databaseSizeBeforeDelete = benhNhanRepository.findAll().size();

        // Delete the benhNhan
        restBenhNhanMockMvc
            .perform(delete(ENTITY_API_URL_ID, benhNhan.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BenhNhan> benhNhanList = benhNhanRepository.findAll();
        assertThat(benhNhanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
