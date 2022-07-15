import java.util.Scanner;

public class PeriodNaive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int n = input.length();
        int r = Bordo(input);
        System.out.println(P(n, r));
    }
    
    public static int P(int n, int r) {
        return n - r;
    }
    
    /* LINEARE
        L'algoritmo è compsto da 6 operazioni di assegnazione di costo θ(1), e un ciclo while che itera sull'intera lunghezza dell'input. 
        All'interno di questo sono presenti solo operazioni di confronto e assegnazione, sempre di costo θ(1) che non provocano l'aumentare della complessità generale
        dell'algoritmo, avendo quindi terminazione in tempo lineare ( θ(n) ).
    */
    public static int Bordo(String s) {
        int n = s.length();
        int[] lps = new int[n];
        lps[0] = 0;
        int len = 0;
        int i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
		int res = lps[n-1];
        return res;
    }


    // QUADRATICO
     public static int periodNaiveAlgorithm(String s){
        int n = s.length();
        for(int p = 1; p < n; p++){
            if(checkRest(s, p))
                return p;
        }
        return n;
    }
    public static boolean checkRest(String s, int p){
        for(int i = (s.length()-1); i >= p; i--)
            {
                if(s.charAt(i) != s.charAt(i-p))
                return false;
            }
            return true;
    }
}
