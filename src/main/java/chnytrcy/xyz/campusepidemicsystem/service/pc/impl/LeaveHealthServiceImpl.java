package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveHealthMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveHealth;
import chnytrcy.xyz.campusepidemicsystem.service.pc.LeaveHealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: LeaveHealthServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/11 10:56
 * @Version: 1.0
 */
@Service
public class LeaveHealthServiceImpl extends ServiceImpl<LeaveHealthMapper, LeaveHealth>
    implements LeaveHealthService {

}
