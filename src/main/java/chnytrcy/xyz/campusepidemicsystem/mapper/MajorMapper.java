package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.mapper
 * @InterfaceName: MajorMapper
 * @Author: ChnyTrcy
 * @Description: 专业Mapper
 * @Date: 2022/8/24 8:26 PM
 * @Version: 1.0
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {

  /**
   * 根据院系编号获得专业列表
   * @return
   */
  List<MajorListVO> getMajorListByDept();
}
