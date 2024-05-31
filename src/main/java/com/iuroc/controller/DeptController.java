package com.iuroc.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DataIntegrityViolationException;
import com.iuroc.mapper.DeptMapper;
import com.iuroc.model.Dept;
import com.iuroc.util.MakeRes;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptMapper deptMapper;

    @PostMapping("/insert")
    public Object insert(@RequestParam String name) {
        try {
            Dept dept = new Dept();
            dept.setName(name);
            dept.setCreateTime(new Date());
            dept.setUpdateTime(new Date());
            deptMapper.insert(dept);
            return new MakeRes<Dept>(true, "增加部门成功", dept);
        } catch (DuplicateKeyException e) {
            return new MakeRes<>(false, "部门名称已经存在，请换一个重试");
        } catch (DataIntegrityViolationException e) {
            return new MakeRes<>(false, "部门名称过长，name.length <= 50");
        }
    }

    @PostMapping("/delete")
    public MakeRes<String> delete(@RequestParam int id) {
        deptMapper.deleteById(id);
        return new MakeRes<>("删除部门成功");
    }

    @PostMapping("/update")
    public MakeRes<String> update(@RequestParam String name, @RequestParam int id) {
        Dept dept = new Dept();
        dept.setName(name);
        dept.setId(id);
        dept.setUpdateTime(new Date());
        try {
            deptMapper.update(dept);
        } catch (DuplicateKeyException e) {
            return new MakeRes<>(false, "部门名称已经存在，请换一个重试");
        } catch (DataIntegrityViolationException e) {
            return new MakeRes<>(false, "部门名称过长，name.length <= 50");
        }
        return new MakeRes<>("更新部门成功");
    }

    @PostMapping("/search")
    public MakeRes<List<Dept>> search(@RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "72") Integer pageSize) {
        return new MakeRes<List<Dept>>().data(deptMapper.search(keyword, pageSize, page * pageSize));
    }
}