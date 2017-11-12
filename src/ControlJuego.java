import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion = 0;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		puntuacion = 0;
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		int contadorMinas = 0;
		while(contadorMinas < MINAS_INICIALES){
			Random rd = new Random();
			int ale1 = rd.nextInt(10);
			int ale2 = rd.nextInt(10);
			if (tablero[ale1][ale2] != MINA) {
				tablero[ale1][ale2] = MINA;
				contadorMinas++;
			}
		}
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
		
	}
	
	/**Cálculo de las minas adjuntas:
	 * Para calcular el número de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdrán LADO_TABLERO-1.
	 * Por lo tanto, como mucho la i y la j valdrán como poco 0.
	 * @param i: posición verticalmente de la casilla a rellenar
	 * @param j: posición horizontalmente de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
		int minas = 0;
		
		//Caso 1, Esquina superior izq.
			if((i == 0)&&(j == 0)) {
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
				if (tablero[i+1][j+1] == MINA) {
					minas++;
				}
			}
			
			//Caso 2, Esquina superior derecha.
			if ((i == 0)&&(j == LADO_TABLERO-1)) {
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
				if (tablero[i+1][j-1] == MINA) {
					minas++;
				}
			}
			
			//Caso 3, Esquina inferior izq.
			if ((i == LADO_TABLERO-1)&&(j == 0)) {
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				if (tablero[i-1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
			}
			
			//Caso 4, Esquina inferior derecha
			if ((i == LADO_TABLERO-1)&&(j == LADO_TABLERO-1)) {
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				if (tablero[i-1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
			}
			
			//Caso5, Linea izquierda
			if ((j == 0)&&(i > 0)&&(i < LADO_TABLERO-1)) {
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				
				if (tablero[i-1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
			}
			//Caso 6, Linea inferior;
			if ((i == LADO_TABLERO-1)&&(j > 0)&&(j < LADO_TABLERO-1)) {
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
				if (tablero[i-1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				if (tablero[i-1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
			}
			//Caso 7, Linea derecha
			if ((j == LADO_TABLERO-1)&&(i > 0)&&(i < LADO_TABLERO-1)) {
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				if (tablero[i-1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
			}
			//Caso 8, Linea superior
			if ((i == 0)&&(j > 0)&&(j < LADO_TABLERO-1)) {
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
				if (tablero[i+1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
			}
			//Caso 9, caso normal
			if ((i > 0)&&(i < LADO_TABLERO-1)&&(j > 0)&&(j < LADO_TABLERO-1)) {
				if (tablero[i-1][j] == MINA) {
					minas++;
				}
				if (tablero[i-1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j-1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j+1] == MINA) {
					minas++;
				}
				if (tablero[i+1][j] == MINA) {
					minas++;
				}
				if (tablero[i][j+1] == MINA) {
					minas++;
				}
				if (tablero[i-1][j+1] == MINA) {
					minas++;
				}
			}
			
		return minas;
	}
	
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el GestorJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
			if (tablero[i][j] != -1) {
				puntuacion++;
				return true;
			}else {
				return false;
			}
		
	}
	
	
	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		if (getPuntuacion() == (LADO_TABLERO*LADO_TABLERO)-MINAS_INICIALES) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: "+puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
