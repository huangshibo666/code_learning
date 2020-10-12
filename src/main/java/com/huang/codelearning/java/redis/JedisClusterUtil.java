package com.huang.codelearning.java.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: huangshibo
 * @Date: 2020/10/12 20:06
 */
public class JedisClusterUtil {
    public static void main(String[] args) throws IOException {
        HostAndPort localhost = new HostAndPort("localhost", 6379);
        Set<HostAndPort> redisNodeSet = new HashSet<>();
        redisNodeSet.add(localhost);
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数, 应用自己评估，不要超过Redis每个实例最大的连接数
        config.setMaxTotal(1000);
        //最大空闲连接数, 应用自己评估，不要超过Redis每个实例最大的连接数
        config.setMaxIdle(50);
        config.setMinIdle(100);
        config.setMaxWaitMillis(2000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        int timeOut = 2000;
        int maxAttempts = 5;
        // Redis集群的节点集合
        JedisCluster jedisCluster = new JedisCluster(redisNodeSet, timeOut, maxAttempts, config);

        Map<String, String> map = jedisCluster.hgetAll("xiaoming");
        System.out.println(map);
        jedisCluster.close();

    }

}
