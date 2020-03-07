package io.github.shiyusen.jmu.business.mapper;

import io.github.shiyusen.jmu.business.model.OperationLogPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 石玉森
 * @create 2020-03-07 15:34
 **/


@Mapper
public interface OperationLogMapper {

    public void deleteOperationLogInfoById(OperationLogPo operationLogPo);

    public void deleteOperationLogInfoByCreated(OperationLogPo operationLogPo);

    public void insertOperationLogInfo(OperationLogPo operationLogPo);

    public List<OperationLogPo> getOperationLogInfo(OperationLogPo operationLogPo);
}
