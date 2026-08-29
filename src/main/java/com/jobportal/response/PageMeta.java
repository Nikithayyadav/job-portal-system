package com.jobportal.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageMeta {

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
}