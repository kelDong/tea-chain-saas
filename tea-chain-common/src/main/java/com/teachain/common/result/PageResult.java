package com.teachain.common.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long total;
    private long size;
    private long current;
    private long pages;
    private List<T> records;

    public static <T> PageResult<T> of(long total, long size, long current, List<T> records) {
        PageResult<T> r = new PageResult<>();
        r.setTotal(total);
        r.setSize(size);
        r.setCurrent(current);
        r.setPages(size == 0 ? 0 : (total + size - 1) / size);
        r.setRecords(records == null ? Collections.emptyList() : records);
        return r;
    }

    public static <T> PageResult<T> empty(long size, long current) {
        return of(0, size, current, Collections.emptyList());
    }
}
