package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel
 * @ClassName: AnalysisListener
 * @Author: ChnyTrcy
 * @Description: Excel解析抽象监听器
 * @Date: 2022/12/15 15:05
 * @Version: 1.0
 */
public abstract class AnalysisBaseListener<T,K> extends AnalysisEventListener<T> {

  protected List<ErrorEntity> errorList = Lists.newArrayList();

  protected Integer analysisRow = 3;

  public List<ErrorEntity> getErrorList() {
    List<ErrorEntity> errorEntities = ObjectUtil.cloneByStream(this.errorList);
    errorList.clear();
    return errorEntities;
  }

  protected abstract void fillData(List<K> data);

  protected abstract void batchInsert();

  protected abstract void cleanList();

  protected abstract Boolean validationData(T bo);
}
