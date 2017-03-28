package com.egoo.sso.server.util;

public enum Role {
    AGENT,
    USER,
    TENANT,//租户级别 level：1
    ADMIN,
    MANAGER,
    AGENT_GROUP,//坐席组级别 level:2
    SKILL_GROUP,//技能组级别 level：3
    MONITOR //组长
}