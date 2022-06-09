package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DieuTriTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DieuTri.class);
        DieuTri dieuTri1 = new DieuTri();
        dieuTri1.setId(1L);
        DieuTri dieuTri2 = new DieuTri();
        dieuTri2.setId(dieuTri1.getId());
        assertThat(dieuTri1).isEqualTo(dieuTri2);
        dieuTri2.setId(2L);
        assertThat(dieuTri1).isNotEqualTo(dieuTri2);
        dieuTri1.setId(null);
        assertThat(dieuTri1).isNotEqualTo(dieuTri2);
    }
}
