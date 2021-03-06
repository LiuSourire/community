package com.sourire.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    private String accountId;

    private String name;

    private String token;

    private Date gmtCreate;

    private Date gmtModify;

    private String avatarUrl;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
