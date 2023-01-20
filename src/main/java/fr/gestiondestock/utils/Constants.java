package fr.gestiondestock.utils;

public interface Constants {

    String AUTHORIZATION = "Authorization";

    String BEARER = "Bearer ";

    String APP_ROOT = "gestiondestock/v1";

    String ARTICLE_ENDPOINT = APP_ROOT + "/articles";

    String CATEGORY_ENDPOINT = APP_ROOT  + "/categories";

    String CLIENT_ENDPOINT = APP_ROOT + "/clients";

    String COMMANDE_CLIENT_ENDPOINT = APP_ROOT + "/commandeClients";

    String FOURNISSEUR_ENDPOINT = APP_ROOT + "/fournisseurs";

    String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandeFournisseurs";

    String ENTREPRISE_ENDPOINT = APP_ROOT + "/entreprises";

    String VENTES_ENDPOINT = APP_ROOT + "/ventes";

    String UTILISATEUR_ENDPOINT = APP_ROOT + "/utilisateurs";

    String AUTHENTICATION_ENDPOINT = APP_ROOT + "/auth";
    String PHOTO_ENDPOINT = APP_ROOT + "/photos";
    String MVTSTOCK_ENDPOINT = APP_ROOT + "/mvtstocks";

}
