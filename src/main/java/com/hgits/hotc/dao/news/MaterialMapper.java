package com.hgits.hotc.dao.news;
import com.hgits.hotc.common.mapper.MyMapper;
import com.hgits.hotc.entity.news.Material;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
public interface MaterialMapper extends MyMapper<Material> {

    @Select("select interfaceMaterialsValue from sys_51_materials_tranformation where materialsName = #{materialName}")
    String selectMaterialFivenoeNoByName(@Param("materialName") String materialName);

    @Select("select materialsUnit from sys_51_materials_unit where materialsName = #{materialName}")
    String selectMaterialUnitByName(@Param("materialName") String materialName);

    @Select("select materialsUnit from sys_51_materials_unit where materialsName like CONCAT(#{materialName},'%')")
    List<String> selectMaterialUnitByReverseName(String materialName);

    @Select("select material_name from tb_material where material_no = #{materialNo}")
    List<String> selectMaterialNameByMaterialNo(String materialNo);
}
