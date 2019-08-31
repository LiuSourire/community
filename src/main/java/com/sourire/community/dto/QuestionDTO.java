package com.sourire.community.dto;

import com.sourire.community.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionDTO {
    private Integer id;

    private String title;

    private String description;

    private Date gmtCreate;

    private Date gmtModify;

    private Long creator;

    private Long viewCount;

    private Long commentCount;

    private Long likeCount;

    private String tag;

    private User user;
}
