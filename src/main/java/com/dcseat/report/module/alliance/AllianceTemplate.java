package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

public class AllianceTemplate implements Alliance {

    private ArrayList<Alliance> module = new ArrayList<>();


    public void add(Alliance a) {
        module.add(a);
    }

    public void remove(Alliance a) {
        module.remove(a);
    }


    @Override
    public void printExcelTitle(Sheet sheet, int row, int col) {

    }

    @Override
    public void printExcelValue(Sheet sheet, int row, int col) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void initData() {

    }

}
