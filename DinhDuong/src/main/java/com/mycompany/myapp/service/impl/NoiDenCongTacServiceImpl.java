package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.repository.NoiDenCongTacRepository;
import com.mycompany.myapp.service.NoiDenCongTacService;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import com.mycompany.myapp.service.mapper.NoiDenCongTacMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NoiDenCongTac}.
 */
@Service
@Transactional
public class NoiDenCongTacServiceImpl implements NoiDenCongTacService {

    private final Logger log = LoggerFactory.getLogger(NoiDenCongTacServiceImpl.class);

    private final NoiDenCongTacRepository noiDenCongTacRepository;

    private final NoiDenCongTacMapper noiDenCongTacMapper;

    public NoiDenCongTacServiceImpl(NoiDenCongTacRepository noiDenCongTacRepository, NoiDenCongTacMapper noiDenCongTacMapper) {
        this.noiDenCongTacRepository = noiDenCongTacRepository;
        this.noiDenCongTacMapper = noiDenCongTacMapper;
    }

    @Override
    public NoiDenCongTacDTO save(NoiDenCongTacDTO noiDenCongTacDTO) {
        log.debug("Request to save NoiDenCongTac : {}", noiDenCongTacDTO);
        NoiDenCongTac noiDenCongTac = noiDenCongTacMapper.toEntity(noiDenCongTacDTO);
        noiDenCongTac = noiDenCongTacRepository.save(noiDenCongTac);
        return noiDenCongTacMapper.toDto(noiDenCongTac);
    }

    @Override
    public NoiDenCongTacDTO update(NoiDenCongTacDTO noiDenCongTacDTO) {
        log.debug("Request to save NoiDenCongTac : {}", noiDenCongTacDTO);
        NoiDenCongTac noiDenCongTac = noiDenCongTacMapper.toEntity(noiDenCongTacDTO);
        noiDenCongTac = noiDenCongTacRepository.save(noiDenCongTac);
        return noiDenCongTacMapper.toDto(noiDenCongTac);
    }

    @Override
    public Optional<NoiDenCongTacDTO> partialUpdate(NoiDenCongTacDTO noiDenCongTacDTO) {
        log.debug("Request to partially update NoiDenCongTac : {}", noiDenCongTacDTO);

        return noiDenCongTacRepository
            .findById(noiDenCongTacDTO.getId())
            .map(existingNoiDenCongTac -> {
                noiDenCongTacMapper.partialUpdate(existingNoiDenCongTac, noiDenCongTacDTO);

                return existingNoiDenCongTac;
            })
            .map(noiDenCongTacRepository::save)
            .map(noiDenCongTacMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoiDenCongTacDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoiDenCongTacs");
        return noiDenCongTacRepository.findAll(pageable).map(noiDenCongTacMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoiDenCongTacDTO> findOne(Long id) {
        log.debug("Request to get NoiDenCongTac : {}", id);
        return noiDenCongTacRepository.findById(id).map(noiDenCongTacMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoiDenCongTac : {}", id);
        noiDenCongTacRepository.deleteById(id);
    }
}
