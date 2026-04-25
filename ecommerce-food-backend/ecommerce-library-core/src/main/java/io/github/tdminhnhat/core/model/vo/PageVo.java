package io.github.tdminhnhat.core.model.vo;

import java.util.List;

public record PageVo<S extends BaseVo>(
        List<S> data,

        int size,

        int page,

        int currentPage,

        boolean next,

        boolean previous
) {
}
