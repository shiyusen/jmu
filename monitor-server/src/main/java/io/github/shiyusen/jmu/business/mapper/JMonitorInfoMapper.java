package io.github.shiyusen.jmu.business.mapper;

import io.github.shiyusen.jmu.business.model.JMonitorInfoPo;
import io.github.shiyusen.jmu.business.model.OperationLogPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 石玉森
 * at created 2020-03-07 15:34
 **/


@Mapper
public interface JMonitorInfoMapper {

    public void deleteMonitorInfoById(JMonitorInfoPo jMonitorInfoPo);

    public void deleteMonitorInfoByCreated(JMonitorInfoPo jMonitorInfoPo);

    public void insertMonitorInfo(JMonitorInfoPo jMonitorInfoPo);

    public List<OperationLogPo> getMonitorInfo(JMonitorInfoPo jMonitorInfoPo);
}
