package com.czy.library.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookSaveReq {

    private Integer id;

    @NotBlank(message = "【书名】不能为空")
    private String bookName;

    @NotBlank(message = "【作者】不能为空")
    private String authorName;

    @NotBlank(message = "【书籍类型】不能为空")
    private String type;

    @NotNull(message = "【书籍数量】不能为空")
    private Integer number;
}
