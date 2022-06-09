package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.VatTuHoTro;
import com.mycompany.myapp.repository.VatTuHoTroRepository;
import com.mycompany.myapp.service.VatTuHoTroService;
import com.mycompany.myapp.service.dto.VatTuHoTroDTO;
import com.mycompany.myapp.service.mapper.VatTuHoTroMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VatTuHoTro}.
 */
@Service
@Transactional
public class VatTuHoTroServiceImpl implements VatTuHoTroService {

    private final Logger log = LoggerFactory.getLogger(VatTuHoTroServiceImpl.class);

    private final VatTuHoTroRepository vatTuHoTroRepository;

    private final VatTuHoTroMapper vatTuHoTroMapper;

    public VatTuHoTroServiceImpl(VatTuHoTroRepository vatTuHoTroRepository, VatTuHoTroMapper vatTuHoTroMapper) {
        this.vatTuHoTroRepository = vatTuHoTroRepository;
        this.vatTuHoTroMapper = vatTuHoTroMapper;
    }

    @Override
    public VatTuHoTroDTO save(VatTuHoTroDTO vatTuHoTroDTO) {
        log.debug("Request to save VatTuHoTro : {}", vatTuHoTroDTO);
        VatTuHoTro vatTuHoTro = vatTuHoTroMapper.toEntity(vatTuHoTroDTO);
        vatTuHoTro = vatTuHoTroRepository.save(vatTuHoTro);
        return vatTuHoTroMapper.toDto(vatTuHoTro);
    }

    @Override
    public VatTuHoTroDTO update(VatTuHoTroDTO vatTuHoTroDTO) {
        log.debug("Request to save VatTuHoTro : {}", vatTuHoTroDTO);
        VatTuHoTro vatTuHoTro = vatTuHoTroMapper.toEntity(vatTuHoTroDTO);
        vatTuHoTro = vatTuHoTroRepository.save(vatTuHoTro);
        return vatTuHoTroMapper.toDto(vatTuHoTro);
    }

    @Override
    public Optional<VatTuHoTroDTO> partialUpdate(VatTuHoTroDTO vatTuHoTroDTO) {
        log.debug("Request to partially update VatTuHoTro : {}", vatTuHoTroDTO);

        return vatTuHoTroRepository
            .findById(vatTuHoTroDTO.getId())
            .map(existingVatTuHoTro -> {
                vatTuHoTroMapper.partialUpdate(existingVatTuHoTro, vatTuHoTroDTO);

                return existingVatTuHoTro;
            })
            .map(vatTuHoTroRepository::save)
            .map(vatTuHoTroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VatTuHoTroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VatTuHoTros");
        return vatTuHoTroRepository.findAll(pageable).map(vatTuHoTroMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VatTuHoTroDTO> findOne(Long id) {
        log.debug("Request to get VatTuHoTro : {}", id);
        return vatTuHoTroRepository.findById(id).map(vatTuHoTroMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VatTuHoTro : {}", id);
        vatTuHoTroRepository.deleteById(id);
    }
}
