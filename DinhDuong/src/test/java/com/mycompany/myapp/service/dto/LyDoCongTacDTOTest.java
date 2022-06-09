package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LyDoCongTacDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LyDoCongTacDTO.class);
        LyDoCongTacDTO lyDoCongTacDTO1 = new LyDoCongTacDTO();
        lyDoCongTacDTO1.setId(1L);
        LyDoCongTacDTO lyDoCongTacDTO2 = new LyDoCongTacDTO();
        assertThat(lyDoCongTacDTO1).isNotEqualTo(lyDoCongTacDTO2);
        lyDoCongTacDTO2.setId(lyDoCongTacDTO1.getId());
        assertThat(lyDoCongTacDTO1).isEqualTo(lyDoCongTacDTO2);
        lyDoCongTacDTO2.setId(2L);
        assertThat(lyDoCongTacDTO1).isNotEqualTo(lyDoCongTacDTO2);
        lyDoCongTacDTO1.setId(null);
        assertThat(lyDoCongTacDTO1).isNotEqualTo(lyDoCongTacDTO2);
    }
}
