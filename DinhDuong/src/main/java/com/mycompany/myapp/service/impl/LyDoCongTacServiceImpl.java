package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.repository.LyDoCongTacRepository;
import com.mycompany.myapp.service.LyDoCongTacService;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import com.mycompany.myapp.service.mapper.LyDoCongTacMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LyDoCongTac}.
 */
@Service
@Transactional
public class LyDoCongTacServiceImpl implements LyDoCongTacService {

    private final Logger log = LoggerFactory.getLogger(LyDoCongTacServiceImpl.class);

    private final LyDoCongTacRepository lyDoCongTacRepository;

    private final LyDoCongTacMapper lyDoCongTacMapper;

    public LyDoCongTacServiceImpl(LyDoCongTacRepository lyDoCongTacRepository, LyDoCongTacMapper lyDoCongTacMapper) {
        this.lyDoCongTacRepository = lyDoCongTacRepository;
        this.lyDoCongTacMapper = lyDoCongTacMapper;
    }

    @Override
    public LyDoCongTacDTO save(LyDoCongTacDTO lyDoCongTacDTO) {
        log.debug("Request to save LyDoCongTac : {}", lyDoCongTacDTO);
        LyDoCongTac lyDoCongTac = lyDoCongTacMapper.toEntity(lyDoCongTacDTO);
        lyDoCongTac = lyDoCongTacRepository.save(lyDoCongTac);
        return lyDoCongTacMapper.toDto(lyDoCongTac);
    }

    @Override
    public LyDoCongTacDTO update(LyDoCongTacDTO lyDoCongTacDTO) {
        log.debug("Request to save LyDoCongTac : {}", lyDoCongTacDTO);
        LyDoCongTac lyDoCongTac = lyDoCongTacMapper.toEntity(lyDoCongTacDTO);
        lyDoCongTac = lyDoCongTacRepository.save(lyDoCongTac);
        return lyDoCongTacMapper.toDto(lyDoCongTac);
    }

    @Override
    public Optional<LyDoCongTacDTO> partialUpdate(LyDoCongTacDTO lyDoCongTacDTO) {
        log.debug("Request to partially update LyDoCongTac : {}", lyDoCongTacDTO);

        return lyDoCongTacRepository
            .findById(lyDoCongTacDTO.getId())
            .map(existingLyDoCongTac -> {
                lyDoCongTacMapper.partialUpdate(existingLyDoCongTac, lyDoCongTacDTO);

                return existingLyDoCongTac;
            })
            .map(lyDoCongTacRepository::save)
            .map(lyDoCongTacMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LyDoCongTacDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LyDoCongTacs");
        return lyDoCongTacRepository.findAll(pageable).map(lyDoCongTacMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LyDoCongTacDTO> findOne(Long id) {
        log.debug("Request to get LyDoCongTac : {}", id);
        return lyDoCongTacRepository.findById(id).map(lyDoCongTacMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LyDoCongTac : {}", id);
        lyDoCongTacRepository.deleteById(id);
    }
}
