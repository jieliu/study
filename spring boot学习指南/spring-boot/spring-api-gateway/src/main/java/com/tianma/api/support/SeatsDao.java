package com.tianma.api.support;

import com.tianma.api.pojo.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Created by Franco on 2016/2/26.
 */


@Repository
public class SeatsDao {
    @Autowired
    private Dao dao;//这里有问题,这个dao的sqlsessionfactory是对应的primaryfactory,对应primaryDS,不是freelinkconfig的东西

    public List<Seats> getAll() {
        List<Seats> result = dao.getList("org.mybatis.config.AuthenticNumberMapper.", "selectAll");
        return result;
    }

    public Seats getById(Seats seats) {
        Seats result = dao.get("org.mybatis.config.AuthenticNumberMapper.", "selectById",seats);
        return result;
    }

    public Seats getByPhoneNumber(Seats seats) {
        Seats result = dao.get("org.mybatis.config.AuthenticNumberMapper.", "selectByPhoneNumber",seats);
        return result;
    }

    public void update(Seats seats) {
        dao.update("org.mybatis.config.AuthenticNumberMapper.", "updateSeats", seats);
    }

    public void insert(Seats seats) {
        dao.insert("org.mybatis.config.AuthenticNumberMapper.", "insertSeats", seats);
    }

    public void delete(Seats seats) {
        dao.delete("org.mybatis.config.AuthenticNumberMapper.", "deleteSeats", seats);
    }

}
