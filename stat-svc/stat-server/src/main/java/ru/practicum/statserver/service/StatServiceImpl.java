package ru.practicum.statserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.statdto.HitDto;
import ru.practicum.statdto.ViewDto;
import ru.practicum.statserver.mapper.StatMapper;
import ru.practicum.statserver.repository.StatRepository;

import java.time.LocalDateTime;
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
    public List<ViewDto> getStat(LocalDateTime start, LocalDateTime end, String[] uris, boolean unique) {
        Sort sort = Sort.by("hits").descending();
        if (Objects.isNull(uris)) {
            if (unique)
                return statRepository.getAllUniqueIpUri(start, end, sort);
            else
                return statRepository.getAllUri(start, end, sort);
        } else {
            if (unique)
                return statRepository.getCertainUniqueIpUris(start, end, uris, sort);
            else
                return statRepository.getCertainUris(start, end, uris, sort);
        }
    }
}
