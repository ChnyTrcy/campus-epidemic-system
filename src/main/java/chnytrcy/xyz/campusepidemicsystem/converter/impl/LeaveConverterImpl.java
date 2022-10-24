package chnytrcy.xyz.campusepidemicsystem.converter.impl;

import static chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils.MAPPER;

import chnytrcy.xyz.campusepidemicsystem.converter.LeaveConverter;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageLeaveDTO;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.StudentEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leavedetail.LeaveDetailVO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.converter.impl
 * @ClassName: LeaveConverterImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/3 9:46 PM
 * @Version: 1.0
 */
@Component
public class LeaveConverterImpl implements LeaveConverter {

  @Override
  public List<QueryPageLeaveListVO> converter(List<QueryPageLeaveDTO> data) {
    if (data == null) {
      return null;
    } else {
      List<QueryPageLeaveListVO> list = new ArrayList();
      data.forEach((item) -> {
        String reputationString = StudentEnums.getReputationString(item.getReputation());
        List<LeaveDetailVO> a = Lists.newArrayList();
        item.getComplex().forEach(e -> a.add(MAPPER.map(e, LeaveDetailVO.class)));
        QueryPageLeaveListVO map = MAPPER.map(item, QueryPageLeaveListVO.class);
        map.setReputation(reputationString);
        map.setComplex(a);
        list.add(map);
      });
      return list;
    }
  }
}
