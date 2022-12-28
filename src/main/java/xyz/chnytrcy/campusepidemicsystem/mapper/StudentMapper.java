package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: StudentMapper
 * @Author: ChnyTrcy
 * @Description: 学生Mapper
 * @Date: 2022/8/24 3:15 PM
 * @Version: 1.0
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

  /**
   * 根据关键词查询学生数据表
   * @param keyword 关键词
   * @param wordType 查询类型
   * @return 结果集
   */
  List<Student> queryStudentByKeyword(@Param("keyword") String keyword,
      @Param("wordType") Integer wordType);
}
