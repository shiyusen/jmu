package io.github.shiyusen.jmu.business.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 石玉森
 * @create 2020-03-09 16:14
 **/
@Data
public class JMonitorInfoPo {
    private Long id;
    private String process;
    private String pid;
    private String code;
    private String name;
    private String value;
    private Date created;
}
