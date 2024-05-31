package com.iuroc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.iuroc.model.Emp;

@Mapper
public interface EmpMapper {
    /** 初始化员工表 */
    void init();

    int insert(Emp emp);

    void deleteById(Integer id);

    void update(Emp emp);

    List<Emp> search(String nameKeyword, Integer gender, Integer job, String entryTimeStart, String entryTimeEnd,
            Integer deptId, Integer limit, Integer offset);
}