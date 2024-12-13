package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author bispo
 * @param <T>
 */
public class ControllerPesquisar<T> extends AbstractTableModel {

    private final Class<T> clazz;
    private List<T> lista;

    public ControllerPesquisar(Class<T> clazz) {
        this.clazz = clazz;
        this.lista = new ArrayList<>();
    }

    public void setList(List<T> lista) {
        this.lista = lista;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T getBean(int rowIndex) {
        return lista.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        if (clazz.getDeclaredFields().length < 4) {
            return clazz.getDeclaredFields().length;
        } else {
            return 4;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            /*System.out.println("");
             System.out.println("----------------------");
             System.out.println("");*/

            T bean = lista.get(rowIndex);
            //System.out.println("T bean: " + bean);

            String nomeAtributo = clazz.getDeclaredFields()[columnIndex].getName(); // busca todos os atributos do bean
            //System.out.println("clazz.getDeclaredFields:  "+Arrays.toString(clazz.getDeclaredFields()));
            //System.out.println("nome atributo: " + nomeAtributo);

            String metodoGet = "get" + nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1); // Constrói o nome do método getter.
            /*System.out.println("nomeAtributo.substring(0, 1).toUpperCase():  "+nomeAtributo.substring(0, 1).toUpperCase());
             System.out.println("nomeAtributo.substring(1):  "+nomeAtributo.substring(1));
             System.out.println("metodo get: " + metodoGet);*/

            Method get = clazz.getMethod(metodoGet);
            //System.out.println("get: " + get);
            //System.out.println("get invoke (bean): " + get.invoke(bean));

            return get.invoke(bean);
        } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            String nomeAtributoBruto = clazz.getDeclaredFields()[columnIndex].getName(); // pega o nome do atributo.
            String nomeAtributo = nomeAtributoBruto.substring(3);
            String nomeAtributoID = nomeAtributoBruto.substring(3, 5);

            if (nomeAtributoID.equals("Id")) {
                return nomeAtributoID.toUpperCase();
            } else {
                return nomeAtributo.toUpperCase();
            } // retorna os nome dos atributo como nome das colunas.
        } catch (Exception e) {
            return "COLUNA " + columnIndex;
        }
    }
}
