package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveHealth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: LeaveHealthMapper
 * @Author: ChnyTrcy
 * @Description: 请假健康Mapper
 * @Date: 2022/9/19 20:59
 * @Version: 1.0
 */
@Mapper
public interface LeaveHealthMapper extends BaseMapper<LeaveHealth> {

}
