package app.dao;

public class ExcepcionDAO extends Exception {

    private static final long serialVersionUID = 1L;

    public ExcepcionDAO() {
        super();
    }

    public ExcepcionDAO(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ExcepcionDAO(String arg0) {
        super(arg0);
    }

    public ExcepcionDAO(Throwable arg0) {
        super(arg0);
    }
}
