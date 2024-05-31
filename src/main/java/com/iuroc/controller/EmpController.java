package com.iuroc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuroc.mapper.EmpMapper;
import com.iuroc.model.Emp;
import com.iuroc.util.MakeRes;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    EmpMapper empMapper;

    @PostMapping("/insert")
    public MakeRes<Emp> insert(@RequestBody Emp emp) throws Exception {
        if (emp.getUsername() == null)
            throw new MissingServletRequestParameterException("username", "String");
        if (emp.getPassword() == null)
            throw new MissingServletRequestParameterException("password", "String");
        if (emp.getName() == null)
            throw new MissingServletRequestParameterException("name", "String");
        if (emp.getGender() == null)
            throw new MissingServletRequestParameterException("gender", "int");
        if (emp.getImage() == null)
            throw new MissingServletRequestParameterException("image", "String");
        if (emp.getJob() == null)
            throw new MissingServletRequestParameterException("job", "int");
        if (emp.getEntryTime() == null)
            throw new MissingServletRequestParameterException("entryTime", "String");
        if (emp.getDeptId() == null)
            throw new MissingServletRequestParameterException("deptId", "int");
        emp.setCreateTime(new Date());
        emp.setUpdateTime(new Date());
        try {
            empMapper.insert(emp);
            emp.setPassword(null);
            return new MakeRes<Emp>("添加员工成功").data(emp);
        } catch (DuplicateKeyException e) {
            return new MakeRes<Emp>(false, "用户名被被使用，请换一个重试");
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("foreign key constraint"))
                throw new Exception("deptId 不存在");
            return new MakeRes<Emp>(false, "不符合数据约束，如数据长度过长");
        }
    }

    @PostMapping("/delete")
    public MakeRes<String> delete(@RequestParam Integer id) {
        empMapper.deleteById(id);
        return new MakeRes<>("删除成功");
    }

    @PostMapping("/update")
    public MakeRes<String> update(@RequestBody Emp emp) throws Exception {
        if (emp.getId() == null)
            throw new MissingServletRequestParameterException("id", "int");
        if (emp.getUsername() == null
                && emp.getPassword() == null
                && emp.getName() == null
                && emp.getGender() == null
                && emp.getImage() == null
                && emp.getJob() == null
                && emp.getEntryTime() == null
                && emp.getDeptId() == null
                && emp.getUpdateTime() == null)
            throw new Exception("请至少包含一项需要更新的字段");
        empMapper.update(emp);
        return new MakeRes<>("更新成功");
    }

    @PostMapping("/search")
    public MakeRes<List<Emp>> search(
            @RequestParam String nameKeyword,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) Integer job,
            @RequestParam(required = false) String entryTimeStart,
            @RequestParam(required = false) String entryTimeEnd,
            @RequestParam(required = false) Integer deptId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "72") Integer pageSize) {
        return new MakeRes<List<Emp>>()
                .data(empMapper.search(nameKeyword, gender, job, entryTimeStart, entryTimeEnd, deptId, pageSize,
                        page * pageSize));
    }
}
