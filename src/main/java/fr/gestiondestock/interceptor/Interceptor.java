package fr.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            //select utilisateu0_.
            final String entityName = sql.substring(7 , sql.indexOf("."));
            final String idEntreprise = MDC.get("idEntreprise");
            final boolean test = StringUtils.hasLength(entityName) && !entityName.toLowerCase().contains("entreprise") &&
            !entityName.toLowerCase().contains("roles") && StringUtils.hasLength(idEntreprise);
            if (test) {
                if (sql.toLowerCase().contains("where")) {
                    sql += String.format(" and %s.entreprise_id = %s" , entityName , idEntreprise);
                }
                else {
                    sql += String.format(" where %s.entreprise_id = %s" , entityName , idEntreprise);
                }
            }

        }
        return super.onPrepareStatement(sql);
    }
}
