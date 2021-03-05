package lux.pe.na.store.utils;

public class DataStatus {

    private DataStatus() {
    }

    public static final String ROLE_NAME = "No se encontro el rol con el NOMBRE :";

    public static final String ROLE_USER = "hasRole('USER')";
    public static final String ROLE_ADMIN = "hasRole('ADMIN')";

    public static final String USER_ID = "No se encontro el usuario con el ID :";
    public static final String USER_NAME = "No se encontro el usuario con el NOMBRE :";
    public static final String USER_EXISTS = "El usuario ya existe";
    public static final String USER_MAIL = "El email ya existe";
    public static final String USER_OK = "Usuario registrado con exito";

    public static final boolean ENABLED = true;
    public static final boolean DISABLED = false;

    public static final String CATEGORY_ID = " No se encontro la categoria con el ID :";
    public static final String PRODUCT_ID = " No se encontro el product con el ID :";
}
