package io.github.shiyusen.jmu.businiess.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 石玉森
 * @create 2020-03-07 17:57
 **/
@Data
public class BaseQueryVo {
    private Date start;
    private Date end;
    private int index;
    private int size;
}
