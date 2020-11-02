package com.hgits.hotc.service.impl.news;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.SysRoleMapper;
import com.hgits.hotc.entity.news.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import com.hgits.hotc.common.service.IService;
/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Service
public class SysRoleServiceImpl implements IService<SysRole> {
    @Autowired
    protected SysRoleMapper mapper;

    public Mapper<SysRole> getMapper() {
        return mapper;
    }

    @Override
    public List<SysRole> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public SysRole selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(SysRole entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<SysRole> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(SysRole entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(SysRole entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<SysRole> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<SysRole> value) {
        value.forEach(mapper::insert);
    }
}
