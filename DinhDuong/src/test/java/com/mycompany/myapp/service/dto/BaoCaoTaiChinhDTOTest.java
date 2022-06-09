package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BaoCaoTaiChinhDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaoCaoTaiChinhDTO.class);
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO1 = new BaoCaoTaiChinhDTO();
        baoCaoTaiChinhDTO1.setId(1L);
        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO2 = new BaoCaoTaiChinhDTO();
        assertThat(baoCaoTaiChinhDTO1).isNotEqualTo(baoCaoTaiChinhDTO2);
        baoCaoTaiChinhDTO2.setId(baoCaoTaiChinhDTO1.getId());
        assertThat(baoCaoTaiChinhDTO1).isEqualTo(baoCaoTaiChinhDTO2);
        baoCaoTaiChinhDTO2.setId(2L);
        assertThat(baoCaoTaiChinhDTO1).isNotEqualTo(baoCaoTaiChinhDTO2);
        baoCaoTaiChinhDTO1.setId(null);
        assertThat(baoCaoTaiChinhDTO1).isNotEqualTo(baoCaoTaiChinhDTO2);
    }
}
