package top.xinsin.service;

public class RamService {
    public String getRam(){
        //获取空闲jvm空间
        long idle = Runtime.getRuntime().freeMemory();
        //获取jvm尝试使用的最大内存空间
        long max = Runtime.getRuntime().maxMemory();
        //获取jvm正在使用的内存空间
        long total = Runtime.getRuntime().totalMemory();
        StringBuilder sb = new StringBuilder();
        sb.append("{" +
                "\"JVMfree\":"+idle +
                ",\"JVMmax\":" + max +
                ",\"JVMtotal\":" + total +
                "}");
        return String.valueOf(sb);
    }
}
