package com.hgits.hotc.service.impl.news;

import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.MaterialMapper;
import com.hgits.hotc.entity.news.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import com.hgits.hotc.common.service.IService;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Service
public class MaterialServiceImpl implements IService<Material> {
    @Autowired
    protected MaterialMapper mapper;

    public Mapper<Material> getMapper() {
        return mapper;
    }

    @Override
    public List<Material> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Material selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(Material entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<Material> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(Material entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(Material entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<Material> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<Material> value) {
        value.forEach(mapper::insert);
    }

    public String selectMaterialFivenoeNoByName(String materialName) {
        return mapper.selectMaterialFivenoeNoByName(materialName);
    }

    public String selectMaterialUnitByName(String materialsName) {
        return mapper.selectMaterialUnitByName(materialsName);
    }

    public List<String> selectMaterialUnitByReverseName(String materialName) {
        return mapper.selectMaterialUnitByReverseName(materialName);
    }

    public List<String> selectMaterialNameByMaterialNo(String materialNo) {
        return mapper.selectMaterialNameByMaterialNo(materialNo);
    }
}
