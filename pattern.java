public class pattern {
    /*
    Pattern 1: Rectangular Star Pattern
    * * * * *
    * * * * *
    * * * * *
    * * * * *
    * * * * *
    */
    public static void pattern1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 2: Right-Angled Triangle Pattern
    *
    * *
    * * *
    * * * *
    * * * * *
    */
    public static void pattern2(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 3: Right-Angled Number Pyramid
    1
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5
    */
    public static void pattern3(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 4: Right-Angled Number Pyramid - II
    1
    2 2
    3 3 3
    4 4 4 4
    5 5 5 5 5
    */
    public static void pattern4(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 5: Inverted Right Pyramid
    * * * * *
    * * * *
    * * *
    * *
    *
    */
    public static void pattern5(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 6: Inverted Numbered Right Pyramid
    1 2 3 4 5
    1 2 3 4
    1 2 3
    1 2
    1
    */
    public static void pattern6(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 7: Star Pyramid
        *
       ***
      *****
     *******
    *********
    */
    public static void pattern7(int n) {
        for (int i = 0; i < n; i++) {
            // Space
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            // Star
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            // Space
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 8: Inverted Star Pyramid
    *********
     *******
      *****
       ***
        *
    */
    public static void pattern8(int n) {
        for (int i = 0; i < n; i++) {
            // Space
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // Star
            for (int j = 0; j < 2 * n - (2 * i + 1); j++) {
                System.out.print("*");
            }
            // Space
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 9: Diamond Star Pattern
        *
       ***
      *****
     *******
    *********
    *********
     *******
      *****
       ***
        *
    */
    public static void pattern9(int n) {
        pattern7(n);
        pattern8(n);
    }

    /*
    Pattern 10: Half Diamond Star Pattern
    *
    **
    ***
    ****
    *****
    ****
    ***
    **
    *
    */
    public static void pattern10(int n) {
        // Upper half
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Lower half
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    Pattern 11: Binary Number Triangle Pattern
    1
    0 1
    1 0 1
    0 1 0 1
    1 0 1 0 1
    */
    public static void pattern11(int n) {
        int start = 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) start = 1;
            else start = 0;
            for (int j = 0; j <= i; j++) {
                System.out.print(start + " ");
                start = 1 - start;
            }
            System.out.println();
        }
    }

    /*
    Pattern 12: Number Crown Pattern
    1       1
    12     21
    123   321
    1234 4321
    1234554321
    */
    public static void pattern12(int n) {

    for (int i = 1; i <= n; i++) {
        // Part 1: Increasing numbers
        for (int j = 1; j <= i; j++) {
            System.out.print(j);
        }

        // Part 2: Spaces
        int spaces = 2 * (n - i);

        for (int j = 1; j <= spaces; j++) {
            System.out.print(" ");
        }

        // Part 3: Decreasing numbers
        for (int j = i; j >= 1; j--) {
            System.out.print(j);
        }

        System.out.println();
        }
    }

    /*
    Pattern 13: Increasing Number Triangle Pattern
    1
    2 3
    4 5 6
    7 8 9 10
    11 12 13 14 15
    */
    public static void pattern13(int n) {
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }

    /*
    Pattern 14: Increasing Letter Triangle Pattern
    A
    A B
    A B C
    A B C D
    A B C D E
    */
    public static void pattern14(int n) {
        for (int i = 0; i < n; i++) {
            for (char ch = 'A'; ch <= 'A' + i; ch++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 15: Reverse Letter Triangle Pattern
    A B C D E
    A B C D
    A B C
    A B
    A
    */
    public static void pattern15(int n) {
        for (int i = 0; i < n; i++) {
            for (char ch = 'A'; ch <= 'A' + (n - i - 1); ch++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 16: Alpha-Ramp Pattern
    A
    B B
    C C C
    D D D D
    E E E E E
    */
    public static void pattern16(int n) {
        for (int i = 0; i < n; i++) {
            char ch = (char) ('A' + i);
            for (int j = 0; j <= i; j++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 17: Alpha-Hill Pattern
        A
       ABA
      ABCBA
     ABCDCBA
    ABCDEDCBA
    */
    public static void pattern17(int n) {
        for (int i = 0; i < n; i++) {

            // Part 1: Spaces
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }

            // Part 2: Increasing letters
            for (char ch = 'A'; ch <= 'A' + i; ch++) {
                System.out.print(ch);
            }

            // Part 3: Decreasing letters
            for (char ch = (char)('A' + i - 1); ch >= 'A'; ch--) {
                System.out.print(ch);
            }

            System.out.println();
        }
    }

    /*
    Pattern 18: Alpha-Triangle Pattern
    E
    E D
    E D C
    E D C B
    E D C B A
    */
    public static void pattern18(int n) {
        for (int i = 0; i < n; i++) {
            for (char ch = (char) ('A' + n - 1); ch >= (char) ('A' + n - 1 - i); ch--) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    /*
    Pattern 19: Symmetric-Void Pattern
    **********
    ****  ****
    ***    ***
    **      **
    *        *
    *        *
    **      **
    ***    ***
    ****  ****
    **********
    */
    public static void pattern19(int n) {

        for (int i = 0; i < n; i++) {
            // Stars
            for (int j = 1; j <= n - i; j++) {
                System.out.print("*");
            }
            // Spaces
            for (int j = 0; j < 2 * i; j++) {
                System.out.print(" ");
            }
            // Stars
            for (int j = 1; j <= n - i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            // Stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            // Spaces
            for (int j = 0; j < 2 * (n - i); j++) {
                System.out.print(" ");
            }
            // Stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    Pattern 20: Symmetric-Butterfly Pattern
    *        *
    **      **
    ***    ***
    ****  ****
    **********
    ****  ****
    ***    ***
    **      **
    *        *
    */
    public static void pattern20(int n) {

        // Upper half
        for (int i = 1; i <= n; i++) {

            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Spaces
            int spaces = 2 * (n - i);
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        // Lower half
        for (int i = n - 1; i >= 1; i--) {

            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            // Spaces
            int spaces = 2 * (n - i);
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /*
    Pattern 21: Hollow Rectangle Pattern
    *****
    *   *
    *   *
    *   *
    *****
    */
    public static void pattern21(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /*
    Pattern 22: The Number Pattern
    4444444
    4333334
    4322234
    4321234
    4322234
    4333334
    4444444
    */
    public static void pattern22(int n) {
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int top = i;
                int left = j;
                int right = (2 * n - 2) - j;
                int bottom = (2 * n - 2) - i;
                System.out.print(n - Math.min(Math.min(top, bottom), Math.min(left, right)));
            }
            System.out.println();
        }
    }
}
