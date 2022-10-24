package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.TeacherCommon;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.teacher.GetInformationVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.TeacherAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
