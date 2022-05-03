package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.TClass;
import generator.service.TClassService;
import generator.mapper.TClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 86182
* @description 针对表【t_class】的数据库操作Service实现
* @createDate 2022-05-03 23:02:29
*/
@Service
public class TClassServiceImpl extends ServiceImpl<TClassMapper, TClass>
    implements TClassService{

}




