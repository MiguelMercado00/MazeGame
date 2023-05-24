import java.util.Scanner;
import java.util.Random;

public class MazeGame {


    public static void imprimirMatriz(char[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("'" + matriz[i][j] + "'");
                if (j != matriz[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        Monstruo[] monstruos = new Monstruo[4];
        int filaAccion = 0;
        int columnaAccion = 0;
        int fila = 0;
        int columna = 0;
        char[][] matriz = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
        int intentosRestantes = 20;
        System.out.println(" __       __                                       ______                                    \n" +
                "/  \\     /  |                                     /      \\                                   \n" +
                "$$  \\   /$$ |  ______   ________   ______        /$$$$$$  |  ______   _____  ____    ______  \n" +
                "$$$  \\ /$$$ | /      \\ /        | /      \\       $$ | _$$/  /      \\ /     \\/    \\  /      \\ \n" +
                "$$$$  /$$$$ | $$$$$$  |$$$$$$$$/ /$$$$$$  |      $$ |/    | $$$$$$  |$$$$$$ $$$$  |/$$$$$$  |\n" +
                "$$ $$ $$/$$ | /    $$ |  /  $$/  $$    $$ |      $$ |$$$$ | /    $$ |$$ | $$ | $$ |$$    $$ |\n" +
                "$$ |$$$/ $$ |/$$$$$$$ | /$$$$/__ $$$$$$$$/       $$ \\__$$ |/$$$$$$$ |$$ | $$ | $$ |$$$$$$$$/ \n" +
                "$$ | $/  $$ |$$    $$ |/$$      |$$       |      $$    $$/ $$    $$ |$$ | $$ | $$ |$$       |\n" +
                "$$/      $$/  $$$$$$$/ $$$$$$$$/  $$$$$$$/        $$$$$$/   $$$$$$$/ $$/  $$/  $$/  $$$$$$$/ \n" +
                "                                                                                             ");

        System.out.println("Estado Inicial");
        imprimirMatriz(matriz);
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("¿Quieres jugar el juego? (si/no): ");
            String respuesta = input.nextLine();

            if (respuesta.equalsIgnoreCase("no")) {
                System.out.println("¡Adiós!");
                return;
            } else if (respuesta.equalsIgnoreCase("si")) {
                System.out.println("El juego ha comenzado");
                System.out.println("¿Deseas crear monstruos? (si/no): ");
                String respuestaMonstruos = input.nextLine();
                if (respuestaMonstruos.equalsIgnoreCase("no")) {
                    System.out.println("¡Adiós!");
                    return;
                } else if (respuestaMonstruos.equalsIgnoreCase("si")) {
                    System.out.println("Monstruos Creados");
                    GenerarMonstruos(matriz, monstruos, filaAccion, columnaAccion);
                    imprimirMatriz(matriz);
                } else if (!respuestaMonstruos.equalsIgnoreCase("si")) {
                    System.out.println("Respuesta no válida. ¡Adiós!");
                    return;
                }

            } else if (!respuesta.equalsIgnoreCase("si")) {
                System.out.println("Respuesta no válida. ¡Adiós!");
                return;
            }

            Monstruo monstruoTrebol = monstruos[0];
            Monstruo monstruoDiamante = monstruos[1];
            Monstruo monstruoPica = monstruos[2];
            Monstruo monstruoCorazon = monstruos[3];


            for (intentosRestantes = 20; intentosRestantes > 0; intentosRestantes--) {
                System.out.println("Movimientos restantes: " + intentosRestantes);
                System.out.println("Vida de los monstruos: ");
                if (monstruoTrebol.getVida() > 0) {
                    System.out.println("Trebol: " + monstruoTrebol.getVida());
                }
                if (monstruoDiamante.getVida() > 0) {
                    System.out.println("Diamante: " + monstruoDiamante.getVida());
                }
                if (monstruoPica.getVida() > 0) {
                    System.out.println("Pica: " + monstruoPica.getVida());
                }
                if (monstruoCorazon.getVida() > 0) {
                    System.out.println("Corazon: " + monstruoCorazon.getVida() + "\n");
                }
                System.out.println("¿Qué deseas hacer?");
                System.out.println("(i) Disparar con cauchera (quita 5 vida)");
                System.out.println("(ii) Pegar con palo de clavos (quita 10 de vida)");
                System.out.println("(iii) Lanzar molotov (quita 20 de vida)");
                System.out.println("(iv) Montar bloqueo (restringe el movimiento de los monstruos)");
                System.out.println("(v) Tirar bomba (quita 30 de vida por monstruo)");
                System.out.println("(vi) Salir del juego");
                filaAccion = 0;
                columnaAccion = 0;

                String respuestaMovimiento = input.nextLine();
                if (respuestaMovimiento.equalsIgnoreCase("i")) {
                    System.out.println("A que cordenadas deseas disparar? (x,y):");
                    String respuestaCordenadas = input.nextLine();
                    String[] cordenadas = respuestaCordenadas.split(",");
                    int x = Integer.parseInt(cordenadas[1]);
                    int y = Integer.parseInt(cordenadas[0]);
                    fila = x;
                    columna = y;
                    filaAccion = fila;
                    columnaAccion = columna;
                    if (cordenadas.length != 2 || x > 4 || y > 4 || x < 0 || y < 0) {
                        System.out.println("Respuesta no válida. Ingresa coordenadas válidas.");
                        intentosRestantes++;
                    }
                    for (int i = 0; i < monstruos.length; i++) {
                        if (monstruos[i].getFila() == x && monstruos[i].getColumna() == y) {
                            System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                            monstruos[i].dispararConCauchera(); // Restar vida del monstruo
                            System.out.println("Le has quitado 5 de vida al monstruo " + monstruos[i].getSimbolo());
                            break; // Salir del loop una vez que se ha encontrado el monstruo
                        }
                    }
                    System.out.println("Has disparado con cauchera (quita 5 vida)");
                } else if (respuestaMovimiento.equalsIgnoreCase("ii")) {
                    System.out.println("A que cordenadas deseas disparar? (x,y):");
                    String respuestaCordenadas = input.nextLine();
                    String[] cordenadas = respuestaCordenadas.split(",");
                    int x = Integer.parseInt(cordenadas[1]);
                    int y = Integer.parseInt(cordenadas[0]);
                    fila = x;
                    columna = y;
                    filaAccion = fila;
                    columnaAccion = columna;
                    if (cordenadas.length != 2 || x > 4 || y > 4 || x < 0 || y < 0) {
                        System.out.println("Respuesta no válida. Ingresa coordenadas válidas.");
                        intentosRestantes++;
                    }
                    for (int i = 0; i < monstruos.length; i++) {
                        if (monstruos[i].getFila() == x && monstruos[i].getColumna() == y) {
                            System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                            monstruos[i].pegarConPaloDeClavos(); // Restar vida del monstruo
                            System.out.println("Le has quitado 10 de vida al monstruo " + monstruos[i].getSimbolo());
                            break; // Salir del loop una vez que se ha encontrado el monstruo
                        }
                    }
                    System.out.println("Has pegado con palo de clavos (quita 10 de vida)");
                } else if (respuestaMovimiento.equalsIgnoreCase("iii")) {
                    System.out.println("A que cordenadas deseas disparar? (x,y):");
                    String respuestaCordenadas = input.nextLine();
                    String[] cordenadas = respuestaCordenadas.split(",");
                    int x = Integer.parseInt(cordenadas[1]);
                    int y = Integer.parseInt(cordenadas[0]);
                    fila = x;
                    columna = y;
                    filaAccion = fila;
                    columnaAccion = columna;
                    if (cordenadas.length != 2 || x > 4 || y > 4 || x < 0 || y < 0) {
                        System.out.println("Respuesta no válida. Ingresa coordenadas válidas.");
                        intentosRestantes++;
                    }
                    for (int i = 0; i < monstruos.length; i++) {
                        if (monstruos[i].getFila() == x && monstruos[i].getColumna() == y) {
                            System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                            monstruos[i].lanzarMolotov(); // Restar vida del monstruo
                            System.out.println("Le has quitado 20 de vida al monstruo " + monstruos[i].getSimbolo());
                            break; // Salir del loop una vez que se ha encontrado el monstruo
                        }
                    }
                    System.out.println("Has lanzado molotov (quita 20 de vida)");
                } else if (respuestaMovimiento.equalsIgnoreCase("iv")) {
                    System.out.println("A que cordenadas deseas disparar? (x,y):");
                    String respuestaCordenadas = input.nextLine();
                    String[] cordenadas = respuestaCordenadas.split(",");
                    int x = Integer.parseInt(cordenadas[1]);
                    int y = Integer.parseInt(cordenadas[0]);
                    fila = x;
                    columna = y;
                    filaAccion = fila;
                    columnaAccion = columna;
                    if (cordenadas.length != 2 || x > 4 || y > 4 || x < 0 || y < 0) {
                        System.out.println("Respuesta no válida. Ingresa coordenadas válidas.");
                        intentosRestantes++;
                    }
                    if (matriz[x][y] == ' ') {
                        matriz[x][y] = '#';

                        System.out.println("Montar bloqueo (restringe el movimiento de los monstruos)");
                    }
                } else if (!respuestaMovimiento.equalsIgnoreCase("i") && !respuestaMovimiento.equalsIgnoreCase("ii") && !respuestaMovimiento.equalsIgnoreCase("iii") && !respuestaMovimiento.equalsIgnoreCase("iv") && !respuestaMovimiento.equalsIgnoreCase("v") && !respuestaMovimiento.equalsIgnoreCase("vi")) {
                    System.out.println("Respuesta no válida. Ingresa una opción válida (i, ii, iii, iv, v)");
                    intentosRestantes++;
                } else if (respuestaMovimiento.equalsIgnoreCase("v")) {
                    System.out.println("A que cordenadas deseas disparar? (x,y):");
                    String respuestaCordenadas = input.nextLine();
                    String[] cordenadas = respuestaCordenadas.split(",");
                    int x = Integer.parseInt(cordenadas[1]);
                    int y = Integer.parseInt(cordenadas[0]);
                    fila = x;
                    columna = y;
                    filaAccion = fila;
                    columnaAccion = columna;
                    if (cordenadas.length != 2 || x > 4 || y > 4 || x < 0 || y < 0 ) {
                        System.out.println("Respuesta no válida. Ingresa coordenadas válidas.");
                        intentosRestantes++;
                    }
                    for (int i = 0; i < monstruos.length; i++) {
                        if (monstruos[i] != null) {
                                if (monstruos[i].getFila() == x && monstruos[i].getColumna() == y ) {
                                    System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                                    monstruos[i].TirarBomba(); // Restar vida del monstruo
                                    System.out.println("Le has quitado 5 de vida al monstruo " + monstruos[i].getSimbolo());

                                } else if (monstruos[i].getFila() - 1 == x && monstruos[i].getColumna() == y ) {
                                    System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                                    monstruos[i].TirarBomba(); // Restar vida del monstruo
                                    System.out.println("Le has quitado 30 de vida al monstruo " + monstruos[i].getSimbolo());

                                } else if (monstruos[i].getFila() + 1 == x && monstruos[i].getColumna() == y) {
                                    System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                                    monstruos[i].TirarBomba(); // Restar vida del monstruo
                                    System.out.println("Le has quitado 30 de vida al monstruo " + monstruos[i].getSimbolo());

                                } else if (monstruos[i].getFila() == x && monstruos[i].getColumna() - 1 == y) {
                                    System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                                    monstruos[i].TirarBomba(); // Restar vida del monstruo
                                    System.out.println("Le has quitado 30 de vida al monstruo " + monstruos[i].getSimbolo());

                                } else if (monstruos[i].getFila() == x && monstruos[i].getColumna() + 1 == y) {
                                    System.out.println("Monstruo en fila " + monstruos[i].getFila() + ", columna " + monstruos[i].getColumna());
                                    monstruos[i].TirarBomba(); // Restar vida del monstruo
                                    System.out.println("Le has quitado 30 de vida al monstruo " + monstruos[i].getSimbolo());

                                }
                            }
                    }
                    System.out.println("Has tirado una bomba (quita 30 vida)");


                } else if (respuestaMovimiento.equalsIgnoreCase("vi")) {
                    System.out.println("¡Hasta luego!");
                    break;
                }
                if (intentosRestantes == 1) {
                    System.out.println("Se acabaron tus intentos. ¡Perdiste!");
                }

                if (monstruos[0] != null) {
                if (monstruos[0].getVida() <= 0) {
                    System.out.println("Monstruo " + monstruos[0].getSimbolo() + " ha muerto");
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = ' ';
                    monstruos[0] = null;
                }
                }

                if (monstruos[1] != null) {
                if (monstruos[1].getVida() <= 0) {
                    System.out.println("Monstruo " + monstruos[1].getSimbolo() + " ha muerto");
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1] = null;
                }
                }

                if (monstruos[2] != null) {
                 if (monstruos[2].getVida() <= 0) {
                    System.out.println("Monstruo " + monstruos[2].getSimbolo() + " ha muerto");
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = ' ';
                    monstruos[2] = null;
                }
                }

                if (monstruos[3] != null) {
                    if (monstruos[3].getVida() <= 0) {
                        System.out.println("Monstruo " + monstruos[3].getSimbolo() + " ha muerto");
                        matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = ' ';
                        monstruos[3] = null;
                    }
                }

                if (monstruos[0] == null && monstruos[1] == null && monstruos[2] == null && monstruos[3] == null) {
                    System.out.println("Felicitaciones ¡Ganaste!");
                    break;
                }


                MovimientoMountruos(monstruos, matriz);
                imprimirMatriz(matriz);


                boolean Movimiento1 = false;
                boolean Movimiento2 = false;
                boolean Movimiento3 = false;

                 if (monstruos[0] != null) {
                if (monstruos[0].getFila() - 1 >= 0 && matriz[monstruos[0].getFila() - 1][monstruos[0].getColumna()] == ' ') {

                } else {
                    if (monstruos[0].getFila() + 1 < matriz.length && matriz[monstruos[0].getFila() + 1][monstruos[0].getColumna()] == ' ') {

                    } else {
                        if (monstruos[0].getColumna() - 1 >= 0 && matriz[monstruos[0].getFila()][monstruos[0].getColumna() - 1] == ' ') {

                        } else {
                            if (monstruos[0].getColumna() + 1 < matriz.length && matriz[monstruos[0].getFila()][monstruos[0].getColumna() + 1] == ' ') {

                            } else {
                                Movimiento1 = true;
                            }

                        }
                    }
                }
            }

                 if (monstruos[1] != null) {
                     if (monstruos[1].getFila() - 1 >= 0 && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna()] == ' ') {

                     } else {
                         if (monstruos[1].getFila() + 1 < matriz.length && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna()] == ' ') {

                         } else {
                             if (monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila()][monstruos[1].getColumna() - 1] == ' ') {

                             } else {
                                 if (monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila()][monstruos[1].getColumna() + 1] == ' ') {

                                 } else {
                                     if (monstruos[1].getFila() + 1 < matriz.length && monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna() + 1] == ' ') {

                                     } else {
                                         if (monstruos[1].getFila() - 1 >= 0 && monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna() - 1] == ' ') {

                                         } else {
                                             if (monstruos[1].getFila() + 1 < matriz.length && monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna() - 1] == ' ') {

                                             } else {
                                                 if (monstruos[1].getFila() - 1 >= 0 && monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna() + 1] == ' ') {

                                                 } else {
                                                     Movimiento2 = true;
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
                 }

                 if (monstruos[2] != null) {
                if (monstruos[2].getFila() + 2 < matriz.length && monstruos[2].getColumna() + 2 < matriz.length && matriz[monstruos[2].getFila() + 2][monstruos[2].getColumna() + 2] == ' ') {

                } else {
                    if (monstruos[2].getFila() - 2 >= 0 && monstruos[2].getColumna() - 2 >= 0 && matriz[monstruos[2].getFila() - 2][monstruos[2].getColumna() - 2] == ' ') {

                    } else {
                        if (monstruos[2].getFila() + 2 < matriz.length && monstruos[2].getColumna() - 2 >= 0 && matriz[monstruos[2].getFila() + 2][monstruos[2].getColumna() - 2] == ' ') {

                        } else {
                            if (monstruos[2].getFila() - 2 >= 0 && monstruos[2].getColumna() + 2 < matriz.length && matriz[monstruos[2].getFila() - 2][monstruos[2].getColumna() + 2] == ' ') {

                            } else {
                                Movimiento3 = true;
                            }

                        }
                    }
                }
            }

                if (monstruos[3] == null) {
                    if (Movimiento1 && Movimiento2 && Movimiento3) {
                        System.out.println("No se pueden mover mas, has ganado!");
                        break;
                    }
                }



            }
        }

    }


    public static void GenerarMonstruos(char[][] matriz, Monstruo monstruos[], int filaAccion, int columnaAccion) {
        Random rand = new Random();
        monstruos[0] = new Monstruo(100, 10, 1, '\u2663');
        monstruos[1] = new Monstruo(75, 10, 1, '\u2666');
        monstruos[2] = new Monstruo(50, 10, 2, '\u2660');
        monstruos[3] = new Monstruo(45, 10, 1, '\u2661');
        for (int i = 0; i < monstruos.length; i++) {
            int filaMonstruo, columnaMonstruo;
            char simboloMonstruo = monstruos[i].getSimbolo();
            do {
                filaMonstruo = rand.nextInt(matriz.length);
                columnaMonstruo = rand.nextInt(matriz[0].length);
                monstruos[i].setFila(filaMonstruo);
                monstruos[i].setColumna(columnaMonstruo);
            } while (matriz[filaMonstruo][columnaMonstruo] != ' ' || (filaMonstruo == filaAccion && columnaMonstruo == columnaAccion));

            monstruos[i].setFila(filaMonstruo);
            monstruos[i].setColumna(columnaMonstruo);

            matriz[filaMonstruo][columnaMonstruo] = simboloMonstruo;
        }

    }


    public static void MovimientoMountruos(Monstruo monstruos[], char matriz[][]) {
        Random rand1 = new Random();
        int Random1 = rand1.nextInt(3);

        if (monstruos[0] != null) {
        switch (Random1) {

            case 0:
                if (monstruos[0].getFila() - 1 >= 0 && matriz[monstruos[0].getFila() - 1][monstruos[0].getColumna()] == ' ') {
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = ' ';
                    monstruos[0].setFila(monstruos[0].getFila() - 1);
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = monstruos[0].getSimbolo();
                }
                break;

            case 1:
                if (monstruos[0].getFila() + 1 < matriz.length && matriz[monstruos[0].getFila() + 1][monstruos[0].getColumna()] == ' ') {
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = ' ';
                    monstruos[0].setFila(monstruos[0].getFila() + 1);
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = monstruos[0].getSimbolo();
                }
                break;
            case 2:
                if (monstruos[0].getColumna() - 1 >= 0 && matriz[monstruos[0].getFila()][monstruos[0].getColumna() - 1] == ' ') {
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = ' ';
                    monstruos[0].setColumna(monstruos[0].getColumna() - 1);
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = monstruos[0].getSimbolo();
                }
                break;
            case 3:
                if (monstruos[0].getColumna() + 1 < matriz.length && matriz[monstruos[0].getFila()][monstruos[0].getColumna() + 1] == ' ') {
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = ' ';
                    monstruos[0].setColumna(monstruos[0].getColumna() + 1);
                    matriz[monstruos[0].getFila()][monstruos[0].getColumna()] = monstruos[0].getSimbolo();
                }
                break;
        }
        }

        Random rand2 = new Random();
        int Random2 = rand2.nextInt(7);

        if (monstruos[1] != null) {
        switch (Random2) {
            case 0:
                if (monstruos[1].getFila() - 1 >= 0 && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna()] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setFila(monstruos[1].getFila() - 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;

            case 1:
                if (monstruos[1].getFila() + 1 < matriz.length && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna()] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setFila(monstruos[1].getFila() + 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
            case 2:
                if (monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila()][monstruos[1].getColumna() - 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() - 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
            case 3:
                if (monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila()][monstruos[1].getColumna() + 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() + 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;

            case 4:
                if (monstruos[1].getFila() + 1 < matriz.length && monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna() + 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() + 1);
                    monstruos[1].setFila(monstruos[1].getFila() + 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
            case 5:
                if (monstruos[1].getFila() - 1 >= 0 && monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna() - 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() - 1);
                    monstruos[1].setFila(monstruos[1].getFila() - 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
            case 6:
                if (monstruos[1].getFila() + 1 < matriz.length && monstruos[1].getColumna() - 1 >= 0 && matriz[monstruos[1].getFila() + 1][monstruos[1].getColumna() - 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() - 1);
                    monstruos[1].setFila(monstruos[1].getFila() + 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
            case 7:
                if (monstruos[1].getFila() - 1 >= 0 && monstruos[1].getColumna() + 1 < matriz.length && matriz[monstruos[1].getFila() - 1][monstruos[1].getColumna() + 1] == ' ') {
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = ' ';
                    monstruos[1].setColumna(monstruos[1].getColumna() + 1);
                    monstruos[1].setFila(monstruos[1].getFila() - 1);
                    matriz[monstruos[1].getFila()][monstruos[1].getColumna()] = monstruos[1].getSimbolo();
                }
                break;
        }
    }

        Random rand3 = new Random();
        int Random3 = rand3.nextInt(3);

        if (monstruos[2] != null) {
        switch (Random3) {

            case 0:
                if (monstruos[2].getFila() + 2 < matriz.length && monstruos[2].getColumna() + 2 < matriz.length && matriz[monstruos[2].getFila() + 2][monstruos[2].getColumna() + 2] == ' ') {
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = ' ';
                    monstruos[2].setColumna(monstruos[2].getColumna() + 2);
                    monstruos[2].setFila(monstruos[2].getFila() + 2);
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = monstruos[2].getSimbolo();
                }
                break;
            case 1:
                if (monstruos[2].getFila() - 2 >= 0 && monstruos[2].getColumna() - 2 >= 0 && matriz[monstruos[2].getFila() - 2][monstruos[2].getColumna() - 2] == ' ') {
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = ' ';
                    monstruos[2].setColumna(monstruos[2].getColumna() - 2);
                    monstruos[2].setFila(monstruos[2].getFila() - 2);
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = monstruos[2].getSimbolo();
                }
                break;
            case 2:
                if (monstruos[2].getFila() + 2 < matriz.length && monstruos[2].getColumna() - 2 >= 0 && matriz[monstruos[2].getFila() + 2][monstruos[2].getColumna() - 2] == ' ') {
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = ' ';
                    monstruos[2].setColumna(monstruos[2].getColumna() - 2);
                    monstruos[2].setFila(monstruos[2].getFila() + 2);
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = monstruos[2].getSimbolo();
                }
                break;
            case 3:
                if (monstruos[2].getFila() - 2 >= 0 && monstruos[2].getColumna() + 2 < matriz.length && matriz[monstruos[2].getFila() - 2][monstruos[2].getColumna() + 2] == ' ') {
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = ' ';
                    monstruos[2].setColumna(monstruos[2].getColumna() + 2);
                    monstruos[2].setFila(monstruos[2].getFila() - 2);
                    matriz[monstruos[2].getFila()][monstruos[2].getColumna()] = monstruos[2].getSimbolo();
                }
                break;
        }
    }

            Random rand4 = new Random();
            int Random4 = rand4.nextInt(3);

            if (monstruos[3] != null) {
                switch (Random4) {

                    case 0:
                        if (monstruos[3].getFila() + 1 < matriz.length && monstruos[3].getColumna() + 1 < matriz.length) {
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = ' ';
                            monstruos[3].setColumna(monstruos[3].getColumna() + 1);
                            monstruos[3].setFila(monstruos[3].getFila() + 1);
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = monstruos[3].getSimbolo();
                        }
                        break;
                    case 1:
                        if (monstruos[3].getFila() - 1 >= 0 && monstruos[3].getColumna() - 1 >= 0) {
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = ' ';
                            monstruos[3].setColumna(monstruos[3].getColumna() - 1);
                            monstruos[3].setFila(monstruos[3].getFila() - 1);
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = monstruos[3].getSimbolo();
                        }
                        break;
                    case 2:
                        if (monstruos[3].getFila() + 1 < matriz.length && monstruos[3].getColumna() - 1 >= 0) {
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = ' ';
                            monstruos[3].setColumna(monstruos[3].getColumna() - 1);
                            monstruos[3].setFila(monstruos[3].getFila() + 1);
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = monstruos[3].getSimbolo();
                        }
                        break;
                    case 3:
                        if (monstruos[3].getFila() - 1 >= 0 && monstruos[3].getColumna() + 1 < matriz.length) {
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = ' ';
                            monstruos[3].setColumna(monstruos[3].getColumna() + 1);
                            monstruos[3].setFila(monstruos[3].getFila() - 1);
                            matriz[monstruos[3].getFila()][monstruos[3].getColumna()] = monstruos[3].getSimbolo();
                        }
                        break;


                }
            }
    }
}


    class Monstruo {
        int nivelVida;
        int movimientosDisponibles;
        int distanciaSalto;
        char simbolo;
        int fila;
        int columna;

        public Monstruo(int nivelVida, int movimientosDisponibles, int distanciaSalto, char simbolo) {
            this.nivelVida = nivelVida;
            this.movimientosDisponibles = movimientosDisponibles;
            this.distanciaSalto = distanciaSalto;
            this.simbolo = simbolo;
            this.fila = 0;
            this.columna = 0;
        }

        public int getVida() {
            return nivelVida;
        }

        public char getSimbolo() {
            return simbolo;
        }

        public void setSimbolo(char simbolo) {
            this.simbolo = simbolo;
        }


        public void setnivelVida(int vida) {
            this.nivelVida = vida;
        }

        public int getMovimientosDisponibles() {
            return movimientosDisponibles;
        }

        public void setMovimientosDisponibles(int movimientosDisponibles) {
            this.movimientosDisponibles = movimientosDisponibles;
        }

        public int getDistanciaSalto() {
            return distanciaSalto;
        }

        public void setDistanciaSalto(int distanciaSalto) {
            this.distanciaSalto = distanciaSalto;
        }


        public void dispararConCauchera() {
            int dano = 5;
            int nuevaVida = this.nivelVida - dano;
            setnivelVida(nuevaVida);
        }

        public void pegarConPaloDeClavos() {
            int dano = 10;
            int nuevaVida = this.nivelVida - dano;
            setnivelVida(nuevaVida);
        }

        public void lanzarMolotov() {
            int dano = 20;
            int nuevaVida = this.nivelVida - dano;
            setnivelVida(nuevaVida);
        }

        public void montarBloqueo() {
            int nuevaVida = 0;
            setnivelVida(nuevaVida);
            movimientosDisponibles = 0;
        }

        public void TirarBomba(){
            int dano = 30;
            int nuevaVida = this.nivelVida - dano;
            setnivelVida(nuevaVida);
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        public void setFila(int fila) {
            this.fila = fila;
        }

        public void setColumna(int columna) {
            this.columna = columna;
        }
    }

    //Game by Miguel Mercado && Juan Jose Botero.
    //EAFIT University. 2023.

