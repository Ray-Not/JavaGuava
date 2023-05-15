package ru.tinkoff.edu.java.scrapper.domain.jooq;

import javax.annotation.processing.Generated;

import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.User;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLink;


/**
 * Convenience access to all tables in the default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>link</code>.
     */
    public static final Link LINK = Link.LINK;

    /**
     * The table <code>user</code>.
     */
    public static final User USER = User.USER;

    /**
     * The table <code>user_link</code>.
     */
    public static final UserLink USER_LINK = UserLink.USER_LINK;
}