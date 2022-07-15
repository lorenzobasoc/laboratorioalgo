import java.util.Scanner;

public class editDistance
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        String string1 = sc.nextLine();
        String string2 = sc.nextLine();
		System.out.println(editDist(string1, string2));
	}

    public static int editDist(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int m[][] = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    m[i][j] = j;
                } else if (j == 0) {
                    m[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    m[i][j] = m[i - 1][j - 1];
                } else {
                    m[i][j] = 1 + min(m[i][j - 1], m[i - 1][j], m[i - 1][j - 1]);
                }
            }
        }
        return m[len1][len2];
    }
    
    public static int min(int x, int y, int z) {
        if (x <= y && x <= z) {
            return x;
        } else if (y <= x && y <= z) {
            return y;
        } else {
            return z;
        }
    }
}
