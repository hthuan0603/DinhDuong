package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NhanVienTiepNhanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanVienTiepNhanDTO.class);
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO1 = new NhanVienTiepNhanDTO();
        nhanVienTiepNhanDTO1.setId(1L);
        NhanVienTiepNhanDTO nhanVienTiepNhanDTO2 = new NhanVienTiepNhanDTO();
        assertThat(nhanVienTiepNhanDTO1).isNotEqualTo(nhanVienTiepNhanDTO2);
        nhanVienTiepNhanDTO2.setId(nhanVienTiepNhanDTO1.getId());
        assertThat(nhanVienTiepNhanDTO1).isEqualTo(nhanVienTiepNhanDTO2);
        nhanVienTiepNhanDTO2.setId(2L);
        assertThat(nhanVienTiepNhanDTO1).isNotEqualTo(nhanVienTiepNhanDTO2);
        nhanVienTiepNhanDTO1.setId(null);
        assertThat(nhanVienTiepNhanDTO1).isNotEqualTo(nhanVienTiepNhanDTO2);
    }
}
