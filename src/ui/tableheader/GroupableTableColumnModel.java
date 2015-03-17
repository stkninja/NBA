package ui.tableheader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class GroupableTableColumnModel extends DefaultTableColumnModel {
    @SuppressWarnings("rawtypes")
	protected ArrayList columnGroups = new ArrayList();
    //-----------------------------------------------------------
    @SuppressWarnings("unchecked")
	public void addColumnGroup(ColumnGroup columnGroup) {
        columnGroups.add(columnGroup);
    }
    
    @SuppressWarnings("rawtypes")
	public Iterator columnGroupIterator() {
        return columnGroups.iterator();
    }
    
    public ColumnGroup getColumnGroup(int index) {
        if(index >= 0 && index < columnGroups.size()) {
            return (ColumnGroup)columnGroups.get(index);
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
	public Iterator getColumnGroups(TableColumn col) {
        if (columnGroups.isEmpty()) return null;
        Iterator iter = columnGroups.iterator();
        while (iter.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup)iter.next();
            Vector v_ret = (Vector)cGroup.getColumnGroups(col,new Vector());
            if (v_ret != null) {
                return v_ret.iterator();
            }
        }
        return null;
    }
}