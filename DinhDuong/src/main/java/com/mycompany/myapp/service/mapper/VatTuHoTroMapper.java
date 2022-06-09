package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.HoTro;
import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.service.dto.HoTroDTO;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VatTuHoTro} and its DTO {@link VatTuHoTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface VatTuHoTroMapper extends EntityMapper<VatTuHoTroDTO, VatTuHoTro> {
    @Mapping(target = "hoTro", source = "hoTro", qualifiedByName = "hoTroId")
    VatTuHoTroDTO toDto(VatTuHoTro s);

    @Named("hoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HoTroDTO toDtoHoTroId(HoTro hoTro);
}
