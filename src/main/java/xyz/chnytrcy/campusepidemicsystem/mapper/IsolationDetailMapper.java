package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: IsolationDetailMapper
 * @Author: ChnyTrcy
 * @Description: 隔离记录Mapper
 * @Date: 2022/8/24 3:55 PM
 * @Version: 1.0
 */
@Mapper
public interface IsolationDetailMapper extends BaseMapper<IsolationDetail> {

}
