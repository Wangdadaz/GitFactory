package src.main.java.com.example.springboogmp.mybatis.service.impl;

import src.main.java.com.example.springboogmp.mybatis.entity.Emp;
import src.main.java.com.example.springboogmp.mybatis.mapper.EmpMapper;
import src.main.java.com.example.springboogmp.mybatis.service.IEmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-05-01
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

}
