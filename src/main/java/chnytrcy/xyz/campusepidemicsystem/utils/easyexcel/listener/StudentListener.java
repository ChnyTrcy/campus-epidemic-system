package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.StudentBO;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: StudentListener
 * @Author: ChnyTrcy
 * @Description: 学生模板Excel解析监听器
 * @Date: 2022/11/28 20:46
 * @Version: 1.0
 */
@Slf4j
@Component
public class StudentListener extends AnalysisEventListener<StudentBO> {

  private List<errorEntity> errorList = Lists.newArrayList();
  private List<StudentBO> studentBOList = Lists.newArrayList();

  private Integer analysisRow = 2;

  static {
    log.info("解析Excel开始了......");
  }

  @Override
  public void invoke(StudentBO studentBO, AnalysisContext analysisContext) {
    log.info("解析到一条数据:{}", JSON.toJSONString(studentBO));
    studentBOList.add(studentBO);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.info("解析完成了");
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  public class errorEntity extends Base {

    @ApiModelProperty("行数")
    private Integer row;

    @ApiModelProperty("解析内容（JSON）")
    private String content;

    @ApiModelProperty("错误内容")
    private String errorContent;
  }

}
