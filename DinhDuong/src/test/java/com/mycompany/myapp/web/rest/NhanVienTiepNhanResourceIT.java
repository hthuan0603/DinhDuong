package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.NhanVienTiepNhan;
import com.mycompany.myapp.repository.NhanVienTiepNhanRepository;
import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import com.mycompany.myapp.service.mapper.NhanVienTiepNhanMapper;
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
 * Integration tests for the {@link NhanVienTiepNhanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NhanVienTiepNhanResourceIT {

    private static final Integer DEFAULT_MA_NHAN_VIEN = 1;
    private static final Integer UPDATED_MA_NHAN_VIEN = 2;

    private static final String DEFAULT_TEN_NHAN_VIEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHAN_VIEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/nhan-vien-tiep-nhans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NhanVienTiepNhanRepository nhanVienTiepNhanRepository;

    @Autowired
    private NhanVienTiepNhanMapper nhanVienTiepNhanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNhanVienTiepNhanMockMvc;

    private NhanVienTiepNhan nhanVienTiepNhan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanVienTiepNhan createEntity(EntityManager em) {
        NhanVienTiepNhan nhanVienTiepNhan = new NhanVienTiepNhan().maNhanVien(DEFAULT_MA_NHAN_VIEN).tenNhanVien(DEFAULT_TEN_NHAN_VIEN);
        return nhanVienTiepNhan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanVienTiepNhan createUpdatedEntity(EntityManager em) {
        NhanVienTiepNhan nhanVienTiepNhan = new NhanVienTiepNhan().maNhanVien(UPDATED_MA_NHAN_VIEN).tenNhanVien(UPDATED_TEN_NHAN_VIEN);
        return nhanVienTiepNhan;
    }

    @BeforeEach
    public void initTest() {
        nhanVienTiepNhan = createEntity(em);
    }

    @Test
    @Transactional
    void createNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeCreate = nhanVienTiepNhanRepository.findAll().size();
        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);
        restNhanVienTiepNhanMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeCreate + 1);
        NhanVienTiepNhan testNhanVienTiepNhan = nhanVienTiepNhanList.get(nhanVienTiepNhanList.size() - 1);
        assertThat(testNhanVienTiepNhan.getMaNhanVien()).isEqualTo(DEFAULT_MA_NHAN_VIEN);
        assertThat(testNhanVienTiepNhan.getTenNhanVien()).isEqualTo(DEFAULT_TEN_NHAN_VIEN);
    }

    @Test
    @Transactional
    void createNhanVienTiepNhanWithExistingId() throws Exception {
        // Create the NhanVienTiepNhan with an existing ID
        nhanVienTiepNhan.setId(1L);
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        int databaseSizeBeforeCreate = nhanVienTiepNhanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanVienTiepNhanMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNhanVienTiepNhans() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        // Get all the nhanVienTiepNhanList
        restNhanVienTiepNhanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanVienTiepNhan.getId().intValue())))
            .andExpect(jsonPath("$.[*].maNhanVien").value(hasItem(DEFAULT_MA_NHAN_VIEN)))
            .andExpect(jsonPath("$.[*].tenNhanVien").value(hasItem(DEFAULT_TEN_NHAN_VIEN)));
    }

    @Test
    @Transactional
    void getNhanVienTiepNhan() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        // Get the nhanVienTiepNhan
        restNhanVienTiepNhanMockMvc
            .perform(get(ENTITY_API_URL_ID, nhanVienTiepNhan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nhanVienTiepNhan.getId().intValue()))
            .andExpect(jsonPath("$.maNhanVien").value(DEFAULT_MA_NHAN_VIEN))
            .andExpect(jsonPath("$.tenNhanVien").value(DEFAULT_TEN_NHAN_VIEN));
    }

    @Test
    @Transactional
    void getNonExistingNhanVienTiepNhan() throws Exception {
        // Get the nhanVienTiepNhan
        restNhanVienTiepNhanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNhanVienTiepNhan() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();

        // Update the nhanVienTiepNhan
        NhanVienTiepNhan updatedNhanVienTiepNhan = nhanVienTiepNhanRepository.findById(nhanVienTiepNhan.getId()).get();
        // Disconnect from session so that the updates on updatedNhanVienTiepNhan are not directly saved in db
        em.detach(updatedNhanVienTiepNhan);
        updatedNhanVienTiepNhan.maNhanVien(UPDATED_MA_NHAN_VIEN).tenNhanVien(UPDATED_TEN_NHAN_VIEN);
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(updatedNhanVienTiepNhan);

        restNhanVienTiepNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhanVienTiepNhanDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isOk());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
        NhanVienTiepNhan testNhanVienTiepNhan = nhanVienTiepNhanList.get(nhanVienTiepNhanList.size() - 1);
        assertThat(testNhanVienTiepNhan.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVienTiepNhan.getTenNhanVien()).isEqualTo(UPDATED_TEN_NHAN_VIEN);
    }

    @Test
    @Transactional
    void putNonExistingNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhanVienTiepNhanDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNhanVienTiepNhanWithPatch() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();

        // Update the nhanVienTiepNhan using partial update
        NhanVienTiepNhan partialUpdatedNhanVienTiepNhan = new NhanVienTiepNhan();
        partialUpdatedNhanVienTiepNhan.setId(nhanVienTiepNhan.getId());

        partialUpdatedNhanVienTiepNhan.maNhanVien(UPDATED_MA_NHAN_VIEN);

        restNhanVienTiepNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhanVienTiepNhan.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNhanVienTiepNhan))
            )
            .andExpect(status().isOk());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
        NhanVienTiepNhan testNhanVienTiepNhan = nhanVienTiepNhanList.get(nhanVienTiepNhanList.size() - 1);
        assertThat(testNhanVienTiepNhan.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVienTiepNhan.getTenNhanVien()).isEqualTo(DEFAULT_TEN_NHAN_VIEN);
    }

    @Test
    @Transactional
    void fullUpdateNhanVienTiepNhanWithPatch() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();

        // Update the nhanVienTiepNhan using partial update
        NhanVienTiepNhan partialUpdatedNhanVienTiepNhan = new NhanVienTiepNhan();
        partialUpdatedNhanVienTiepNhan.setId(nhanVienTiepNhan.getId());

        partialUpdatedNhanVienTiepNhan.maNhanVien(UPDATED_MA_NHAN_VIEN).tenNhanVien(UPDATED_TEN_NHAN_VIEN);

        restNhanVienTiepNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhanVienTiepNhan.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNhanVienTiepNhan))
            )
            .andExpect(status().isOk());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
        NhanVienTiepNhan testNhanVienTiepNhan = nhanVienTiepNhanList.get(nhanVienTiepNhanList.size() - 1);
        assertThat(testNhanVienTiepNhan.getMaNhanVien()).isEqualTo(UPDATED_MA_NHAN_VIEN);
        assertThat(testNhanVienTiepNhan.getTenNhanVien()).isEqualTo(UPDATED_TEN_NHAN_VIEN);
    }

    @Test
    @Transactional
    void patchNonExistingNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nhanVienTiepNhanDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNhanVienTiepNhan() throws Exception {
        int databaseSizeBeforeUpdate = nhanVienTiepNhanRepository.findAll().size();
        nhanVienTiepNhan.setId(count.incrementAndGet());

        // Create the NhanVienTiepNhan
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhanVienTiepNhanMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nhanVienTiepNhanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhanVienTiepNhan in the database
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNhanVienTiepNhan() throws Exception {
        // Initialize the database
        nhanVienTiepNhanRepository.saveAndFlush(nhanVienTiepNhan);

        int databaseSizeBeforeDelete = nhanVienTiepNhanRepository.findAll().size();

        // Delete the nhanVienTiepNhan
        restNhanVienTiepNhanMockMvc
            .perform(delete(ENTITY_API_URL_ID, nhanVienTiepNhan.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NhanVienTiepNhan> nhanVienTiepNhanList = nhanVienTiepNhanRepository.findAll();
        assertThat(nhanVienTiepNhanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
