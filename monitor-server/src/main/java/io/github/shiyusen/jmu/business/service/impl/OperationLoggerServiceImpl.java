package io.github.shiyusen.jmu.business.service.impl;

import io.github.shiyusen.jmu.business.mapper.OperationLogMapper;
import io.github.shiyusen.jmu.business.model.OperationLogPo;
import io.github.shiyusen.jmu.business.service.OperationLoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石玉森
 * at created 2020-03-07 18:05
 **/
@Slf4j
@Service
public class OperationLoggerServiceImpl implements OperationLoggerService {

    @Value("${test-p}")
    private String test;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public ResponseEntity deleteOperationLogInfoById(OperationLogPo operationLogPo) {
        operationLogMapper.deleteOperationLogInfoById(operationLogPo);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity deleteOperationLogInfoByCreated(OperationLogPo operationLogPo) {
        operationLogMapper.deleteOperationLogInfoByCreated(operationLogPo);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity insertOperationLogInfo(OperationLogPo operationLogPo) {
        try {
            operationLogMapper.insertOperationLogInfo(operationLogPo);
        } catch (Exception e) {
            log.error("insertOperationLogInfo error.msg={}", e.getMessage(), e);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<OperationLogPo>> getOperationLogInfo(OperationLogPo operationLogPo) {
        List<OperationLogPo> result = operationLogMapper.getOperationLogInfo(operationLogPo);
        return ResponseEntity.ok().body(result);
    }
}
