package com.hgits.hotc.service.impl.news;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.BorrowRequisitionRelateMapper;
import com.hgits.hotc.entity.news.BorrowRequisitionRelate;
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
public class BorrowRequisitionRelateServiceImpl implements IService<BorrowRequisitionRelate> {
    @Autowired
    protected BorrowRequisitionRelateMapper mapper;

    public Mapper<BorrowRequisitionRelate> getMapper() {
        return mapper;
    }

    @Override
    public List<BorrowRequisitionRelate> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public BorrowRequisitionRelate selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(BorrowRequisitionRelate entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<BorrowRequisitionRelate> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(BorrowRequisitionRelate entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(BorrowRequisitionRelate entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<BorrowRequisitionRelate> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<BorrowRequisitionRelate> value) {
        value.forEach(mapper::insert);
    }
}
