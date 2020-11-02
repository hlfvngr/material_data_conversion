package com.hgits.hotc.service.impl.news;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.BorrowMaterialMapper;
import com.hgits.hotc.entity.news.BorrowMaterial;
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
public class BorrowMaterialServiceImpl implements IService<BorrowMaterial> {
    @Autowired
    protected BorrowMaterialMapper mapper;

    public Mapper<BorrowMaterial> getMapper() {
        return mapper;
    }

    @Override
    public List<BorrowMaterial> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public BorrowMaterial selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(BorrowMaterial entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<BorrowMaterial> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(BorrowMaterial entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(BorrowMaterial entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<BorrowMaterial> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<BorrowMaterial> value) {
        value.forEach(mapper::insert);
    }
}
