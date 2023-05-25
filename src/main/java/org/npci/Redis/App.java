package org.npci.Redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonProcessingException
    {
        System.out.println( "Hello World!" );
        
        Employee emp1 = new Employee();
        emp1.setId(0);
        emp1.setName("Sahil");
        emp1.setLastName("Shreshth");
        emp1.setDoj("26-sep-2022");
        emp1.setAddress("Mumbai");
        
        
        Employee emp2 = new Employee();
        emp2.setId(1);
        emp2.setName("Raja");
        emp2.setLastName("Naidu");
        emp2.setDoj("26-sep-2018");
        emp2.setAddress("Chennai");
        
        ObjectMapper mapper = new ObjectMapper();
        String emp1Json = mapper.writeValueAsString(emp1);
        String emp2Json = mapper.writeValueAsString(emp2);
        
        
        Jedis myredis = new Jedis("localhost");
        
        myredis.set(emp1.getId()+"", emp1Json);
        myredis.set(emp2.getId()+"", emp2Json);
        
        System.out.println(myredis.get(emp1.getId()+""));
        System.out.println(myredis.get("1"));
        
        myredis.close();
        
    }
}
