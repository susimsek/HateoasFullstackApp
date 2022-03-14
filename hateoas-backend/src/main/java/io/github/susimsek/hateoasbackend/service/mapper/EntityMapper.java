package io.github.susimsek.hateoasbackend.service.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);
}
