package io.github.shiyusen.jmu.business.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 石玉森
 * @create 2020-03-07 15:30
 **/
@Data
public class OperationLogPo extends BaseQueryVo {

    private Long id;
    private String name;
    private String ip;
    private String userName;
    private String level;
    private String input;
    private String output;
    private Date created;
}
