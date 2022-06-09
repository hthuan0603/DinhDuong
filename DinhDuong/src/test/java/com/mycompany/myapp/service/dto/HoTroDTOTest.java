package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoTroDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoTroDTO.class);
        HoTroDTO hoTroDTO1 = new HoTroDTO();
        hoTroDTO1.setId(1L);
        HoTroDTO hoTroDTO2 = new HoTroDTO();
        assertThat(hoTroDTO1).isNotEqualTo(hoTroDTO2);
        hoTroDTO2.setId(hoTroDTO1.getId());
        assertThat(hoTroDTO1).isEqualTo(hoTroDTO2);
        hoTroDTO2.setId(2L);
        assertThat(hoTroDTO1).isNotEqualTo(hoTroDTO2);
        hoTroDTO1.setId(null);
        assertThat(hoTroDTO1).isNotEqualTo(hoTroDTO2);
    }
}
