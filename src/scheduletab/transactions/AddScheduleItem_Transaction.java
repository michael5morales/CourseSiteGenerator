/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduletab.transactions;

import jtps.jTPS_Transaction;
import scheduletab.data.ScheduleItem;
import scheduletab.data.ScheduleTabData;

/**
 *
 * @author Michael
 */
public class AddScheduleItem_Transaction implements jTPS_Transaction{
    ScheduleTabData data;
    ScheduleItem item;
    
    public AddScheduleItem_Transaction(ScheduleTabData initData, ScheduleItem initItem) {
        data = initData;
        item = initItem;
    }

    @Override
    public void doTransaction() {
        data.addItem(item);
    }

    @Override
    public void undoTransaction() {
        data.removeItem(item);
    }
}
