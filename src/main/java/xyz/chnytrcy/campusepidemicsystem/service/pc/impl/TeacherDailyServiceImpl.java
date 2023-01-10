package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.TeacherDailyMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacherdaily.QueryTeacherDailyHistoryCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.TeacherDaily;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.teacherdaily.QueryTeacherDailyHistoryVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.TeacherDailyService;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
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
