package ui.tableheader;

import java.util.Iterator;

import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class GroupableTableHeader extends JTableHeader {
    @SuppressWarnings("unused")
	private static final String uiClassID = "GroupableTableHeaderUI";
    //-------------------------------------------------------------------
    public GroupableTableHeader(GroupableTableColumnModel model) {
        super(model);
        setUI(new GroupableTableHeaderUI());
        setReorderingAllowed(false);
    }
    
    @SuppressWarnings("rawtypes")
	public void setColumnMargin() {
        int columnMargin = getColumnModel().getColumnMargin();
        Iterator iter = ((GroupableTableColumnModel)columnModel).columnGroupIterator();
        while (iter.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup)iter.next();
            cGroup.setColumnMargin(columnMargin);
        }
    }
}
