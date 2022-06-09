package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class KetQuaCongTacTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KetQuaCongTac.class);
        KetQuaCongTac ketQuaCongTac1 = new KetQuaCongTac();
        ketQuaCongTac1.setId(1L);
        KetQuaCongTac ketQuaCongTac2 = new KetQuaCongTac();
        ketQuaCongTac2.setId(ketQuaCongTac1.getId());
        assertThat(ketQuaCongTac1).isEqualTo(ketQuaCongTac2);
        ketQuaCongTac2.setId(2L);
        assertThat(ketQuaCongTac1).isNotEqualTo(ketQuaCongTac2);
        ketQuaCongTac1.setId(null);
        assertThat(ketQuaCongTac1).isNotEqualTo(ketQuaCongTac2);
    }
}
