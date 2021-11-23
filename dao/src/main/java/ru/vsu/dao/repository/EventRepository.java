package ru.vsu.dao.repository;

import ru.vsu.dao.db.annotation.Query;
import ru.vsu.dao.entity.Event;
import ru.vsu.dao.entity.EventType;
import ru.vsu.dao.db.annotation.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JPARepository<Event, Integer> {

    Collection<Event> findByEventTypeContains(List<EventType> types);

    @Query(query = "")
    Collection<Event> findByYearAndByEventTypeContains(Integer year, List<EventType> types);

    @Query(query = "")
    Collection<Event> findByMonthAndEventTypeContains(Integer month, List<EventType> types);

    Collection<Event> findByNameLikeAndEventTypeContains(String name, List<EventType> types);

    Collection<Event> findByDateTimeAndEventTypeContains(LocalDateTime dateTime, List<EventType> types);

    Collection<Event> findByDateTimeGreaterThanAndEventTypeContains(LocalDateTime dateTime, List<EventType> types);

    Collection<Event> findByDateTimeLessThanAndEventTypeContains(LocalDateTime dateTime, List<EventType> types);

}
