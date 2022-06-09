package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoTroTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoTro.class);
        HoTro hoTro1 = new HoTro();
        hoTro1.setId(1L);
        HoTro hoTro2 = new HoTro();
        hoTro2.setId(hoTro1.getId());
        assertThat(hoTro1).isEqualTo(hoTro2);
        hoTro2.setId(2L);
        assertThat(hoTro1).isNotEqualTo(hoTro2);
        hoTro1.setId(null);
        assertThat(hoTro1).isNotEqualTo(hoTro2);
    }
}
