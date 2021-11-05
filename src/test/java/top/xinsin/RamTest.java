package top.xinsin;

import org.junit.Test;

public class RamTest {
    @Test
    public void test(){
        //获取空闲jvm空间
        System.out.println(Runtime.getRuntime().freeMemory());
        //获取jvm尝试使用的最大内存空间
        System.out.println(Runtime.getRuntime().maxMemory());
        //获取jvm正在使用的内存空间
        System.out.println(Runtime.getRuntime().totalMemory());
    }
}
