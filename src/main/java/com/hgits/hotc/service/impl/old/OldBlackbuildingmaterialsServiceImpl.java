package com.hgits.hotc.service.impl.old;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.old.OldBlackbuildingmaterialsMapper;
import com.hgits.hotc.entity.old.OldBlackbuildingmaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hgits.hotc.common.service.IService;
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
public class OldBlackbuildingmaterialsServiceImpl implements IService<OldBlackbuildingmaterials> {

    @Autowired
    protected OldBlackbuildingmaterialsMapper mapper;

    public Mapper<OldBlackbuildingmaterials> getMapper() {
        return mapper;
    }

    @Override
    public List<OldBlackbuildingmaterials> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public OldBlackbuildingmaterials selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(OldBlackbuildingmaterials entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<OldBlackbuildingmaterials> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(OldBlackbuildingmaterials entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(OldBlackbuildingmaterials entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<OldBlackbuildingmaterials> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OldBlackbuildingmaterials> value) {
        value.forEach(mapper::insert);
    }
}
