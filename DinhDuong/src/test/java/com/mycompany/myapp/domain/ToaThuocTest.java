package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToaThuocTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToaThuoc.class);
        ToaThuoc toaThuoc1 = new ToaThuoc();
        toaThuoc1.setId(1L);
        ToaThuoc toaThuoc2 = new ToaThuoc();
        toaThuoc2.setId(toaThuoc1.getId());
        assertThat(toaThuoc1).isEqualTo(toaThuoc2);
        toaThuoc2.setId(2L);
        assertThat(toaThuoc1).isNotEqualTo(toaThuoc2);
        toaThuoc1.setId(null);
        assertThat(toaThuoc1).isNotEqualTo(toaThuoc2);
    }
}
