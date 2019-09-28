package com.sourire.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author sourire
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment extends Model<Comment> {

private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 父一级id
     */
    @TableField("parentId")
    private Integer parentId;

    /**
     * 评论类型
     */
    private Integer type;

    /**
     * 评论人id
     */
    private Integer commentator;

    /**
     * 评论时间
     */
    private Date gmtCreate;

    /**
     * 修改评论时间
     */
    private Date gmtModify;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Long commentCount;

    @Override
    protected Serializable pkVal() {
        return id;
    }

}
