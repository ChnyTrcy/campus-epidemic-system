package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: AdminMapper
 * @Author: ChnyTrcy
 * @Description: 管理员Mapper
 * @Date: 2022/8/30 10:01 AM
 * @Version: 1.0
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
