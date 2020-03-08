package io.github.shiyusen.jmu.business.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 石玉森
 * at created 2020-03-07 17:57
 **/
@Data
public class BaseQueryVo {
    private Date start;
    private Date end;
    private int index;
    private int size;
}
