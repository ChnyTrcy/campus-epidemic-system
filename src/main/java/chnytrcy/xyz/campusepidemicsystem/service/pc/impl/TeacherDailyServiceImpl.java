package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherDailyMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacherdaily.QueryTeacherDailyHistoryCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.TeacherDaily;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacherdaily.QueryTeacherDailyHistoryVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherDailyService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: TeacherDailyServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 5:02 PM
 * @Version: 1.0
 */
@Service
public class TeacherDailyServiceImpl extends ServiceImpl<TeacherDailyMapper, TeacherDaily>
    implements TeacherDailyService {

  @Override
  public Result<List<QueryTeacherDailyHistoryVO>> queryTeacherDailyHistory(
      QueryTeacherDailyHistoryCommand command) {
    List<TeacherDaily> teacherDailies = getBaseMapper().selectList(
        new LambdaQueryWrapper<TeacherDaily>()
            .eq(TeacherDaily::getCode, command.getCode())
            .orderByDesc(TeacherDaily::getCreateTime)
            .last("limit 10"));
    List<QueryTeacherDailyHistoryVO> vos = DozerUtils.convertList(
        teacherDailies, QueryTeacherDailyHistoryVO.class);
    return ResultFactory.successResult(vos);
  }
}
