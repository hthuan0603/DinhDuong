package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.NhanVienTiepNhan;
import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NhanVienTiepNhan} and its DTO {@link NhanVienTiepNhanDTO}.
 */
@Mapper(componentModel = "spring")
public interface NhanVienTiepNhanMapper extends EntityMapper<NhanVienTiepNhanDTO, NhanVienTiepNhan> {}
