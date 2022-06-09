package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Thuoc;
import com.mycompany.myapp.repository.ThuocRepository;
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
 * Integration tests for the {@link ThuocResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ThuocResourceIT {

    private static final String DEFAULT_TEN_THUOC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_THUOC = "BBBBBBBBBB";

    private static final String DEFAULT_DUONG_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_DUONG_DUNG = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_LUONG = 1;
    private static final Integer UPDATED_SO_LUONG = 2;

    private static final String DEFAULT_CACH_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_CACH_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_HOAT_CHAT = "AAAAAAAAAA";
    private static final String UPDATED_HOAT_CHAT = "BBBBBBBBBB";

    private static final String DEFAULT_D_VT = "AAAAAAAAAA";
    private static final String UPDATED_D_VT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DON_GIA = 1;
    private static final Integer UPDATED_DON_GIA = 2;

    private static final Integer DEFAULT_THANH_TIEN = 1;
    private static final Integer UPDATED_THANH_TIEN = 2;

    private static final String DEFAULT_LOAI_TT_CU = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_TT_CU = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_TT_MOI = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_TT_MOI = "BBBBBBBBBB";

    private static final String DEFAULT_LIEU_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_K_S = "AAAAAAAAAA";
    private static final String UPDATED_K_S = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/thuocs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ThuocRepository thuocRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restThuocMockMvc;

    private Thuoc thuoc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Thuoc createEntity(EntityManager em) {
        Thuoc thuoc = new Thuoc()
            .tenThuoc(DEFAULT_TEN_THUOC)
            .duongDung(DEFAULT_DUONG_DUNG)
            .soLuong(DEFAULT_SO_LUONG)
            .cachDung(DEFAULT_CACH_DUNG)
            .hoatChat(DEFAULT_HOAT_CHAT)
            .dVT(DEFAULT_D_VT)
            .donGia(DEFAULT_DON_GIA)
            .thanhTien(DEFAULT_THANH_TIEN)
            .loaiTTCu(DEFAULT_LOAI_TT_CU)
            .loaiTTMoi(DEFAULT_LOAI_TT_MOI)
            .lieuDung(DEFAULT_LIEU_DUNG)
            .kS(DEFAULT_K_S);
        return thuoc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Thuoc createUpdatedEntity(EntityManager em) {
        Thuoc thuoc = new Thuoc()
            .tenThuoc(UPDATED_TEN_THUOC)
            .duongDung(UPDATED_DUONG_DUNG)
            .soLuong(UPDATED_SO_LUONG)
            .cachDung(UPDATED_CACH_DUNG)
            .hoatChat(UPDATED_HOAT_CHAT)
            .dVT(UPDATED_D_VT)
            .donGia(UPDATED_DON_GIA)
            .thanhTien(UPDATED_THANH_TIEN)
            .loaiTTCu(UPDATED_LOAI_TT_CU)
            .loaiTTMoi(UPDATED_LOAI_TT_MOI)
            .lieuDung(UPDATED_LIEU_DUNG)
            .kS(UPDATED_K_S);
        return thuoc;
    }

    @BeforeEach
    public void initTest() {
        thuoc = createEntity(em);
    }

    @Test
    @Transactional
    void createThuoc() throws Exception {
        int databaseSizeBeforeCreate = thuocRepository.findAll().size();
        // Create the Thuoc
        restThuocMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isCreated());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeCreate + 1);
        Thuoc testThuoc = thuocList.get(thuocList.size() - 1);
        assertThat(testThuoc.getTenThuoc()).isEqualTo(DEFAULT_TEN_THUOC);
        assertThat(testThuoc.getDuongDung()).isEqualTo(DEFAULT_DUONG_DUNG);
        assertThat(testThuoc.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testThuoc.getCachDung()).isEqualTo(DEFAULT_CACH_DUNG);
        assertThat(testThuoc.getHoatChat()).isEqualTo(DEFAULT_HOAT_CHAT);
        assertThat(testThuoc.getdVT()).isEqualTo(DEFAULT_D_VT);
        assertThat(testThuoc.getDonGia()).isEqualTo(DEFAULT_DON_GIA);
        assertThat(testThuoc.getThanhTien()).isEqualTo(DEFAULT_THANH_TIEN);
        assertThat(testThuoc.getLoaiTTCu()).isEqualTo(DEFAULT_LOAI_TT_CU);
        assertThat(testThuoc.getLoaiTTMoi()).isEqualTo(DEFAULT_LOAI_TT_MOI);
        assertThat(testThuoc.getLieuDung()).isEqualTo(DEFAULT_LIEU_DUNG);
        assertThat(testThuoc.getkS()).isEqualTo(DEFAULT_K_S);
    }

    @Test
    @Transactional
    void createThuocWithExistingId() throws Exception {
        // Create the Thuoc with an existing ID
        thuoc.setId(1L);

        int databaseSizeBeforeCreate = thuocRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restThuocMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllThuocs() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        // Get all the thuocList
        restThuocMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thuoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenThuoc").value(hasItem(DEFAULT_TEN_THUOC)))
            .andExpect(jsonPath("$.[*].duongDung").value(hasItem(DEFAULT_DUONG_DUNG)))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG)))
            .andExpect(jsonPath("$.[*].cachDung").value(hasItem(DEFAULT_CACH_DUNG)))
            .andExpect(jsonPath("$.[*].hoatChat").value(hasItem(DEFAULT_HOAT_CHAT)))
            .andExpect(jsonPath("$.[*].dVT").value(hasItem(DEFAULT_D_VT)))
            .andExpect(jsonPath("$.[*].donGia").value(hasItem(DEFAULT_DON_GIA)))
            .andExpect(jsonPath("$.[*].thanhTien").value(hasItem(DEFAULT_THANH_TIEN)))
            .andExpect(jsonPath("$.[*].loaiTTCu").value(hasItem(DEFAULT_LOAI_TT_CU)))
            .andExpect(jsonPath("$.[*].loaiTTMoi").value(hasItem(DEFAULT_LOAI_TT_MOI)))
            .andExpect(jsonPath("$.[*].lieuDung").value(hasItem(DEFAULT_LIEU_DUNG)))
            .andExpect(jsonPath("$.[*].kS").value(hasItem(DEFAULT_K_S)));
    }

    @Test
    @Transactional
    void getThuoc() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        // Get the thuoc
        restThuocMockMvc
            .perform(get(ENTITY_API_URL_ID, thuoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(thuoc.getId().intValue()))
            .andExpect(jsonPath("$.tenThuoc").value(DEFAULT_TEN_THUOC))
            .andExpect(jsonPath("$.duongDung").value(DEFAULT_DUONG_DUNG))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG))
            .andExpect(jsonPath("$.cachDung").value(DEFAULT_CACH_DUNG))
            .andExpect(jsonPath("$.hoatChat").value(DEFAULT_HOAT_CHAT))
            .andExpect(jsonPath("$.dVT").value(DEFAULT_D_VT))
            .andExpect(jsonPath("$.donGia").value(DEFAULT_DON_GIA))
            .andExpect(jsonPath("$.thanhTien").value(DEFAULT_THANH_TIEN))
            .andExpect(jsonPath("$.loaiTTCu").value(DEFAULT_LOAI_TT_CU))
            .andExpect(jsonPath("$.loaiTTMoi").value(DEFAULT_LOAI_TT_MOI))
            .andExpect(jsonPath("$.lieuDung").value(DEFAULT_LIEU_DUNG))
            .andExpect(jsonPath("$.kS").value(DEFAULT_K_S));
    }

    @Test
    @Transactional
    void getNonExistingThuoc() throws Exception {
        // Get the thuoc
        restThuocMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewThuoc() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();

        // Update the thuoc
        Thuoc updatedThuoc = thuocRepository.findById(thuoc.getId()).get();
        // Disconnect from session so that the updates on updatedThuoc are not directly saved in db
        em.detach(updatedThuoc);
        updatedThuoc
            .tenThuoc(UPDATED_TEN_THUOC)
            .duongDung(UPDATED_DUONG_DUNG)
            .soLuong(UPDATED_SO_LUONG)
            .cachDung(UPDATED_CACH_DUNG)
            .hoatChat(UPDATED_HOAT_CHAT)
            .dVT(UPDATED_D_VT)
            .donGia(UPDATED_DON_GIA)
            .thanhTien(UPDATED_THANH_TIEN)
            .loaiTTCu(UPDATED_LOAI_TT_CU)
            .loaiTTMoi(UPDATED_LOAI_TT_MOI)
            .lieuDung(UPDATED_LIEU_DUNG)
            .kS(UPDATED_K_S);

        restThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedThuoc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedThuoc))
            )
            .andExpect(status().isOk());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
        Thuoc testThuoc = thuocList.get(thuocList.size() - 1);
        assertThat(testThuoc.getTenThuoc()).isEqualTo(UPDATED_TEN_THUOC);
        assertThat(testThuoc.getDuongDung()).isEqualTo(UPDATED_DUONG_DUNG);
        assertThat(testThuoc.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testThuoc.getCachDung()).isEqualTo(UPDATED_CACH_DUNG);
        assertThat(testThuoc.getHoatChat()).isEqualTo(UPDATED_HOAT_CHAT);
        assertThat(testThuoc.getdVT()).isEqualTo(UPDATED_D_VT);
        assertThat(testThuoc.getDonGia()).isEqualTo(UPDATED_DON_GIA);
        assertThat(testThuoc.getThanhTien()).isEqualTo(UPDATED_THANH_TIEN);
        assertThat(testThuoc.getLoaiTTCu()).isEqualTo(UPDATED_LOAI_TT_CU);
        assertThat(testThuoc.getLoaiTTMoi()).isEqualTo(UPDATED_LOAI_TT_MOI);
        assertThat(testThuoc.getLieuDung()).isEqualTo(UPDATED_LIEU_DUNG);
        assertThat(testThuoc.getkS()).isEqualTo(UPDATED_K_S);
    }

    @Test
    @Transactional
    void putNonExistingThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thuoc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateThuocWithPatch() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();

        // Update the thuoc using partial update
        Thuoc partialUpdatedThuoc = new Thuoc();
        partialUpdatedThuoc.setId(thuoc.getId());

        partialUpdatedThuoc
            .tenThuoc(UPDATED_TEN_THUOC)
            .duongDung(UPDATED_DUONG_DUNG)
            .soLuong(UPDATED_SO_LUONG)
            .cachDung(UPDATED_CACH_DUNG)
            .dVT(UPDATED_D_VT)
            .donGia(UPDATED_DON_GIA)
            .thanhTien(UPDATED_THANH_TIEN)
            .loaiTTMoi(UPDATED_LOAI_TT_MOI)
            .kS(UPDATED_K_S);

        restThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedThuoc))
            )
            .andExpect(status().isOk());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
        Thuoc testThuoc = thuocList.get(thuocList.size() - 1);
        assertThat(testThuoc.getTenThuoc()).isEqualTo(UPDATED_TEN_THUOC);
        assertThat(testThuoc.getDuongDung()).isEqualTo(UPDATED_DUONG_DUNG);
        assertThat(testThuoc.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testThuoc.getCachDung()).isEqualTo(UPDATED_CACH_DUNG);
        assertThat(testThuoc.getHoatChat()).isEqualTo(DEFAULT_HOAT_CHAT);
        assertThat(testThuoc.getdVT()).isEqualTo(UPDATED_D_VT);
        assertThat(testThuoc.getDonGia()).isEqualTo(UPDATED_DON_GIA);
        assertThat(testThuoc.getThanhTien()).isEqualTo(UPDATED_THANH_TIEN);
        assertThat(testThuoc.getLoaiTTCu()).isEqualTo(DEFAULT_LOAI_TT_CU);
        assertThat(testThuoc.getLoaiTTMoi()).isEqualTo(UPDATED_LOAI_TT_MOI);
        assertThat(testThuoc.getLieuDung()).isEqualTo(DEFAULT_LIEU_DUNG);
        assertThat(testThuoc.getkS()).isEqualTo(UPDATED_K_S);
    }

    @Test
    @Transactional
    void fullUpdateThuocWithPatch() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();

        // Update the thuoc using partial update
        Thuoc partialUpdatedThuoc = new Thuoc();
        partialUpdatedThuoc.setId(thuoc.getId());

        partialUpdatedThuoc
            .tenThuoc(UPDATED_TEN_THUOC)
            .duongDung(UPDATED_DUONG_DUNG)
            .soLuong(UPDATED_SO_LUONG)
            .cachDung(UPDATED_CACH_DUNG)
            .hoatChat(UPDATED_HOAT_CHAT)
            .dVT(UPDATED_D_VT)
            .donGia(UPDATED_DON_GIA)
            .thanhTien(UPDATED_THANH_TIEN)
            .loaiTTCu(UPDATED_LOAI_TT_CU)
            .loaiTTMoi(UPDATED_LOAI_TT_MOI)
            .lieuDung(UPDATED_LIEU_DUNG)
            .kS(UPDATED_K_S);

        restThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedThuoc))
            )
            .andExpect(status().isOk());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
        Thuoc testThuoc = thuocList.get(thuocList.size() - 1);
        assertThat(testThuoc.getTenThuoc()).isEqualTo(UPDATED_TEN_THUOC);
        assertThat(testThuoc.getDuongDung()).isEqualTo(UPDATED_DUONG_DUNG);
        assertThat(testThuoc.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testThuoc.getCachDung()).isEqualTo(UPDATED_CACH_DUNG);
        assertThat(testThuoc.getHoatChat()).isEqualTo(UPDATED_HOAT_CHAT);
        assertThat(testThuoc.getdVT()).isEqualTo(UPDATED_D_VT);
        assertThat(testThuoc.getDonGia()).isEqualTo(UPDATED_DON_GIA);
        assertThat(testThuoc.getThanhTien()).isEqualTo(UPDATED_THANH_TIEN);
        assertThat(testThuoc.getLoaiTTCu()).isEqualTo(UPDATED_LOAI_TT_CU);
        assertThat(testThuoc.getLoaiTTMoi()).isEqualTo(UPDATED_LOAI_TT_MOI);
        assertThat(testThuoc.getLieuDung()).isEqualTo(UPDATED_LIEU_DUNG);
        assertThat(testThuoc.getkS()).isEqualTo(UPDATED_K_S);
    }

    @Test
    @Transactional
    void patchNonExistingThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, thuoc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isBadRequest());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamThuoc() throws Exception {
        int databaseSizeBeforeUpdate = thuocRepository.findAll().size();
        thuoc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThuocMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(thuoc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Thuoc in the database
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteThuoc() throws Exception {
        // Initialize the database
        thuocRepository.saveAndFlush(thuoc);

        int databaseSizeBeforeDelete = thuocRepository.findAll().size();

        // Delete the thuoc
        restThuocMockMvc
            .perform(delete(ENTITY_API_URL_ID, thuoc.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Thuoc> thuocList = thuocRepository.findAll();
        assertThat(thuocList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
