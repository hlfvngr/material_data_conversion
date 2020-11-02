package com.hgits.hotc.service.impl.old;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.old.OldUserRoleMapper;
import com.hgits.hotc.entity.old.OldUserRole;
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
public class OldUserRoleServiceImpl implements IService<OldUserRole> {

    @Autowired
    protected OldUserRoleMapper mapper;

    public Mapper<OldUserRole> getMapper() {
        return mapper;
    }

    @Override
    public List<OldUserRole> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public OldUserRole selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(OldUserRole entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<OldUserRole> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(OldUserRole entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(OldUserRole entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<OldUserRole> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OldUserRole> value) {
        value.forEach(mapper::insert);
    }
}
