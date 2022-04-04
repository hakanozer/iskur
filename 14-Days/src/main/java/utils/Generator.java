package utils;

import java.sql.Statement;

public class Generator {

    DB db = new DB();
    public Generator() {
        userGenerator();
    }

    private void userGenerator() {
        String sql = "create table if not exists user\n" +
                "(\n" +
                "\tuid INTEGER\n" +
                "\t\tconstraint user_pk\n" +
                "\t\t\tprimary key autoincrement,\n" +
                "\tname TEXT,\n" +
                "\tsurname TEXT,\n" +
                "\temail TEXT not null,\n" +
                "\tpassword TEXT not null\n" +
                ");\n" +
                "\n" +
                "create unique index user_email_uindex\n" +
                "\ton user (email);\n" +
                "\n";
        try {
            Statement st = db.connect().createStatement();
            st.execute(sql);
        }catch (Exception ex) {
            System.err.println("userGenerator Error : " + ex);
        }
    }


}
