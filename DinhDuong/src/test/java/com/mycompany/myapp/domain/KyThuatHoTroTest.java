package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class KyThuatHoTroTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KyThuatHoTro.class);
        KyThuatHoTro kyThuatHoTro1 = new KyThuatHoTro();
        kyThuatHoTro1.setId(1L);
        KyThuatHoTro kyThuatHoTro2 = new KyThuatHoTro();
        kyThuatHoTro2.setId(kyThuatHoTro1.getId());
        assertThat(kyThuatHoTro1).isEqualTo(kyThuatHoTro2);
        kyThuatHoTro2.setId(2L);
        assertThat(kyThuatHoTro1).isNotEqualTo(kyThuatHoTro2);
        kyThuatHoTro1.setId(null);
        assertThat(kyThuatHoTro1).isNotEqualTo(kyThuatHoTro2);
    }
}
