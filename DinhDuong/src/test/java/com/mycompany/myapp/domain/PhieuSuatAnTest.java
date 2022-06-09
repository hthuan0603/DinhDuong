package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PhieuSuatAnTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhieuSuatAn.class);
        PhieuSuatAn phieuSuatAn1 = new PhieuSuatAn();
        phieuSuatAn1.setId(1L);
        PhieuSuatAn phieuSuatAn2 = new PhieuSuatAn();
        phieuSuatAn2.setId(phieuSuatAn1.getId());
        assertThat(phieuSuatAn1).isEqualTo(phieuSuatAn2);
        phieuSuatAn2.setId(2L);
        assertThat(phieuSuatAn1).isNotEqualTo(phieuSuatAn2);
        phieuSuatAn1.setId(null);
        assertThat(phieuSuatAn1).isNotEqualTo(phieuSuatAn2);
    }
}
