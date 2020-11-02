package com.hgits.hotc.service.impl.old;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.old.OldPrintMapper;
import com.hgits.hotc.entity.old.OldPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hgits.hotc.common.service.IService;
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
public class OldPrintServiceImpl implements IService<OldPrint> {

    @Autowired
    protected OldPrintMapper mapper;

    public Mapper<OldPrint> getMapper() {
        return mapper;
    }

    @Override
    public List<OldPrint> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public OldPrint selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(OldPrint entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<OldPrint> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(OldPrint entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(OldPrint entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<OldPrint> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OldPrint> value) {
        value.forEach(mapper::insert);
    }
}
