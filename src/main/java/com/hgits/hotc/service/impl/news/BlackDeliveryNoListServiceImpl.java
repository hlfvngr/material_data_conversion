package com.hgits.hotc.service.impl.news;

import com.hgits.hotc.common.service.IService;
import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.BlackDeliveryNoListMapper;
import com.hgits.hotc.entity.news.BlackDeliveryNoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Service
public class BlackDeliveryNoListServiceImpl implements IService<BlackDeliveryNoList> {

    @Autowired
    protected BlackDeliveryNoListMapper mapper;

    public Mapper<BlackDeliveryNoList> getMapper() {
        return mapper;
    }

    @Override
    public List<BlackDeliveryNoList> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public BlackDeliveryNoList selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(BlackDeliveryNoList entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<BlackDeliveryNoList> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(BlackDeliveryNoList entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(BlackDeliveryNoList entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<BlackDeliveryNoList> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<BlackDeliveryNoList> value) {
        value.forEach(mapper::insert);
    }
}
