package com.example.springboogmp.lambda;//wangDD

//2022-04-2022/4/29-20:39
public class Test02 {
    public static void main(String[] args) {

        for (int i =1; i < 10; i++) {
            boolean mod = mod(new Mod() {
                @Override
                public boolean mod(int a) {
                    return a % 2 == 0;
                }
            }, i);
            if (mod) System.out.println(i);
        }

        for (int i = 0; i <10; i++) {
            boolean mod = mod((z) -> z % 2 == 0, i);
            if (!mod) System.out.println(i);

        }


    }
    static boolean mod(Mod m,int number){

        return m.mod(number);
    }
}
