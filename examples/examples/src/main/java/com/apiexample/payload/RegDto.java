package com.apiexample.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegDto {
    private List<RegistrationDto> collect;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean isFirstPage;
    private boolean isLastPage;
}