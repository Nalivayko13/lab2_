package bsu.rfe.group10.lab3.Nalivayko.varB5;

import javax.swing.table.AbstractTableModel;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }

    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        if (col == 0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        }
        if (col == 1) {
// Если запрашивается значение 2-го столбца, то это значение
// многочлена
            Double result = 0.0;
// Вычисление значения в точке по схеме Горнера.
            for (int i = 0; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        } else {
            double result1 = 0;
            for (int i = 0; i < coefficients.length; i++) {
                result1 =  result1 * x + coefficients[i];
            }
            Integer integer = (int) result1;
            BigInteger bigInteger = BigInteger.valueOf(integer);
            Boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
          return (probablePrime);
        }

    }


    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";

            case 1:
                return "Значение многочлена";
            default:
// Название 2-го столбца
                return "значение простое";
        }
    }

    public Class<?> getColumnClass(int col) {
        if(col==0) return Double.class;
        if(col==1) return Double.class;
        else return Boolean.class;
    }
}
