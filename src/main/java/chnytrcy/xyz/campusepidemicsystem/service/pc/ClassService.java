package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: ClassService
 * @Author: ChnyTrcy
 * @Description: 班级服务接口
 * @Date: 2022/8/24 8:20 PM
 * @Version: 1.0
 */
public interface ClassService extends IService<ClassEntity> {

  /**
   * 根据专业编号获取班级列表
   */
  Result<List<ClassListVO>> getClassListByMajorCode();
}
