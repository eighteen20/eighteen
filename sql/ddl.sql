create table if not exists love.users
(
    user_id        serial
        primary key,
    open_id        varchar(50)                                                       not null,
    phone_number   varchar(20)                                                       not null,
    full_name      varchar(50),
    nickname       varchar(50),
    birth_date     date                                                              not null,
    gender         char,
    flag           boolean                  default true                             not null,
    created_at     timestamp with time zone default (now() AT TIME ZONE 'utc'::text) not null,
    created_by     varchar(50)                                                       not null,
    updated_at     timestamp with time zone default (now() AT TIME ZONE 'utc'::text) not null,
    updated_by     varchar(50)                                                       not null,
    lunar_birthday date                                                              not null,
    avatar         varchar(255)
);

comment on column love.users.user_id is '用户ID，自动递增';

comment on column love.users.open_id is '用户的openId';

comment on column love.users.phone_number is '手机号';

comment on column love.users.full_name is '姓名';

comment on column love.users.nickname is '昵称';

comment on column love.users.birth_date is '阳历生日';

comment on column love.users.gender is '性别';

comment on column love.users.flag is '是否有效，默认为TRUE';

comment on column love.users.created_at is '创建日期，自动使用当前时间';

comment on column love.users.created_by is '创建人';

comment on column love.users.updated_at is '修改日期，自动使用当前时间';

comment on column love.users.updated_by is '修改人';

comment on column love.users.lunar_birthday is '农历生日';

comment on column love.users.avatar is '头像URL';

alter table love.users
    owner to postgresql;

create index if not exists idx_users_open_id
    on love.users (open_id);

create index if not exists idx_users_phone_number
    on love.users (phone_number);

create index if not exists idx_users_created_by
    on love.users (created_by);

create index if not exists idx_users_updated_by
    on love.users (updated_by);

create table if not exists love.subscribe_message_scheduled_task
(
    id                       serial
        primary key,
    open_id                  varchar(255)                           not null,
    template_id              varchar(255)                           not null,
    message_content          jsonb                                  not null,
    cron_expression          varchar(255)                           not null,
    task_name                varchar(255)                           not null,
    flag                     boolean                  default true,
    created_at               timestamp with time zone default CURRENT_TIMESTAMP,
    created_by               varchar(255),
    updated_at               timestamp with time zone default CURRENT_TIMESTAMP,
    updated_by               varchar(255),
    task_type                integer                                not null,
    "execute_after_startup " boolean                  default false not null
);

comment on column love.subscribe_message_scheduled_task.id is '主键';

comment on column love.subscribe_message_scheduled_task.open_id is '接收人openID';

comment on column love.subscribe_message_scheduled_task.template_id is '订阅消息模板ID';

comment on column love.subscribe_message_scheduled_task.message_content is '订阅消息内容(JSON字符串)';

comment on column love.subscribe_message_scheduled_task.cron_expression is '定时任务执行时间(cron表达式)';

comment on column love.subscribe_message_scheduled_task.task_name is '任务名称';

comment on column love.subscribe_message_scheduled_task.flag is '是否有效，默认为TRUE';

comment on column love.subscribe_message_scheduled_task.created_at is '创建日期，自动使用当前时间';

comment on column love.subscribe_message_scheduled_task.created_by is '创建人';

comment on column love.subscribe_message_scheduled_task.updated_at is '修改日期，自动使用当前时间';

comment on column love.subscribe_message_scheduled_task.updated_by is '修改人';

comment on column love.subscribe_message_scheduled_task.task_type is '任务类型（执行对应的代码函数）';

comment on column love.subscribe_message_scheduled_task."execute_after_startup " is '是否项目启动后立即执行';

alter table love.subscribe_message_scheduled_task
    owner to postgresql;

create table if not exists love.task_type_info
(
    id                      integer                                            not null
        constraint taske_type_info_pk
            primary key,
    task_type_name          varchar(255)                                       not null,
    task_executor_bean_name varchar(255)                                       not null,
    flag                    boolean                  default true              not null,
    created_at              timestamp with time zone default CURRENT_TIMESTAMP not null,
    created_by              integer                                            not null,
    updated_at              timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_by              integer                                            not null
);

comment on table love.task_type_info is '任务类型（对应代码中实现类）';

comment on column love.task_type_info.id is '主键';

comment on column love.task_type_info.task_type_name is '任务类型的名称';

comment on column love.task_type_info.task_executor_bean_name is '任务执行类的Bean名称（即@Component注解中的名称）';

comment on column love.task_type_info.flag is '是否有效，默认为TRUE';

comment on column love.task_type_info.created_at is '创建日期，自动使用当前时间';

comment on column love.task_type_info.created_by is '创建人';

comment on column love.task_type_info.updated_at is '修改日期，自动使用当前时间';

comment on column love.task_type_info.updated_by is '修改人';

alter table love.task_type_info
    owner to postgresql;

