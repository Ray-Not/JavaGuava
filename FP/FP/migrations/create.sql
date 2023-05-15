create table "user" (
    chat_id bigint primary key,
    username text unique not null,
    first_name text,
    last_name text
);

create table "link" (
    id bigserial primary key,
    url text unique not null,
    updated_at timestamp not null
);

create table "user_link" (
    link_id bigint references "link" (id),
    chat_id bigint references "user" (chat_id),
    primary key (link_id,chat_id)
)