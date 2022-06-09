package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BaoHiemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaoHiem.class);
        BaoHiem baoHiem1 = new BaoHiem();
        baoHiem1.setId(1L);
        BaoHiem baoHiem2 = new BaoHiem();
        baoHiem2.setId(baoHiem1.getId());
        assertThat(baoHiem1).isEqualTo(baoHiem2);
        baoHiem2.setId(2L);
        assertThat(baoHiem1).isNotEqualTo(baoHiem2);
        baoHiem1.setId(null);
        assertThat(baoHiem1).isNotEqualTo(baoHiem2);
    }
}
