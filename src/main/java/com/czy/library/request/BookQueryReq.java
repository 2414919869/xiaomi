package com.czy.library.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookQueryReq {


    private String bookName;

    private String authorName;

    private String type;

}
