package com.dxc.ddc.springboot.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class PageableResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T content;
    private long total;
    private int page;
    private int size;
    private PageInfo pageInfo;

    public PageableResult() {
    }

    public PageableResult(int page, int size, long total, T content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.content = content;
    }

    @JsonProperty
    public long getTotal() {
        return total;
    }

    @JsonProperty
    public int getPage() {
        return page;
    }

    @JsonProperty
    public int getSize() {
        return size;
    }

    @JsonProperty
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    @JsonProperty
    public PageInfo getPageInfo() {
        if (pageInfo == null) {
            pageInfo = new PageInfo();
            pageInfo.setTotalPage(getTotalPages());
            pageInfo.setPageSize(getSize());
            pageInfo.setCurrentPage(getPage() + 1);
            pageInfo.setTotalRecords((int) getTotal());
        }

        return pageInfo;
    }

    @JsonProperty
    public T getContent() {
        return content;
    }

    public static Pageable createPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        return PageRequest.of(page - 1, size, direction, properties);
    }
}
