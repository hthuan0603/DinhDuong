package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class KyThuatHoTroDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KyThuatHoTroDTO.class);
        KyThuatHoTroDTO kyThuatHoTroDTO1 = new KyThuatHoTroDTO();
        kyThuatHoTroDTO1.setId(1L);
        KyThuatHoTroDTO kyThuatHoTroDTO2 = new KyThuatHoTroDTO();
        assertThat(kyThuatHoTroDTO1).isNotEqualTo(kyThuatHoTroDTO2);
        kyThuatHoTroDTO2.setId(kyThuatHoTroDTO1.getId());
        assertThat(kyThuatHoTroDTO1).isEqualTo(kyThuatHoTroDTO2);
        kyThuatHoTroDTO2.setId(2L);
        assertThat(kyThuatHoTroDTO1).isNotEqualTo(kyThuatHoTroDTO2);
        kyThuatHoTroDTO1.setId(null);
        assertThat(kyThuatHoTroDTO1).isNotEqualTo(kyThuatHoTroDTO2);
    }
}
