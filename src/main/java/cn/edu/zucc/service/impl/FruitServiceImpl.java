package cn.edu.zucc.service.impl;

import cn.edu.zucc.entity.Fruit;
import cn.edu.zucc.mapper.FruitMapper;
import cn.edu.zucc.service.FruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-05-20
 */
@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements FruitService {

}
