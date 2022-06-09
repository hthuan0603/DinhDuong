package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoiDenCongTacDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDenCongTacDTO.class);
        NoiDenCongTacDTO noiDenCongTacDTO1 = new NoiDenCongTacDTO();
        noiDenCongTacDTO1.setId(1L);
        NoiDenCongTacDTO noiDenCongTacDTO2 = new NoiDenCongTacDTO();
        assertThat(noiDenCongTacDTO1).isNotEqualTo(noiDenCongTacDTO2);
        noiDenCongTacDTO2.setId(noiDenCongTacDTO1.getId());
        assertThat(noiDenCongTacDTO1).isEqualTo(noiDenCongTacDTO2);
        noiDenCongTacDTO2.setId(2L);
        assertThat(noiDenCongTacDTO1).isNotEqualTo(noiDenCongTacDTO2);
        noiDenCongTacDTO1.setId(null);
        assertThat(noiDenCongTacDTO1).isNotEqualTo(noiDenCongTacDTO2);
    }
}
