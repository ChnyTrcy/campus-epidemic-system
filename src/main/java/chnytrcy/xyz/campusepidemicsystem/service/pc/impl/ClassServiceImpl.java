package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ClassService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: ClassServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 8:20 PM
 * @Version: 1.0
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, ClassEntity> implements ClassService {

  @Override
  public Result<List<ClassListVO>> getClassListByMajorCode() {
    List<ClassListVO> key = getBaseMapper().getClassListByMajorCode();
    return ResultFactory.successResult(key);
  }
}
