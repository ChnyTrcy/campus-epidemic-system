package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: LeaveDetailMapper
 * @Author: ChnyTrcy
 * @Description: 离校申请过程Mapper
 * @Date: 2022/9/7 9:22 PM
 * @Version: 1.0
 */
@Mapper
public interface LeaveDetailMapper extends BaseMapper<LeaveDetail> {

}
