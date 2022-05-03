package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Dining;
import generator.service.DiningService;
import generator.mapper.DiningMapper;
import org.springframework.stereotype.Service;

/**
* @author 86182
* @description 针对表【dining】的数据库操作Service实现
* @createDate 2022-05-03 23:01:13
*/
@Service
public class DiningServiceImpl extends ServiceImpl<DiningMapper, Dining>
    implements DiningService{

}




