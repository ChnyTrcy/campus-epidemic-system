package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: StudentDailyMapper
 * @Author: ChnyTrcy
 * @Description: 学生日常请假Mapper
 * @Date: 2022/10/7 10:27
 * @Version: 1.0
 */
@Mapper
public interface StudentDailyMapper extends BaseMapper<StudentDaily> {

}
