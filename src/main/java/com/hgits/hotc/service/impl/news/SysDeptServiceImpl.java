package com.hgits.hotc.service.impl.news;


import com.hgits.hotc.common.service.IService;
import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.SysDeptMapper;
import com.hgits.hotc.entity.news.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Service
public class SysDeptServiceImpl implements IService<SysDept> {

    @Autowired
    protected SysDeptMapper mapper;

    public Mapper<SysDept> getMapper() {
        return mapper;
    }

    @Override
    public List<SysDept> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public SysDept selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(SysDept entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<SysDept> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(SysDept entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(SysDept entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<SysDept> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<SysDept> value) {
        value.forEach(mapper::insert);
    }

    public void echo() {
        System.out.println(this.getClass());
    }
}
