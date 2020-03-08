package io.github.shiyusen.jmu.business.service;

import io.github.shiyusen.jmu.business.model.OperationLogPo;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author 石玉森
 * at created 2020-03-07 18:03
 **/
public interface OperationLoggerService {
    public ResponseEntity deleteOperationLogInfoById(OperationLogPo operationLogPo);

    public ResponseEntity deleteOperationLogInfoByCreated(OperationLogPo operationLogPo);

    public ResponseEntity insertOperationLogInfo(OperationLogPo operationLogPo);

    public ResponseEntity<List<OperationLogPo>> getOperationLogInfo(OperationLogPo operationLogPo);
}
