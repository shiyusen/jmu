package io.github.shiyusen.jmu.businiess.service;

import io.github.shiyusen.jmu.businiess.model.OperationLogPo;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author 石玉森
 * @create 2020-03-07 18:03
 **/


public interface OperationLogService {
    public ResponseEntity deleteOperationLogInfoById(OperationLogPo operationLogPo);

    public ResponseEntity deleteOperationLogInfoByCreated(OperationLogPo operationLogPo);

    public ResponseEntity insertOperationLogInfo(OperationLogPo operationLogPo);

    public ResponseEntity<List<OperationLogPo>> getOperationLogInfo(OperationLogPo operationLogPo);
}
