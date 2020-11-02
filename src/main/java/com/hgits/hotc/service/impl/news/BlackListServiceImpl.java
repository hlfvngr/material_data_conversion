package com.hgits.hotc.service.impl.news;

import com.hgits.hotc.common.service.IService;
import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.BlackListMapper;
import com.hgits.hotc.entity.news.BlackList;
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
@Service("newBlackListService")
public class BlackListServiceImpl implements IService<BlackList> {
    @Autowired
    protected BlackListMapper mapper;

    public Mapper<BlackList> getMapper() {
        return mapper;
    }

    @Override
    public List<BlackList> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public BlackList selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(BlackList entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<BlackList> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(BlackList entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(BlackList entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<BlackList> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<BlackList> value) {
        value.forEach(mapper::insert);
    }
}
