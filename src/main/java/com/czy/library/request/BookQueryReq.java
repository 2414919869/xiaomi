package com.czy.library.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookQueryReq {

    @NotBlank(message = "【书名】不能为空")
    private String bookName;

    @NotBlank(message = "【作者】不能为空")
    private String authorName;

    @NotBlank(message = "【书籍类型】不能为空")
    private String type;

}
