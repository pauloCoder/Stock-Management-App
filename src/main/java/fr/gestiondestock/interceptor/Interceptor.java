package fr.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            if (sql.toLowerCase().contains("where")) {
                sql += " and entreprise_id = 1";
            }
            else {
                sql += " where entreprise_id = 1";
            }
        }
        return super.onPrepareStatement(sql);
    }
}
