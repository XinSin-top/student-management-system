package top.xinsin;

import org.junit.Test;

public class Break1 {
    @Test
    public void break1(){
        for (int i = 200; i <= 300; i++) {
            if (i / 17 == 0){
                System.out.println(i);
                break;
            }
        }
        int i = 97;
        System.out.println(i / 10);
        System.out.println(i % 10);
    }

    @Test
    public void continue1(){
        for (int i = 1; i < 101; i++) {
            if (i / 7 == 0 || i % 10 == 7 || i / 10 == 7){

            }else{
                System.out.println(i);
            }
        }
    }
}
