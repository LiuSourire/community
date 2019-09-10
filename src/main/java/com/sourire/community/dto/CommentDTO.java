package com.sourire.community.dto;

import lombok.Data;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/10 9:03
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
