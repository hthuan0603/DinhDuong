package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class KetQuaCongTacDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KetQuaCongTacDTO.class);
        KetQuaCongTacDTO ketQuaCongTacDTO1 = new KetQuaCongTacDTO();
        ketQuaCongTacDTO1.setId(1L);
        KetQuaCongTacDTO ketQuaCongTacDTO2 = new KetQuaCongTacDTO();
        assertThat(ketQuaCongTacDTO1).isNotEqualTo(ketQuaCongTacDTO2);
        ketQuaCongTacDTO2.setId(ketQuaCongTacDTO1.getId());
        assertThat(ketQuaCongTacDTO1).isEqualTo(ketQuaCongTacDTO2);
        ketQuaCongTacDTO2.setId(2L);
        assertThat(ketQuaCongTacDTO1).isNotEqualTo(ketQuaCongTacDTO2);
        ketQuaCongTacDTO1.setId(null);
        assertThat(ketQuaCongTacDTO1).isNotEqualTo(ketQuaCongTacDTO2);
    }
}
