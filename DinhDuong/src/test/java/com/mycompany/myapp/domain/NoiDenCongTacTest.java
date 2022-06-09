package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoiDenCongTacTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDenCongTac.class);
        NoiDenCongTac noiDenCongTac1 = new NoiDenCongTac();
        noiDenCongTac1.setId(1L);
        NoiDenCongTac noiDenCongTac2 = new NoiDenCongTac();
        noiDenCongTac2.setId(noiDenCongTac1.getId());
        assertThat(noiDenCongTac1).isEqualTo(noiDenCongTac2);
        noiDenCongTac2.setId(2L);
        assertThat(noiDenCongTac1).isNotEqualTo(noiDenCongTac2);
        noiDenCongTac1.setId(null);
        assertThat(noiDenCongTac1).isNotEqualTo(noiDenCongTac2);
    }
}
