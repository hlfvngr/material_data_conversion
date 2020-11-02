package com.hgits.hotc.service.impl.old;


import com.hgits.hotc.common.service.impl.BaseService;
import com.hgits.hotc.dao.old.OldClaimticketbranchMapper;
import com.hgits.hotc.entity.old.OldClaimticketbranch;
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
public class OldClaimticketbranchServiceImpl implements IService<OldClaimticketbranch> {

    @Autowired
    protected OldClaimticketbranchMapper mapper;

    public Mapper<OldClaimticketbranch> getMapper() {
        return mapper;
    }

    @Override
    public List<OldClaimticketbranch> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public OldClaimticketbranch selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(OldClaimticketbranch entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<OldClaimticketbranch> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(OldClaimticketbranch entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(OldClaimticketbranch entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<OldClaimticketbranch> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OldClaimticketbranch> value) {
        value.forEach(mapper::insert);
    }
}
