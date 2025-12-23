package org.example.expert.domain.log.util;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.log.dto.LogRequestDto;
import org.example.expert.domain.log.entity.Log;
import org.example.expert.domain.log.repository.LogRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LogSaveUtil {

    private final LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRequestLog (LogRequestDto dto){

        Log log = new Log(dto.getRequestType(), dto.getRequestContent());

        logRepository.save(log);
    }
}
