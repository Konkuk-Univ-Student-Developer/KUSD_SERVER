package com.kusd.KUmap.domain.competency.service;

import com.kusd.KUmap.domain.competency.repository.CompetencyFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetencyService {

    private final CompetencyFieldRepository competencyFieldRepository;

}
