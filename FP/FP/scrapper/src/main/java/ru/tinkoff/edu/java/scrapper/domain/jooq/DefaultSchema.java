package ru.tinkoff.edu.java.scrapper.domain.jooq;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.User;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.UserLink;


@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    public final Link LINK = Link.LINK;


    public final User USER = User.USER;

    public final UserLink USER_LINK = UserLink.USER_LINK;

    private DefaultSchema() {
        super("", null);
    }


    @Override
    @NotNull
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    @NotNull
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Link.LINK,
            User.USER,
            UserLink.USER_LINK
        );
    }
}