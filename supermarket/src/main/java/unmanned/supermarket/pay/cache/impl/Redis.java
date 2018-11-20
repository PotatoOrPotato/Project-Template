package unmanned.supermarket.pay.cache.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import unmanned.supermarket.pay.cache.Cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository("redis")
public class Redis implements Cache {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(Redis.class);

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate ;
    public static String PROJECT_NAME = "crude_oil";


    /**
     * 根据key取value
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                final String newkey = PROJECT_NAME + "_" + key;
                if (connection.exists(newkey.getBytes())) {
                    return new String(connection.get(newkey.getBytes())); // 从redis服务器获取值
                } else {
                    return null;
                }
            }
        });

    }

    /**
     * set缓存，设置时间
     * @param key
     * @param value
     * @param liveTime
     */
    public Boolean set(String key, String value, int liveTime) {
        final String newkey = PROJECT_NAME + "_" + key;
        return this.set(newkey.getBytes(), value.getBytes(), liveTime);
    }
    /**
     * set缓存，无时间限制
     * @param key
     * @param value
     */
    public Boolean set(String key, String value) {
        final String newkey = PROJECT_NAME + "_" + key;
        return this.set(newkey.getBytes(), value.getBytes(), 0);
    }
    /**
     * set缓存
     * @param key
     * @param value
     * @param liveTime
     * @return
     */
    private Boolean set(final byte[] key, final byte[] value, final Integer liveTime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return true;
            }
        });
    }


    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    public boolean saveCache(final String key, final Object value) {
        return this.saveCache(key, value, null);
    }
    /**
     * 添加缓存
     * @param key
     * @param value
     * @param liveTime
     * @return
     */
    @SuppressWarnings(value={"unchecked", "rawtypes"})
    public boolean saveCache(final String key, final Object value, final Integer liveTime) {
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getStringSerializer();
                final String newkey = PROJECT_NAME + "_" + key;
                byte[] keys = serializer.serialize(newkey);
                byte[] objVal = serializer.serialize(value);
                connection.set(keys, objVal);
                if (null != liveTime && liveTime > 0) {
                    connection.expire(keys, liveTime);
                }
                return true;
            }
        });
        return result;
    }


    /**
     * 读取缓存，返回对象
     * @param key
     * @return
     */
    @SuppressWarnings("all")
    public <T> T readCache(final String key) {
        T result = (T) redisTemplate.execute(new RedisCallback<T>() {
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getStringSerializer(); //getDefaultSerializer();
                final String newkey = PROJECT_NAME + "_" + key;
                byte[] keyByte = serializer.serialize(newkey);
                byte[] value = connection.get(keyByte);
                if (value == null) {
                    return null;
                }
                T u = (T) redisTemplate.getStringSerializer().deserialize(value);
                return u;
            }
        });
        return result;
    }


    /**
     * 读取缓存返回list
     * @param key
     * @return
     */
    @SuppressWarnings("all")
    public <T> List<T> readListCache(final String key) {
        List<T> objList = (List<T>) redisTemplate.execute(new RedisCallback<Object>() {
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = redisTemplate.getStringSerializer();
                final String newkey = PROJECT_NAME + "_" + key;
                byte[] keyByte = serializer.serialize(newkey);
                long length = connection.lLen(keyByte);
                List<byte[]> values = connection.lRange(keyByte, 0, length);
                if (values == null) {
                    return null;
                }
                List<Object> objList = new ArrayList<Object>();
                for (byte[] value : values) {
                    Object u = (Object) redisTemplate.getStringSerializer().deserialize(value);
                    objList.add(u);
                }
                connection.del(keyByte);
                return objList;
            }
        });
        return objList;
    }


    /**
     * 清除缓存
     * @param keys
     * @return
     */
    public Long del(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    final String newkey = PROJECT_NAME + "_" + keys[i];

                    result = connection.del(newkey.getBytes());
                }
                return result;
            }
        });
    }
}
