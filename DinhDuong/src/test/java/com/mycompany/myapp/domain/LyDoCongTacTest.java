package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LyDoCongTacTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LyDoCongTac.class);
        LyDoCongTac lyDoCongTac1 = new LyDoCongTac();
        lyDoCongTac1.setId(1L);
        LyDoCongTac lyDoCongTac2 = new LyDoCongTac();
        lyDoCongTac2.setId(lyDoCongTac1.getId());
        assertThat(lyDoCongTac1).isEqualTo(lyDoCongTac2);
        lyDoCongTac2.setId(2L);
        assertThat(lyDoCongTac1).isNotEqualTo(lyDoCongTac2);
        lyDoCongTac1.setId(null);
        assertThat(lyDoCongTac1).isNotEqualTo(lyDoCongTac2);
    }
}
