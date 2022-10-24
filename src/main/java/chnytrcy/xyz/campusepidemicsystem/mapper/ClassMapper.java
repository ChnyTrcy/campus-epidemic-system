package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.mapper
 * @ClassName: ClassMapper
 * @Author: ChnyTrcy
 * @Description: 班级Mapper
 * @Date: 2022/8/24 8:19 PM
 * @Version: 1.0
 */
@Mapper
public interface ClassMapper extends BaseMapper<ClassEntity> {

  /**
   * 根据专业编号获取班级列表
   */
  List<ClassListVO> getClassListByMajorCode();
}
