package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VatTuHoTroTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VatTuHoTro.class);
        VatTuHoTro vatTuHoTro1 = new VatTuHoTro();
        vatTuHoTro1.setId(1L);
        VatTuHoTro vatTuHoTro2 = new VatTuHoTro();
        vatTuHoTro2.setId(vatTuHoTro1.getId());
        assertThat(vatTuHoTro1).isEqualTo(vatTuHoTro2);
        vatTuHoTro2.setId(2L);
        assertThat(vatTuHoTro1).isNotEqualTo(vatTuHoTro2);
        vatTuHoTro1.setId(null);
        assertThat(vatTuHoTro1).isNotEqualTo(vatTuHoTro2);
    }
}
