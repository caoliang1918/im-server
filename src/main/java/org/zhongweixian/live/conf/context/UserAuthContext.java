package org.zhongweixian.live.conf.context;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class UserAuthContext {
    private ThreadLocal<LoginToken> contextUser = new ThreadLocal<>();

    public ThreadLocal<LoginToken> getContextUser() {
        return contextUser;
    }


    public void remove() {
        contextUser.remove();
    }
}
