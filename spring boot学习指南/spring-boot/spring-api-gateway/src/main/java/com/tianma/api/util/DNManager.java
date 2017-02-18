package com.tianma.api.util;

import com.tianma.api.domain.oauthclient.DN;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by zhengpeiwei on 16/4/20.
 */
@Component
public class DNManager {

    public static HashMap<String,HashMap<Integer,DN>> map=new HashMap<String, HashMap<Integer,DN>>();

    static {
        HashMap<Integer,DN> egoo1=new HashMap<Integer,DN>();
        egoo1.put(new Integer(1),new DN("1", "123"));//这里面的key是以DN的id值作为key
        egoo1.put(new Integer(2),new DN("2", "123"));
        egoo1.put(new Integer(3),new DN("3", "123"));
        map.put("egoo1",egoo1);

        HashMap<Integer,DN> egoo2=new HashMap<Integer,DN>();
        egoo2.put(new Integer(4),new DN("4", "123"));//这里面的key是以DN的id值作为key
        egoo2.put(new Integer(5),new DN("5", "123"));
        egoo2.put(new Integer(6),new DN("6", "123"));
        map.put("egoo2",egoo2);

        HashMap<Integer,DN> egoo3=new HashMap<Integer,DN>();
        egoo3.put(new Integer(7),new DN("7", "123"));//这里面的key是以DN的id值作为key
        egoo3.put(new Integer(8),new DN("8", "123"));
        egoo3.put(new Integer(9),new DN("9", "123"));
        map.put("egoo3",egoo3);
    }


}
