package ru.tinkoff.edu.java.scrapper.domain.jooq.tables;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.DefaultSchema;
import ru.tinkoff.edu.java.scrapper.domain.jooq.Keys;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.UserLinkRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserLink extends TableImpl<UserLinkRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>user_link</code>
     */
    public static final UserLink USER_LINK = new UserLink();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<UserLinkRecord> getRecordType() {
        return UserLinkRecord.class;
    }

    /**
     * The column <code>user_link.LINK_ID</code>.
     */
    public final TableField<UserLinkRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>user_link.CHAT_ID</code>.
     */
    public final TableField<UserLinkRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private UserLink(Name alias, Table<UserLinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserLink(Name alias, Table<UserLinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>user_link</code> table reference
     */
    public UserLink(String alias) {
        this(DSL.name(alias), USER_LINK);
    }

    /**
     * Create an aliased <code>user_link</code> table reference
     */
    public UserLink(Name alias) {
        this(alias, USER_LINK);
    }

    /**
     * Create a <code>user_link</code> table reference
     */
    public UserLink() {
        this(DSL.name("user_link"), null);
    }

    public <O extends Record> UserLink(Table<O> child, ForeignKey<O, UserLinkRecord> key) {
        super(child, key, USER_LINK);
    }

    @Override
    @NotNull
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public UniqueKey<UserLinkRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_143;
    }

    @Override
    @NotNull
    public List<ForeignKey<UserLinkRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_1, Keys.CONSTRAINT_14);
    }

    private transient Link _link;
    private transient User _user;

    /**
     * Get the implicit join path to the <code>PUBLIC.link</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.CONSTRAINT_1);

        return _link;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.user</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.CONSTRAINT_14);

        return _user;
    }

    @Override
    @NotNull
    public UserLink as(String alias) {
        return new UserLink(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public UserLink as(Name alias) {
        return new UserLink(alias, this);
    }

    @Override
    @NotNull
    public UserLink as(Table<?> alias) {
        return new UserLink(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public UserLink rename(String name) {
        return new UserLink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public UserLink rename(Name name) {
        return new UserLink(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public UserLink rename(Table<?> name) {
        return new UserLink(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}