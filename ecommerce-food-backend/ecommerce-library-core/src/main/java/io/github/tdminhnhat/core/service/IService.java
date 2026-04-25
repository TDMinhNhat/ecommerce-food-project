package io.github.tdminhnhat.core.service;

import io.github.tdminhnhat.core.entity.BaseEntity;
import io.github.tdminhnhat.core.model.dto.Filter;
import io.github.tdminhnhat.core.model.vo.BaseVo;
import io.github.tdminhnhat.core.model.vo.PageVo;

import java.util.List;

public interface IService<S extends Record, P extends Number> {

    <R extends BaseVo> R save(S request) throws Exception;

    <R extends BaseVo> R update(P id, S request) throws Exception;

    <R extends BaseVo> R delete(P id) throws Exception;

    <R extends BaseVo> R findById(P id);

    <R extends BaseVo> List<R> findAll();

    default <R extends BaseVo, T extends Filter> PageVo<R> findAll(T filter) {
        return null;
    }

    <R extends BaseVo> R getVo(BaseEntity entity) throws Exception;
}
