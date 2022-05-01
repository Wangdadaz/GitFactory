package src.main.java.com.example.springboogmp.mybatis.service.impl;

import src.main.java.com.example.springboogmp.mybatis.entity.Menu;
import src.main.java.com.example.springboogmp.mybatis.mapper.MenuMapper;
import src.main.java.com.example.springboogmp.mybatis.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
