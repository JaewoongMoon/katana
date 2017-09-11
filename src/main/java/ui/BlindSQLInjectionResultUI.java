/**
 * @ BlindSQLInjectionResultUI.java
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.SwingUtils;

/**
 * <pre>
 * ui
 * BlindSQLInjectionResultUI.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017/09/04
 */
public class BlindSQLInjectionResultUI extends JPanel{

	private JTabbedPane tabs = null;
	private DBResultUI dbUI = null;
	private TableResultUI tableUI = null;
	private ColumnResultUI columnUI = null;
	private DataResultUI dataUI = null; 
	final int WIDTH = 600;
	final int HEIGHT = 700;
	
	public BlindSQLInjectionResultUI(){
		// panel setup
		setLayout(new BorderLayout());
		setSize(WIDTH, HEIGHT); 
		setVisible(true);
		
		tabs = new JTabbedPane();
		add(tabs);
		
		dbUI = new DBResultUI();
		tableUI = new TableResultUI();
		columnUI = new ColumnResultUI();
		dataUI = new DataResultUI();
		
		tabs.add("DB", dbUI);
		tabs.add("Table", tableUI);
		tabs.add("Column", columnUI);
		tabs.add("Data", dataUI);
	}
	
	public DBResultUI getDBResultUI(){
		return dbUI;
	}
	
	public TableResultUI getTableResultUI(){
		return tableUI;
	}

	public void clearResults(){
		// clear db 
		dbUI.clearResult();
		// clear table 
		// clear column 
		// clear data
	}
	
	/* sub tabs */
	public class DBResultUI extends JPanel{
		String[] title = {"순번", "DB명 길이", "DB명", "테이블수"};
		Vector<String> headers = new Vector<String>(Arrays.asList(title));
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		JTable table;
		JScrollPane scroll;
		Vector<String> testRow = new Vector<String>();
		
		private void testRowDo(){
			testRow.add("1");
			testRow.add("9");
			testRow.add("STONESOUP");
			testRow.add("2");
			data.add(testRow);
		}
		public DBResultUI(){
			setLayout(new BorderLayout());
			table = new JTable(data, headers);
			scroll = new JScrollPane(table);
			add(scroll);
		}
		
		public void clearResult(){
			System.out.println("clear db result ...");
			DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
			tableModel.getDataVector().removeAllElements();
			tableModel.fireTableDataChanged();
		}
		
		public DefaultTableModel getTableModel(){
			return (DefaultTableModel)table.getModel();
		}
	}
	
	class TableResultUI extends JPanel{
		String[] title = {"순번", "DB명", "테이블명 길이", "테이블명", "칼럼 수"};
		Vector<String> headers = new Vector<String>(Arrays.asList(title));
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		JTable table;
		JScrollPane scroll;
		
		public TableResultUI(){
			setLayout(new BorderLayout());
			table = new JTable(data, headers);
			scroll = new JScrollPane(table);
			add(scroll);
		}
	}
	
	class ColumnResultUI extends JPanel{
		String[] title = {"순번", "DB명", "테이블명", "칼럼명 길이", "칼럼명", "데이터 수"};
		Vector<String> headers = new Vector<String>(Arrays.asList(title));
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		JTable table;
		JScrollPane scroll;
		
		
		public ColumnResultUI(){
			setLayout(new BorderLayout());
			table = new JTable(data, headers);
			scroll = new JScrollPane(table);
			add(scroll);
		}
	}

	class DataResultUI extends JPanel{
		String[] title = {"순번", "DB명", "테이블명", "칼럼명", "데이터 길이", "데이터 내용"};
		Vector<String> headers = new Vector<String>(Arrays.asList(title));
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		JTable table;
		JScrollPane scroll;
		
		public DataResultUI(){
			setLayout(new BorderLayout());
			table = new JTable(data, headers);
			scroll = new JScrollPane(table);
			add(scroll);
		}
	}

}
