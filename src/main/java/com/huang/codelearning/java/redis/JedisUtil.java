package com.huang.codelearning.java.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Loger
 * Date: 2018-07-30
 * TIme: 23:54
 * Description :
 */
public class JedisUtil {

    private static JedisPoolConfig config;

    private static JedisPool jedisPool;

    private static String password = "";

    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, "localhost", 6379);
    }


    /**
     * @description fjdasl;
     * @return Jedis constructor
     */
    public static Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
//        jedis.auth(password);
        return jedis;
    }

    /**
     * jedis放回连接池
     *
     * @param jedis jedis
     */
    public static void close(Jedis jedis) {
        //从源码可以分析得到，如果是使用连接池的形式，这个并非真正的close,而是把连接放回连接池中
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * @description fad
     * @param key key
     * @return faddy
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    /**
     * set
     *
     * @param key key
     * @param value value
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    public static void set(String key, String value, long seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            //* @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
            //     *                     *          if it already exist.
            //     *                     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
            jedis.set(key, value, "NX", "EX", seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }


    public static Long incr(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void hset(String key,String field,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key,field,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String hget(String key,String field){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key,field);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }

    public static Map<String,String> hgetAll(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hgetAll(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String blpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String blpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(0, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void lpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String brpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String brpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(0, key);
            return list.get(1);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void rpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     * 获取key过期时间 -1表示永久 -2表示该key不存在
     * @param key key
     * @return 过期时间
     */
    public static long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

}