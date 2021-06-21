package com.awtwvo.data.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchDTO {

    @NotEmpty(message = "Query cannot be empty")
    private String query;

}
