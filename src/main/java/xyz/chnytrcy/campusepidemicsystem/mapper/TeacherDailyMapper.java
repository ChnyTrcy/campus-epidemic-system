package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.TeacherDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: TeacherDailyMapper
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡Mapper
 * @Date: 2022/8/24 5:01 PM
 * @Version: 1.0
 */
@Mapper
public interface TeacherDailyMapper extends BaseMapper<TeacherDaily> {

}
