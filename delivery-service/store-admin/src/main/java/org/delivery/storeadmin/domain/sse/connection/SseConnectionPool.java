package org.delivery.storeadmin.domain.sse.connection;

import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolInterface;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseConnectionPool implements ConnectionPoolInterface<String, UserSseConnection> {

    private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
        connectionPool.put(uniqueKey, userSseConnection);
    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection session) {
        connectionPool.remove(session.getUniqueKey());
    }
}
