package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.AddIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.QueryIsolationDetailByIdCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.ReviseIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationdetail.QueryIsolationDetailByIdVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: IsolationDetailService
 * @Author: ChnyTrcy
 * @Description: 隔离记录服务接口
 * @Date: 2022/8/24 3:55 PM
 * @Version: 1.0
 */
public interface IsolationDetailService extends IService<IsolationDetail> {

  /**
   * 根据隔离人员Id查询隔离记录
   */
  Result<List<QueryIsolationDetailByIdVO>> queryIsolationDetailById(
      QueryIsolationDetailByIdCommand command);

  /**
   * 添加隔离人员体温监测
   */
  Result<Void> addIsolationDetail(AddIsolationDetailCommand command);

  /**
   * 修改某一条隔离体温记录
   */
  Result<Void> reviseIsolationDetail(ReviseIsolationDetailCommand command);
}
