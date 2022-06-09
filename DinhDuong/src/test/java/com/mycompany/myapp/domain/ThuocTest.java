package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ThuocTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Thuoc.class);
        Thuoc thuoc1 = new Thuoc();
        thuoc1.setId(1L);
        Thuoc thuoc2 = new Thuoc();
        thuoc2.setId(thuoc1.getId());
        assertThat(thuoc1).isEqualTo(thuoc2);
        thuoc2.setId(2L);
        assertThat(thuoc1).isNotEqualTo(thuoc2);
        thuoc1.setId(null);
        assertThat(thuoc1).isNotEqualTo(thuoc2);
    }
}
