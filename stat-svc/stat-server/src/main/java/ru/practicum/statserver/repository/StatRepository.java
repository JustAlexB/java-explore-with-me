package ru.practicum.statserver.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statdto.ViewDto;
import ru.practicum.statserver.model.StatRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<StatRecord, Long> {
    @Query("select new ru.practicum.statdto.ViewDto(st.app, st.uri, count(distinct st.ip) as hits)" +
            "from StatRecord st " +
            "where st.created between ?1 and ?2 " +
            "group by st.app, st.uri")
    List<ViewDto> getAllUniqueIpUri(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query("select new ru.practicum.statdto.ViewDto(st.app, st.uri, count(st.ip) as hits)" +
            "from StatRecord st " +
            "where st.created between ?1 and ?2 " +
            "group by st.app, st.uri")
    List<ViewDto> getAllUri(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query("select new ru.practicum.statdto.ViewDto(st.app, st.uri, count(distinct st.ip) as hits)" +
            "from StatRecord st " +
            "where st.created between ?1 and ?2 " +
            "and st.uri in ?3 " +
            "group by st.app, st.uri")
    List<ViewDto> getCertainUniqueIpUris(LocalDateTime start, LocalDateTime end, String[] uris, Sort sort);

    @Query("select new ru.practicum.statdto.ViewDto(st.app, st.uri, count(st.ip) as hits)" +
            "from StatRecord st " +
            "where st.created between ?1 and ?2 " +
            "and st.uri in ?3 " +
            "group by st.app, st.uri")
    List<ViewDto> getCertainUris(LocalDateTime start, LocalDateTime end, String[] uris, Sort sort);
}
