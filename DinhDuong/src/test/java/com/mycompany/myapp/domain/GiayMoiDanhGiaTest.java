package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiayMoiDanhGiaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiayMoiDanhGia.class);
        GiayMoiDanhGia giayMoiDanhGia1 = new GiayMoiDanhGia();
        giayMoiDanhGia1.setId(1L);
        GiayMoiDanhGia giayMoiDanhGia2 = new GiayMoiDanhGia();
        giayMoiDanhGia2.setId(giayMoiDanhGia1.getId());
        assertThat(giayMoiDanhGia1).isEqualTo(giayMoiDanhGia2);
        giayMoiDanhGia2.setId(2L);
        assertThat(giayMoiDanhGia1).isNotEqualTo(giayMoiDanhGia2);
        giayMoiDanhGia1.setId(null);
        assertThat(giayMoiDanhGia1).isNotEqualTo(giayMoiDanhGia2);
    }
}
