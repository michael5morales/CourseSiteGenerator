/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officehourstab.transactions;

import jtps.jTPS_Transaction;
import officehourstab.data.OfficeHoursData;
import officehourstab.data.TeachingAssistantPrototype;

/**
 *
 * @author Michael
 */
public class AddTA_Transaction implements jTPS_Transaction{
    OfficeHoursData data;
    TeachingAssistantPrototype ta;
    
    public AddTA_Transaction(OfficeHoursData initData, TeachingAssistantPrototype initTA) {
        data = initData;
        ta = initTA;
    }

    @Override
    public void doTransaction() {
        data.addTA(ta);        
    }

    @Override
    public void undoTransaction() {
        data.removeTA(ta);
    }
}
