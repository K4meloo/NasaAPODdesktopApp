import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class App {

    private int rowHeight = 250;

    public App() throws IOException {
        JFrame frame = new JFrame("App");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Title", "Date", "Explanation", "URL"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 3) {
                    return ImageIcon.class;
                }
                return JTextField.class;
            }
        };
        Object[] columnsName = new Object[4];
        columnsName[0] = "Title";
        columnsName[1] = "Explanation";
        columnsName[2] = "Date";
        columnsName[3] = "URL";
        tableModel.setColumnIdentifiers(columnsName);
        table.setRowHeight(rowHeight);

        Controller controller = new Controller();

        for (ImageData imageData : controller.getImageDataList()) {
            Object[] o = new Object[4];
            o[0] = imageData.getTitle();
            o[1] = imageData.getExplanation();
            o[2] = imageData.getDate();
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(imageData.getUrl())).getScaledInstance(rowHeight, rowHeight, Image.SCALE_SMOOTH));
            o[3] = imageIcon;
            tableModel.addRow(o);
        }

        table.setModel(tableModel);

        table.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setText(value.toString());
                return textArea;
            }
        });

        JScrollPane pane = new JScrollPane(table);
        frame.add(pane);


        frame.setVisible(true);

    }
}