package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.TeacherMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.teacher.GetInformationVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.TeacherAppService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: TeacherAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/13 5:32 PM
 * @Version: 1.0
 */
@Service
public class TeacherAppServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherAppService {

  @Autowired private TeacherCommon teacherCommon;

  @Override
  public Result<GetInformationVO> getInformation() {
    Teacher teacher = teacherCommon.getTeacher();
    GetInformationVO convert = DozerUtils.convert(teacher, GetInformationVO.class);
    return ResultFactory.successResult(convert);
  }
}
