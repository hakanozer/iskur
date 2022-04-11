package models;

import props.Customer;
import utils.DB;

import java.sql.PreparedStatement;
import java.util.List;

public class CustomerImpl implements ICustomer {
    DB db = new DB();
    @Override
    public int customerInsert(Customer customer) {
        int status=0;
        try {
            String sql="insert into customer values(null,?,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,customer.getName());
            pre.setString(2,customer.getSurname());
            pre.setString(3,customer.getEmail());
            pre.setString(4,customer.getPhone());
            pre.setString(5,customer.getAddress());
            status=pre.executeUpdate();
        }catch (Exception e){
            System.out.println("Customer Insert Error: "+e);
            if(e.toString().contains("UNIQUE")){
                status=-1;
            }
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerDelete(int cid) {
    return 0;
    }

    @Override
    public int customerUpdate(Customer customer) {
        return 0;
    }

    @Override
    public List<Customer> customerList() {
        return null;
    }

    @Override
    public List<Customer> customerSearch(String data) {
        return null;
    }
}
