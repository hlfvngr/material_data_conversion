package com.hgits.hotc.service.impl.old;


import com.hgits.hotc.common.service.IService;
import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.old.OldUnitMapper;
import com.hgits.hotc.entity.old.OldUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 'material _cloud_pro.report' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Service
public class OldUnitServiceImpl implements IService<OldUnit> {


    @Autowired
    protected OldUnitMapper mapper;

    public Mapper<OldUnit> getMapper() {
        return mapper;
    }

    @Override
    public List<OldUnit> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public OldUnit selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(OldUnit entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<OldUnit> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(OldUnit entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(OldUnit entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<OldUnit> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OldUnit> value) {
        value.forEach(mapper::insert);
    }

    public void echo() {
        System.out.println(this.getClass());
    }
}
