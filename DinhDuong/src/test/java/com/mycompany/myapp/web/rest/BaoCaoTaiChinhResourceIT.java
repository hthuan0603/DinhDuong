package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.BaoCaoTaiChinh;
import com.mycompany.myapp.repository.BaoCaoTaiChinhRepository;
import com.mycompany.myapp.service.dto.BaoCaoTaiChinhDTO;
import com.mycompany.myapp.service.mapper.BaoCaoTaiChinhMapper;
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
 * Integration tests for the {@link BaoCaoTaiChinhResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BaoCaoTaiChinhResourceIT {

    private static final Integer DEFAULT_MA_BAO_CAO = 1;
    private static final Integer UPDATED_MA_BAO_CAO = 2;

    private static final Integer DEFAULT_LUU_TRU = 1;
    private static final Integer UPDATED_LUU_TRU = 2;

    private static final Integer DEFAULT_TIEN_AN = 1;
    private static final Integer UPDATED_TIEN_AN = 2;

    private static final Integer DEFAULT_TIEN_O = 1;
    private static final Integer UPDATED_TIEN_O = 2;

    private static final Integer DEFAULT_TIEN_DI_LAI = 1;
    private static final Integer UPDATED_TIEN_DI_LAI = 2;

    private static final Integer DEFAULT_TAI_LIEU = 1;
    private static final Integer UPDATED_TAI_LIEU = 2;

    private static final Integer DEFAULT_GIANG_DAY = 1;
    private static final Integer UPDATED_GIANG_DAY = 2;

    private static final Integer DEFAULT_KHAC = 1;
    private static final Integer UPDATED_KHAC = 2;

    private static final String ENTITY_API_URL = "/api/bao-cao-tai-chinhs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BaoCaoTaiChinhRepository baoCaoTaiChinhRepository;

    @Autowired
    private BaoCaoTaiChinhMapper baoCaoTaiChinhMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBaoCaoTaiChinhMockMvc;

    private BaoCaoTaiChinh baoCaoTaiChinh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BaoCaoTaiChinh createEntity(EntityManager em) {
        BaoCaoTaiChinh baoCaoTaiChinh = new BaoCaoTaiChinh()
            .maBaoCao(DEFAULT_MA_BAO_CAO)
            .luuTru(DEFAULT_LUU_TRU)
            .tienAn(DEFAULT_TIEN_AN)
            .tienO(DEFAULT_TIEN_O)
            .tienDiLai(DEFAULT_TIEN_DI_LAI)
            .taiLieu(DEFAULT_TAI_LIEU)
            .giangDay(DEFAULT_GIANG_DAY)
            .khac(DEFAULT_KHAC);
        return baoCaoTaiChinh;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BaoCaoTaiChinh createUpdatedEntity(EntityManager em) {
        BaoCaoTaiChinh baoCaoTaiChinh = new BaoCaoTaiChinh()
            .maBaoCao(UPDATED_MA_BAO_CAO)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);
        return baoCaoTaiChinh;
    }

    @BeforeEach
    public void initTest() {
        baoCaoTaiChinh = createEntity(em);
    }

    @Test
    @Transactional
    void createBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeCreate = baoCaoTaiChinhRepository.findAll().size();
        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);
        restBaoCaoTaiChinhMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeCreate + 1);
        BaoCaoTaiChinh testBaoCaoTaiChinh = baoCaoTaiChinhList.get(baoCaoTaiChinhList.size() - 1);
        assertThat(testBaoCaoTaiChinh.getMaBaoCao()).isEqualTo(DEFAULT_MA_BAO_CAO);
        assertThat(testBaoCaoTaiChinh.getLuuTru()).isEqualTo(DEFAULT_LUU_TRU);
        assertThat(testBaoCaoTaiChinh.getTienAn()).isEqualTo(DEFAULT_TIEN_AN);
        assertThat(testBaoCaoTaiChinh.getTienO()).isEqualTo(DEFAULT_TIEN_O);
        assertThat(testBaoCaoTaiChinh.getTienDiLai()).isEqualTo(DEFAULT_TIEN_DI_LAI);
        assertThat(testBaoCaoTaiChinh.getTaiLieu()).isEqualTo(DEFAULT_TAI_LIEU);
        assertThat(testBaoCaoTaiChinh.getGiangDay()).isEqualTo(DEFAULT_GIANG_DAY);
        assertThat(testBaoCaoTaiChinh.getKhac()).isEqualTo(DEFAULT_KHAC);
    }

    @Test
    @Transactional
    void createBaoCaoTaiChinhWithExistingId() throws Exception {
        // Create the BaoCaoTaiChinh with an existing ID
        baoCaoTaiChinh.setId(1L);
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        int databaseSizeBeforeCreate = baoCaoTaiChinhRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaoCaoTaiChinhMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBaoCaoTaiChinhs() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        // Get all the baoCaoTaiChinhList
        restBaoCaoTaiChinhMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baoCaoTaiChinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maBaoCao").value(hasItem(DEFAULT_MA_BAO_CAO)))
            .andExpect(jsonPath("$.[*].luuTru").value(hasItem(DEFAULT_LUU_TRU)))
            .andExpect(jsonPath("$.[*].tienAn").value(hasItem(DEFAULT_TIEN_AN)))
            .andExpect(jsonPath("$.[*].tienO").value(hasItem(DEFAULT_TIEN_O)))
            .andExpect(jsonPath("$.[*].tienDiLai").value(hasItem(DEFAULT_TIEN_DI_LAI)))
            .andExpect(jsonPath("$.[*].taiLieu").value(hasItem(DEFAULT_TAI_LIEU)))
            .andExpect(jsonPath("$.[*].giangDay").value(hasItem(DEFAULT_GIANG_DAY)))
            .andExpect(jsonPath("$.[*].khac").value(hasItem(DEFAULT_KHAC)));
    }

    @Test
    @Transactional
    void getBaoCaoTaiChinh() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        // Get the baoCaoTaiChinh
        restBaoCaoTaiChinhMockMvc
            .perform(get(ENTITY_API_URL_ID, baoCaoTaiChinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(baoCaoTaiChinh.getId().intValue()))
            .andExpect(jsonPath("$.maBaoCao").value(DEFAULT_MA_BAO_CAO))
            .andExpect(jsonPath("$.luuTru").value(DEFAULT_LUU_TRU))
            .andExpect(jsonPath("$.tienAn").value(DEFAULT_TIEN_AN))
            .andExpect(jsonPath("$.tienO").value(DEFAULT_TIEN_O))
            .andExpect(jsonPath("$.tienDiLai").value(DEFAULT_TIEN_DI_LAI))
            .andExpect(jsonPath("$.taiLieu").value(DEFAULT_TAI_LIEU))
            .andExpect(jsonPath("$.giangDay").value(DEFAULT_GIANG_DAY))
            .andExpect(jsonPath("$.khac").value(DEFAULT_KHAC));
    }

    @Test
    @Transactional
    void getNonExistingBaoCaoTaiChinh() throws Exception {
        // Get the baoCaoTaiChinh
        restBaoCaoTaiChinhMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBaoCaoTaiChinh() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();

        // Update the baoCaoTaiChinh
        BaoCaoTaiChinh updatedBaoCaoTaiChinh = baoCaoTaiChinhRepository.findById(baoCaoTaiChinh.getId()).get();
        // Disconnect from session so that the updates on updatedBaoCaoTaiChinh are not directly saved in db
        em.detach(updatedBaoCaoTaiChinh);
        updatedBaoCaoTaiChinh
            .maBaoCao(UPDATED_MA_BAO_CAO)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(updatedBaoCaoTaiChinh);

        restBaoCaoTaiChinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, baoCaoTaiChinhDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isOk());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
        BaoCaoTaiChinh testBaoCaoTaiChinh = baoCaoTaiChinhList.get(baoCaoTaiChinhList.size() - 1);
        assertThat(testBaoCaoTaiChinh.getMaBaoCao()).isEqualTo(UPDATED_MA_BAO_CAO);
        assertThat(testBaoCaoTaiChinh.getLuuTru()).isEqualTo(UPDATED_LUU_TRU);
        assertThat(testBaoCaoTaiChinh.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testBaoCaoTaiChinh.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testBaoCaoTaiChinh.getTienDiLai()).isEqualTo(UPDATED_TIEN_DI_LAI);
        assertThat(testBaoCaoTaiChinh.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testBaoCaoTaiChinh.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testBaoCaoTaiChinh.getKhac()).isEqualTo(UPDATED_KHAC);
    }

    @Test
    @Transactional
    void putNonExistingBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, baoCaoTaiChinhDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBaoCaoTaiChinhWithPatch() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();

        // Update the baoCaoTaiChinh using partial update
        BaoCaoTaiChinh partialUpdatedBaoCaoTaiChinh = new BaoCaoTaiChinh();
        partialUpdatedBaoCaoTaiChinh.setId(baoCaoTaiChinh.getId());

        partialUpdatedBaoCaoTaiChinh
            .maBaoCao(UPDATED_MA_BAO_CAO)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY);

        restBaoCaoTaiChinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBaoCaoTaiChinh.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaoCaoTaiChinh))
            )
            .andExpect(status().isOk());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
        BaoCaoTaiChinh testBaoCaoTaiChinh = baoCaoTaiChinhList.get(baoCaoTaiChinhList.size() - 1);
        assertThat(testBaoCaoTaiChinh.getMaBaoCao()).isEqualTo(UPDATED_MA_BAO_CAO);
        assertThat(testBaoCaoTaiChinh.getLuuTru()).isEqualTo(DEFAULT_LUU_TRU);
        assertThat(testBaoCaoTaiChinh.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testBaoCaoTaiChinh.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testBaoCaoTaiChinh.getTienDiLai()).isEqualTo(DEFAULT_TIEN_DI_LAI);
        assertThat(testBaoCaoTaiChinh.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testBaoCaoTaiChinh.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testBaoCaoTaiChinh.getKhac()).isEqualTo(DEFAULT_KHAC);
    }

    @Test
    @Transactional
    void fullUpdateBaoCaoTaiChinhWithPatch() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();

        // Update the baoCaoTaiChinh using partial update
        BaoCaoTaiChinh partialUpdatedBaoCaoTaiChinh = new BaoCaoTaiChinh();
        partialUpdatedBaoCaoTaiChinh.setId(baoCaoTaiChinh.getId());

        partialUpdatedBaoCaoTaiChinh
            .maBaoCao(UPDATED_MA_BAO_CAO)
            .luuTru(UPDATED_LUU_TRU)
            .tienAn(UPDATED_TIEN_AN)
            .tienO(UPDATED_TIEN_O)
            .tienDiLai(UPDATED_TIEN_DI_LAI)
            .taiLieu(UPDATED_TAI_LIEU)
            .giangDay(UPDATED_GIANG_DAY)
            .khac(UPDATED_KHAC);

        restBaoCaoTaiChinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBaoCaoTaiChinh.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaoCaoTaiChinh))
            )
            .andExpect(status().isOk());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
        BaoCaoTaiChinh testBaoCaoTaiChinh = baoCaoTaiChinhList.get(baoCaoTaiChinhList.size() - 1);
        assertThat(testBaoCaoTaiChinh.getMaBaoCao()).isEqualTo(UPDATED_MA_BAO_CAO);
        assertThat(testBaoCaoTaiChinh.getLuuTru()).isEqualTo(UPDATED_LUU_TRU);
        assertThat(testBaoCaoTaiChinh.getTienAn()).isEqualTo(UPDATED_TIEN_AN);
        assertThat(testBaoCaoTaiChinh.getTienO()).isEqualTo(UPDATED_TIEN_O);
        assertThat(testBaoCaoTaiChinh.getTienDiLai()).isEqualTo(UPDATED_TIEN_DI_LAI);
        assertThat(testBaoCaoTaiChinh.getTaiLieu()).isEqualTo(UPDATED_TAI_LIEU);
        assertThat(testBaoCaoTaiChinh.getGiangDay()).isEqualTo(UPDATED_GIANG_DAY);
        assertThat(testBaoCaoTaiChinh.getKhac()).isEqualTo(UPDATED_KHAC);
    }

    @Test
    @Transactional
    void patchNonExistingBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, baoCaoTaiChinhDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBaoCaoTaiChinh() throws Exception {
        int databaseSizeBeforeUpdate = baoCaoTaiChinhRepository.findAll().size();
        baoCaoTaiChinh.setId(count.incrementAndGet());

        // Create the BaoCaoTaiChinh
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBaoCaoTaiChinhMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(baoCaoTaiChinhDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BaoCaoTaiChinh in the database
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBaoCaoTaiChinh() throws Exception {
        // Initialize the database
        baoCaoTaiChinhRepository.saveAndFlush(baoCaoTaiChinh);

        int databaseSizeBeforeDelete = baoCaoTaiChinhRepository.findAll().size();

        // Delete the baoCaoTaiChinh
        restBaoCaoTaiChinhMockMvc
            .perform(delete(ENTITY_API_URL_ID, baoCaoTaiChinh.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BaoCaoTaiChinh> baoCaoTaiChinhList = baoCaoTaiChinhRepository.findAll();
        assertThat(baoCaoTaiChinhList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
