package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DanhGiaCanThiepDDTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhGiaCanThiepDD.class);
        DanhGiaCanThiepDD danhGiaCanThiepDD1 = new DanhGiaCanThiepDD();
        danhGiaCanThiepDD1.setId(1L);
        DanhGiaCanThiepDD danhGiaCanThiepDD2 = new DanhGiaCanThiepDD();
        danhGiaCanThiepDD2.setId(danhGiaCanThiepDD1.getId());
        assertThat(danhGiaCanThiepDD1).isEqualTo(danhGiaCanThiepDD2);
        danhGiaCanThiepDD2.setId(2L);
        assertThat(danhGiaCanThiepDD1).isNotEqualTo(danhGiaCanThiepDD2);
        danhGiaCanThiepDD1.setId(null);
        assertThat(danhGiaCanThiepDD1).isNotEqualTo(danhGiaCanThiepDD2);
    }
}
