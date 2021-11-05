package top.xinsin.service;

import com.alibaba.fastjson.JSON;
import top.xinsin.dao.CRUDDao;
import top.xinsin.pojo.CRUD;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CRUDService {
    public String CRUD(InputStreamReader in) throws IOException {
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read()) != -1){
            sb.append((char)len);
        }
        System.out.println(sb);
        CRUD crud = JSON.parseObject(sb.toString(), CRUD.class);
        String s = null;
        switch (crud.getOperationType()){
            case 1:
                s = insert(crud.getSa_name(),crud.getSa_account(),crud.getSa_password(),crud.getSa_authority());
                break;
            case 2:
                s = delete(crud.getSa_id());
                break;
            case 3:
                s = update(crud.getSa_id(), crud.getSa_name(),crud.getSa_account(),crud.getSa_password(),crud.getSa_authority());
                break;
            default:
                return "没有该操作值";
        }
        return s;
    }

    private String update(int sa_id, String sa_name, String sa_account, String sa_password, String sa_authority) {
        Integer update = null;
        try {
            update = CRUDDao.update(sa_id,sa_name,sa_account,sa_password,sa_authority);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "{\"operation\":3,\"status\":"+update+"}";
    }

    private String delete(int sa_id) {
        Integer delete = null;
        try {
            delete = CRUDDao.delete(sa_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "{\"operation\":2,\"status\":"+delete+"}";
    }

    private String insert(String sa_name, String sa_account, String sa_password, String sa_authority) {
        Integer insert = null;
        try {
            insert = CRUDDao.insert(sa_name, sa_account, sa_password, sa_authority);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "{\"operation\":1,\"status\":"+insert+"}";
    }
}
