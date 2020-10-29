package com.dcseat.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 联盟报表接口
 * 组合报表的各项组件指标
 */
public interface Alliance {
    /*
    输出组件标题到excel文件
     */

    /**
     * 输出组件标题到excel文件
     * @param sheet     文件的sheet页
     * @param row       本模块输入的起点行
     * @param col       本模块输入的起点列
     */
    void printExcelTitle(Sheet sheet, int row, int col);

    /**
     * 输出组件数据到excel文件
     * @param sheet     文件的sheet页
     * @param row       本模块输入的起点行
     * @param col       本模块输入的起点列
     */
    void printExcelValue(Sheet sheet, int row, int col);

    /*
    清空组件自身数据
     */
    void clear();

    /*
    初始化组件数据
     */
    void initData();
}
