package ua.nure.ponomarenko.task2;

public class Matrix {
	// Demo
	public static void main(String[] args) {
		Matrix matrix1 = new Matrix(new double[][] {
			{33,34,12},
            {33,19,10},
            {12,14,17},
            {84,24,51},
            {43,71,21}}
        );

        Matrix matrix2 = new Matrix(new double[][] {
			{1,6,12},
            {33,6,10},
            {1,4,8},
            {54,2,5},
            {2,4,5}}
        );

        Matrix matrix3 = new Matrix(new double[][] {
			{10,11,34,55},
            {33,45,17,81},
            {45,63,12,16}
        });

        double number = 2;

        System.out.println("Matrix1:");
        matrix1.print();
        System.out.println();
        System.out.println("Matrix2:");
        matrix2.print();
        System.out.println();
        System.out.println("Matrix3:");
        matrix3.print();
		System.out.println();
        
        System.out.println("Matrix1 add matrix2:");
        matrix1.add(matrix2);
		matrix1.print();
		System.out.println();
        
		System.out.println("Modified matrix1 multiply by " + number);
        matrix1.mul(number);
		matrix1.print();
		System.out.println();
        
		System.out.println("Modified matrix1 multiply by matrix3:");
        matrix1.mul(matrix3);
		matrix1.print();
		System.out.println();

		System.out.println("Transpose modified matrix1:");
        matrix1.transpose();
		matrix1.print();
	} 

	// Properties
	private double[][] array;
	private int rowsCount;
	private int columnsCount;

	// Getters
	public double[][] getArray() {
		return array;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public int getColumnsCount() {
		return columnsCount;
	}

	// Initialization
	public Matrix(double[][] array) {
		this.array = array;
		rowsCount = array.length;
		if (rowsCount > 0) {
			columnsCount = array[0].length; 
		}
      
	}

	// Public methods
	public void add(Matrix matrix) {
		if (this.rowsCount != matrix.getRowsCount() ||
			this.columnsCount != matrix.getColumnsCount()) {
			throw new IllegalArgumentException("Matrixes' sizes don't match");
		}
		for (int i = 0; i < matrix.getRowsCount(); i++) {
			for (int j = 0; j < matrix.getColumnsCount(); j++) {
				this.array[i][j] += matrix.array[i][j];
			}
		}
	}

	public void mul(double x) {
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				this.array[i][j] *= x;
			}
		}
	}

	public void mul(Matrix matrix) throws IllegalArgumentException {
		int aRowsCount = this.rowsCount;
        int aColumnsCount = this.columnsCount;
        int bRowsCount = matrix.getRowsCount();
        int bColumnsCount = matrix.getColumnsCount();

        if (aColumnsCount != bRowsCount) {
			throw new IllegalArgumentException("A:Rows: " + aColumnsCount +
				" did not match B:Columns " + bRowsCount + ".");
		}
      
        Matrix resultMatrix = new Matrix(new double[aRowsCount][bColumnsCount]);
        for (int i = 0; i < aRowsCount; i++) {
            for (int j = 0; j < bColumnsCount; j++) {
                for (int k = 0; k < aColumnsCount; k++) {
                    resultMatrix.array[i][j] += this.array[i][k] * matrix.array[k][j]; 
                }
            }
        }
        array = resultMatrix.array;
        rowsCount = aRowsCount;
        columnsCount = bColumnsCount;
	}
  
	public void transpose() {
		int m = this.getRowsCount();
        int n = this.getColumnsCount();
		Matrix resultMatrix = new Matrix(new double[n][m]);
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				resultMatrix.array[j][i] = this.array[i][j];
			}
		}
		array = resultMatrix.array;
        rowsCount = n;
        columnsCount = m;
	}

	public void print() {
		for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                System.out.printf("%8.1f ", array[i][j]);
            }
            System.out.println();
        }
	}
}