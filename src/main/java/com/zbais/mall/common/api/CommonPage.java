package com.zbais.mall.common.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 分页数据封装类
 * @Author: Zbais
 * @Date: 2021/12/07/13:06
 * @Description:
 */
@Data
public class CommonPage<T> {
    // 页码
    private Integer pageNum;
    //每页记录数
    private Integer pageSize;
    //总页数
    private Integer pageTotal;
    //总记录数
    private  long total;
    //记录集合
    private List<T> list;

    /**
     * 将Mybatis Plus 分页结果转化为通用结果
     */
    public static <T> CommonPage<T> restPage(Page<T> pageResult){
        CommonPage<T> result = new CommonPage<>();
        result.setPageNum(Convert.toInt(pageResult.getCurrent()));
        result.setPageSize(Convert.toInt(pageResult.getSize()));
        result.setPageTotal(Convert.toInt(pageResult.getTotal()/pageResult.getSize()));
        result.setTotal(Convert.toLong(pageResult.getTotal()));
        result.setList(pageResult.getRecords());
        return result;
    }
}
