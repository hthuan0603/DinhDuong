package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BenhNhanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenhNhan.class);
        BenhNhan benhNhan1 = new BenhNhan();
        benhNhan1.setId(1L);
        BenhNhan benhNhan2 = new BenhNhan();
        benhNhan2.setId(benhNhan1.getId());
        assertThat(benhNhan1).isEqualTo(benhNhan2);
        benhNhan2.setId(2L);
        assertThat(benhNhan1).isNotEqualTo(benhNhan2);
        benhNhan1.setId(null);
        assertThat(benhNhan1).isNotEqualTo(benhNhan2);
    }
}
