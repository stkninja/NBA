package ui.tableheader;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ColumnGroup {
	protected TableCellRenderer renderer;
	@SuppressWarnings("rawtypes")
	protected Vector v;
	protected String text;
    protected int margin=0;
    //--------------------------------------------------------------------------
    public ColumnGroup(String text) {
        this(null,text);
    }
    
    @SuppressWarnings({ "serial", "rawtypes" })
	public ColumnGroup(TableCellRenderer renderer,String text) {
        if (renderer == null) {
            this.renderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JTableHeader header = table.getTableHeader();
                    if (header != null) {
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());
                    }
                    setHorizontalAlignment(JLabel.CENTER);
                    setText((value == null) ? "" : value.toString());
                    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                    return this;
                }
            };
        } else {
            this.renderer = renderer;
        }
        this.text = text;
        v = new Vector();
    }
    //------------------------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
	public void add(Object obj) {
        if (obj == null) {
        	return;
        }
        v.addElement(obj);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getColumnGroups(TableColumn c, Vector g) {
        g.addElement(this);
        if (v.contains(c)) return g;
        Iterator iter = v.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof ColumnGroup) {
                Vector groups =
                (Vector)((ColumnGroup)obj).getColumnGroups(c,(Vector)g.clone());
                if (groups != null) return groups;
            }
        }
        return null;
    }
    
    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }
    
    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }
    
    public Object getHeaderValue() {
        return text;
    }
    
    @SuppressWarnings("rawtypes")
	public Dimension getSize(JTable table) {
        Component comp = renderer.getTableCellRendererComponent(
        table, getHeaderValue(), false, false,-1, -1);
        int height = comp.getPreferredSize().height;
        int width  = 0;
        Iterator iter = v.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn)obj;
                width += aColumn.getWidth();
            } else {
                width += ((ColumnGroup)obj).getSize(table).width;
            }
        }
        return new Dimension(width, height);
    }
    
    @SuppressWarnings("rawtypes")
	public void setColumnMargin(int margin) {
        this.margin = margin;
        Iterator iter = v.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof ColumnGroup) {
                ((ColumnGroup)obj).setColumnMargin(margin);
            }
        }
    }
}
