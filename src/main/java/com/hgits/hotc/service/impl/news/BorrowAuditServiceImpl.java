package com.hgits.hotc.service.impl.news;

import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.news.BorrowAuditMapper;
import com.hgits.hotc.entity.news.BorrowAudit;
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
public class BorrowAuditServiceImpl implements IService<BorrowAudit> {
    @Autowired
    protected BorrowAuditMapper mapper;

    public Mapper<BorrowAudit> getMapper() {
        return mapper;
    }

    @Override
    public List<BorrowAudit> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public BorrowAudit selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(BorrowAudit entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<BorrowAudit> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(BorrowAudit entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(BorrowAudit entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<BorrowAudit> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<BorrowAudit> value) {
        value.forEach(mapper::insert);
    }
}
