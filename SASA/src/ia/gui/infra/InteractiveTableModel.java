package ia.gui.infra;

import ia.infra.negocio.horario.Atividade;

import java.text.ParseException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class InteractiveTableModel extends AbstractTableModel {
    public static final int INDEX_REMOVER = 1;
    public static final int INDEX_TIPO    = 2;
    public static final int INDEX_INICIO  = 3;
    public static final int INDEX_TERMINO = 4;
    public static final int INDEX_NOME    = 5;
    public static final int INDEX_HIDDEN  = 0;

    protected String[] columnNames;
    protected Vector<Atividade> dataVector;

    public InteractiveTableModel(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector<Atividade>();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        if (column == INDEX_HIDDEN) 
        	return false;
        else 
        	return true;
    }

    public Class getColumnClass(int column) {
        switch (column) {
	        case INDEX_REMOVER:
	        case INDEX_TIPO:
	        case INDEX_INICIO:
	        case INDEX_TERMINO:
	        case INDEX_NOME:
	        case INDEX_HIDDEN:
	           return String.class;
	        default:
	           return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        Atividade record = dataVector.get(row);
        switch (column) {
    		case INDEX_REMOVER:
    			return new Boolean(false);
        	case INDEX_TIPO:
        		return record.getClass().getCanonicalName().replaceAll("(.)*\\.", "");
            case INDEX_INICIO:
               return record.getInicio().getHours()+":"+record.getInicio().getMinutes();
            case INDEX_TERMINO:
               return record.getTermino().getHours()+":"+record.getTermino().getMinutes();
            case INDEX_NOME:
               return record.getNome();
            default:
               return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
    	Atividade record = dataVector.get(row);
    	
        switch (column) {
			case INDEX_REMOVER:
				
				break;
	    	case INDEX_TIPO:
	    		
	            break;
	        case INDEX_INICIO:
				try {
					record.setInicio((String)value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
	        case INDEX_TERMINO:
				try {
					record.setTermino((String)value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
	        case INDEX_NOME:
				record.setNome((String)value);
				break;
            default:
            	Erro.print("invalid index", "invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    //TODO WTF???
    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) 
        	return false;
        
        Atividade record = dataVector.get(dataVector.size() - 1);
        if ( record.getNome().trim().equals("") && record.getInicio().getHours() <= 0 && record.getTermino().getHours() <= 0 ) {
        	return true;
        } else {
        	return false;
        }
    }

    public void addEmptyRow() {
        dataVector.add(new Atividade());
        fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }

}
