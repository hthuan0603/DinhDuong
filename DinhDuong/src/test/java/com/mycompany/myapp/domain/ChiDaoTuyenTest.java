package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChiDaoTuyenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiDaoTuyen.class);
        ChiDaoTuyen chiDaoTuyen1 = new ChiDaoTuyen();
        chiDaoTuyen1.setId(1L);
        ChiDaoTuyen chiDaoTuyen2 = new ChiDaoTuyen();
        chiDaoTuyen2.setId(chiDaoTuyen1.getId());
        assertThat(chiDaoTuyen1).isEqualTo(chiDaoTuyen2);
        chiDaoTuyen2.setId(2L);
        assertThat(chiDaoTuyen1).isNotEqualTo(chiDaoTuyen2);
        chiDaoTuyen1.setId(null);
        assertThat(chiDaoTuyen1).isNotEqualTo(chiDaoTuyen2);
    }
}
