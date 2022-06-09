package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.HoTro;
import com.mycompany.myapp.repository.HoTroRepository;
import com.mycompany.myapp.service.HoTroService;
import com.mycompany.myapp.service.dto.HoTroDTO;
import com.mycompany.myapp.service.mapper.HoTroMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HoTro}.
 */
@Service
@Transactional
public class HoTroServiceImpl implements HoTroService {

    private final Logger log = LoggerFactory.getLogger(HoTroServiceImpl.class);

    private final HoTroRepository hoTroRepository;

    private final HoTroMapper hoTroMapper;

    public HoTroServiceImpl(HoTroRepository hoTroRepository, HoTroMapper hoTroMapper) {
        this.hoTroRepository = hoTroRepository;
        this.hoTroMapper = hoTroMapper;
    }

    @Override
    public HoTroDTO save(HoTroDTO hoTroDTO) {
        log.debug("Request to save HoTro : {}", hoTroDTO);
        HoTro hoTro = hoTroMapper.toEntity(hoTroDTO);
        hoTro = hoTroRepository.save(hoTro);
        return hoTroMapper.toDto(hoTro);
    }

    @Override
    public HoTroDTO update(HoTroDTO hoTroDTO) {
        log.debug("Request to save HoTro : {}", hoTroDTO);
        HoTro hoTro = hoTroMapper.toEntity(hoTroDTO);
        hoTro = hoTroRepository.save(hoTro);
        return hoTroMapper.toDto(hoTro);
    }

    @Override
    public Optional<HoTroDTO> partialUpdate(HoTroDTO hoTroDTO) {
        log.debug("Request to partially update HoTro : {}", hoTroDTO);

        return hoTroRepository
            .findById(hoTroDTO.getId())
            .map(existingHoTro -> {
                hoTroMapper.partialUpdate(existingHoTro, hoTroDTO);

                return existingHoTro;
            })
            .map(hoTroRepository::save)
            .map(hoTroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HoTroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HoTros");
        return hoTroRepository.findAll(pageable).map(hoTroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoTroDTO> findOne(Long id) {
        log.debug("Request to get HoTro : {}", id);
        return hoTroRepository.findById(id).map(hoTroMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HoTro : {}", id);
        hoTroRepository.deleteById(id);
    }
}
