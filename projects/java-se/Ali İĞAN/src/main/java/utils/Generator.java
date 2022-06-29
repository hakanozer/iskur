package utils;

import java.sql.Statement;

public class Generator {
    //database üzerinden sql komutlarını çalıştırabilmek için bağlantısını kurduğumuz
    //DB sınıfını burada tetikleyip database'ye bağlanırız
    DB db = new DB();
    public Generator() {
        userGenerator();
    }
    //sql sorgularını çalıştırabilmek için aşagıdaki methodu oluşturduk
    private void userGenerator() {
        String sql = "CREATE TABLE if not exists \"user\" (\n" +
                "\t\"uid\"\tINTEGER,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"surname\"\tTEXT,\n" +
                "\t\"email\"\tTEXT NOT NULL,\n" +
                "\t\"password\"\tTEXT NOT NULL,\n" +
                "\tCONSTRAINT \"user_pk\" PRIMARY KEY(\"uid\" AUTOINCREMENT)\n" +
                ");";
        sqlExecute(sql);
        String q = "create unique index if not exists user_email_uindex\n" +
                "\ton user (email);\n";
        sqlExecute(q);
    }

    private void sqlExecute( String sql ) {
        try {
            Statement st = db.connect().createStatement();
            st.execute( sql );
        }catch ( Exception ex ) {
            System.err.println( "userGenerator Error : " + ex );
        }
    }


}


