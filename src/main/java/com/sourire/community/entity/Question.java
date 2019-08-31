package com.sourire.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Question extends Model<Question> {

private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
