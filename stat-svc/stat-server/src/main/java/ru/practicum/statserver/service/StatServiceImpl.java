package ru.practicum.statserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.StatParamDto;
import ru.practicum.statdto.ViewDto;
import ru.practicum.statserver.mapper.StatMapper;
import ru.practicum.statserver.repository.StatRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;

    @Override
    public void addStat(HitDto statHit) {
        statRepository.save(StatMapper.toStatRecord(statHit));
    }

    @Override
    public List<ViewDto> getStat(StatParamDto statParam) {
        Sort sort = Sort.by("hits").descending();
        if (Objects.isNull(statParam.getUris())) {
            if (statParam.isUnique()) {
                return statRepository.getAllUniqueIpUri(statParam.getStart(), statParam.getEnd(), sort);
            } else {
                return statRepository.getAllUri(statParam.getStart(), statParam.getEnd(), sort);
            }
        } else {
            if (statParam.isUnique()) {
                return statRepository.getCertainUniqueIpUris(statParam.getStart(), statParam.getEnd(), statParam.getUris(), sort);
            } else {
                return statRepository.getCertainUris(statParam.getStart(), statParam.getEnd(), statParam.getUris(), sort);
            }
        }
    }
}
