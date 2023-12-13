package S1.Testing.Nivel_1.Ejercicio2;

public class CalculoDni {

    /**
     *                                                  Tabla DNI´s
     * RESTO 	0 	1 	2 	3 	4 	5 	6 	7 	8 	9 	10 	11  12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22
     * LETRA 	T 	R 	W 	A 	G 	M 	Y 	F 	P 	D 	X 	B   N 	J 	Z 	S 	Q 	V 	H 	L 	C 	K 	E
     */
    static char[] letrasDni = new char[]{'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H',
            'L','C','K','E'};
    public char obtenerLetraDni(int dni)
    {
        int resto = dni%23;
        return letrasDni[resto];
    }
}
