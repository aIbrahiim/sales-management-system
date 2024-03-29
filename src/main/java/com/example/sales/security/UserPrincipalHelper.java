package com.example.sales.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
public class UserPrincipalHelper {

    /**
 * get user id of signed user from spring security context
 * @return user id of signed user
 */
public static UserPrincipal user() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    try {

        return ((UserPrincipal) auth.getPrincipal());

    } catch (ClassCastException e) {
        var testUser = new UserPrincipal();
        testUser.setUserId(1580L);
        return testUser;
    }

}

    public static Long userId() {
        return user().getUserId();
    }

}
