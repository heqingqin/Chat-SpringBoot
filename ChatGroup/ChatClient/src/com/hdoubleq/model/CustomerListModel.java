package com.hdoubleq.model;

import com.hdoubleq.bean.list.FriendList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:37
 */
public class CustomerListModel extends AbstractTableModel {
    //初始化
    Vector rowData, columnNames;
    JTable jt = null;

    JScrollPane jsp = null;

    private FriendList list;


    public CustomerListModel() {
        columnNames = new Vector();
        //设置列名
        columnNames.add("成员列表("+list.getList().size()+")");

        rowData = new Vector();

        for (int i = 0; i < list.getList().size(); i++) {
            Vector hang=new Vector();
//				String num=i<10 ? "0"+(i+1) : (i+1)+"";

            hang.add(list.getList().get(i).getAlias()+"("+list.getList().get(i).getAcc()+")");
            rowData.add(hang);
        }
    }
    //设置列

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int column) {
        // TODO Auto-generated method stub
        return ((Vector)this.rowData.get(rowIndex)).get(column);
    }


    @Override
    public String getColumnName(int arg0) {
        // TODO Auto-generated method stub
        return (String) this.columnNames.get(arg0);
    }
}
