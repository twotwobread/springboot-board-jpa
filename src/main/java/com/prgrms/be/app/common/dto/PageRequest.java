package com.prgrms.be.app.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.springframework.data.domain.Sort.Direction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageRequest {

    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 20;

    private int page;
    private int size;
    private Direction direction;

    public PageRequest(int page, int size) {
        this(page, size, Direction.DESC);
    }

    public PageRequest(int page, int size, Direction direction) {
        this.page = page <= 0 ? 1 : page;
        this.size = isRangeSize(size) ? size : DEFAULT_SIZE;
        this.direction = direction;
    }

    private boolean isRangeSize(int size) {
        return size > 0 && size <= MAX_SIZE;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdAt");
    }
}
