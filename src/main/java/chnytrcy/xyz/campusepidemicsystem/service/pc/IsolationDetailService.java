package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.AddIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.QueryIsolationDetailByIdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.ReviseIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationdetail.QueryIsolationDetailByIdVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
