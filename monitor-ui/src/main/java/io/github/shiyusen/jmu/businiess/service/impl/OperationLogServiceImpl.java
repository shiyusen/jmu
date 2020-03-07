package io.github.shiyusen.jmu.businiess.service.impl;

import io.github.shiyusen.jmu.businiess.mapper.OperationLogMapper;
import io.github.shiyusen.jmu.businiess.model.OperationLogPo;
import io.github.shiyusen.jmu.businiess.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石玉森
 * @create 2020-03-07 18:05
 **/
@Service
public class OperationLogServiceImpl implements OperationLogService {

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
        operationLogMapper.insertOperationLogInfo(operationLogPo);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<OperationLogPo>> getOperationLogInfo(OperationLogPo operationLogPo) {
        List<OperationLogPo> result = operationLogMapper.getOperationLogInfo(operationLogPo);
        return ResponseEntity.ok().body(result);
    }
}
