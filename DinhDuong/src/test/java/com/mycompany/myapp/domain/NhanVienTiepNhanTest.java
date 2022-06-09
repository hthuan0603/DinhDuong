package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhanVienTiepNhanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanVienTiepNhan.class);
        NhanVienTiepNhan nhanVienTiepNhan1 = new NhanVienTiepNhan();
        nhanVienTiepNhan1.setId(1L);
        NhanVienTiepNhan nhanVienTiepNhan2 = new NhanVienTiepNhan();
        nhanVienTiepNhan2.setId(nhanVienTiepNhan1.getId());
        assertThat(nhanVienTiepNhan1).isEqualTo(nhanVienTiepNhan2);
        nhanVienTiepNhan2.setId(2L);
        assertThat(nhanVienTiepNhan1).isNotEqualTo(nhanVienTiepNhan2);
        nhanVienTiepNhan1.setId(null);
        assertThat(nhanVienTiepNhan1).isNotEqualTo(nhanVienTiepNhan2);
    }
}
