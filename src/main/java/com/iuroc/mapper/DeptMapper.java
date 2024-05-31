package com.iuroc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iuroc.model.Dept;

@Mapper
public interface DeptMapper {
    /** 初始化部门表 */
    void init();

    int insert(Dept dept);

    void deleteById(Integer id);

    void update(Dept dept);

    List<Dept> search(String keyword, Integer limit, Integer offset);
}
