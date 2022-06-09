package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.HoTro;
import com.mycompany.myapp.domain.KetQuaCongTac;
import com.mycompany.myapp.service.dto.HoTroDTO;
import com.mycompany.myapp.service.dto.KetQuaCongTacDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link KetQuaCongTac} and its DTO {@link KetQuaCongTacDTO}.
 */
@Mapper(componentModel = "spring")
public interface KetQuaCongTacMapper extends EntityMapper<KetQuaCongTacDTO, KetQuaCongTac> {
    @Mapping(target = "hoTro", source = "hoTro", qualifiedByName = "hoTroId")
    KetQuaCongTacDTO toDto(KetQuaCongTac s);

    @Named("hoTroId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HoTroDTO toDtoHoTroId(HoTro hoTro);
}
