package com.blstream.myhoard.constants;

public class Constants {

        public final static int TOKEN_KEEP_ALIVE_TIME = 3600 * 24;//300; // TODO move to application.properties

        public final static int ERROR_CODE_AUTH_BAD_CREDENTIALS = 101;
        public final static int ERROR_CODE_AUTH_TOKEN_NOT_PROVIDED = 102;
        public final static int ERROR_CODE_AUTH_TOKEN_INVALID = 103;
        public final static int ERROR_CODE_FORBIDDEN = 104;
        public final static int ERROR_CODE_BAD_REQUEST = 201;
        public final static int ERROR_CODE_NOT_FOUND = 202;
        public final static int ERROR_CODE_INTERNAL_SERVER_ERROR = 301;
        public final static int ERROR_CODE_AUTH_UNKNOW_ERROR = 400;
        
        public final static String USER = "user";

}
