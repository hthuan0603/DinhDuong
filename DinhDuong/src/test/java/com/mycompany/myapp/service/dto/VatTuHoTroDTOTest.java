package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VatTuHoTroDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VatTuHoTroDTO.class);
        VatTuHoTroDTO vatTuHoTroDTO1 = new VatTuHoTroDTO();
        vatTuHoTroDTO1.setId(1L);
        VatTuHoTroDTO vatTuHoTroDTO2 = new VatTuHoTroDTO();
        assertThat(vatTuHoTroDTO1).isNotEqualTo(vatTuHoTroDTO2);
        vatTuHoTroDTO2.setId(vatTuHoTroDTO1.getId());
        assertThat(vatTuHoTroDTO1).isEqualTo(vatTuHoTroDTO2);
        vatTuHoTroDTO2.setId(2L);
        assertThat(vatTuHoTroDTO1).isNotEqualTo(vatTuHoTroDTO2);
        vatTuHoTroDTO1.setId(null);
        assertThat(vatTuHoTroDTO1).isNotEqualTo(vatTuHoTroDTO2);
    }
}
