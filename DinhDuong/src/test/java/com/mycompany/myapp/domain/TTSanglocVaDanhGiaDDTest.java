package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TTSanglocVaDanhGiaDDTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TTSanglocVaDanhGiaDD.class);
        TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD1 = new TTSanglocVaDanhGiaDD();
        tTSanglocVaDanhGiaDD1.setId(1L);
        TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD2 = new TTSanglocVaDanhGiaDD();
        tTSanglocVaDanhGiaDD2.setId(tTSanglocVaDanhGiaDD1.getId());
        assertThat(tTSanglocVaDanhGiaDD1).isEqualTo(tTSanglocVaDanhGiaDD2);
        tTSanglocVaDanhGiaDD2.setId(2L);
        assertThat(tTSanglocVaDanhGiaDD1).isNotEqualTo(tTSanglocVaDanhGiaDD2);
        tTSanglocVaDanhGiaDD1.setId(null);
        assertThat(tTSanglocVaDanhGiaDD1).isNotEqualTo(tTSanglocVaDanhGiaDD2);
    }
}
