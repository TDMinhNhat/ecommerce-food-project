package io.github.tdminhnhat.core.service;

import io.github.tdminhnhat.core.model.dto.Filter;

import java.util.List;
import java.util.Optional;

public interface IService<S extends Record, P extends Number> {

    Object save(S request);

    Object update(P id, S request);

    Object delete(P id);

    Optional<Object> findById(P id);

    List<Object> findAll();

    <T extends Filter> List<Object> findAll(T filter);
}
