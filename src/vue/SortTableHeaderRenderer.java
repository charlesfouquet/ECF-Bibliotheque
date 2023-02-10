package vue;
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
 
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
/**
 * A simple renderer class for JTable component.
 * @author www.codejava.net
 *
 */
public class SortTableHeaderRenderer extends JLabel implements TableCellRenderer {
 
    private static final long serialVersionUID = 3109748103319141458L;

	public SortTableHeaderRenderer() {
        setFont(new Font("Noto Serif", Font.BOLD, 13));
        setBackground(new Color(199, 152, 50));
        setOpaque(!isOpaque());
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setBorder(BorderFactory.createEtchedBorder());
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
 
}