package xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.easyexcel
 * @ClassName: AnalysisListener
 * @Author: ChnyTrcy
 * @Description: Excel解析抽象监听器
 * @Date: 2022/12/15 15:05
 * @Version: 1.0
 */
@Slf4j
public abstract class AnalysisBaseListener<T,K> extends AnalysisEventListener<T> {

  protected List<ErrorEntity> errorList = Lists.newArrayList();

  protected List<String> addCodeList = Lists.newArrayList();

  protected List<K> addList = Lists.newArrayList();

  protected List<T> boList = Lists.newArrayList();

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

  public abstract Class<? extends Base> getT();
}
