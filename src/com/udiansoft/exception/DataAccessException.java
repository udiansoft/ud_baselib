package com.udiansoft.exception;

public class DataAccessException extends NestedRuntimeException {
    /**
     * Constructor for DataAccessException.
     * @param msg message
     */
    public DataAccessException(String msg) {
      super(msg);
    }

    /**
     * Constructor for DataAccessException.
     * @param msg message
     * @param ex root cause (usually from using a underlying
     * data access API such as JDBC)
     */
    public DataAccessException(String msg, Throwable ex) {
      super(msg, ex);
    }

}
