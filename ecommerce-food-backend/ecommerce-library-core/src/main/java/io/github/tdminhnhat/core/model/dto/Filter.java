package io.github.tdminhnhat.core.model.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class Filter {

    @PositiveOrZero(message = "Page number should be greater than or equal 0")
    int pageNumber;

    @Positive(message = "Page size should be greater than 0")
    int pageSize;

    List<Order> orders;

    private Sort getSort() {
        return Sort.by(orders.stream().map(item -> new Sort.Order(item.getDirection(), item.getProperty())).toList());
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(pageNumber, pageSize, getSort());
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    static class Order {

        Sort.Direction direction;

        String property;

    }
}
