package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BaoCaoTaiChinhTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaoCaoTaiChinh.class);
        BaoCaoTaiChinh baoCaoTaiChinh1 = new BaoCaoTaiChinh();
        baoCaoTaiChinh1.setId(1L);
        BaoCaoTaiChinh baoCaoTaiChinh2 = new BaoCaoTaiChinh();
        baoCaoTaiChinh2.setId(baoCaoTaiChinh1.getId());
        assertThat(baoCaoTaiChinh1).isEqualTo(baoCaoTaiChinh2);
        baoCaoTaiChinh2.setId(2L);
        assertThat(baoCaoTaiChinh1).isNotEqualTo(baoCaoTaiChinh2);
        baoCaoTaiChinh1.setId(null);
        assertThat(baoCaoTaiChinh1).isNotEqualTo(baoCaoTaiChinh2);
    }
}
